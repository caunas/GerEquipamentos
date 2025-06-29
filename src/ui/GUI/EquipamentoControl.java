package ui.GUI;

/**
 * Jeremias 29:11
 * Autores: Robert, Cauan
 **/

import core.Equipamento;
import core.EquipamentoDAO;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import util.Verificador;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

public class EquipamentoControl {

    // campos de formulario
    @FXML private TextField nomeField;
    @FXML private TextField buscaField;
    @FXML private ChoiceBox<String> categoriaSelector;
    @FXML private ChoiceBox<String> categoriaSelectorBusca;
    @FXML private TextField detalhesField;
    @FXML private TextField idGerenciarField;
    @FXML private TextField caminhoArquivoField;
    @FXML private ChoiceBox<String> temaSelector;

    // tabela e colunas
    @FXML private TableView<Equipamento> tabela;
    @FXML private TableColumn<Equipamento, Integer> colId;
    @FXML private TableColumn<Equipamento, String> colNome;
    @FXML private TableColumn<Equipamento, String> colCategoria;
    @FXML private TableColumn<Equipamento, String> colData;
    @FXML private TableColumn<Equipamento, String> colDetalhes;

    // labels
    @FXML private Label totalLabel;
    @FXML private Label statusPersistencia;


    private ObservableList<Equipamento> equipamentos = FXCollections.observableArrayList();
    private ObservableList<String> categorias_disponiveis = FXCollections.observableArrayList(
            "Construção",
            "Manutenção",
            "Informática"
    );

    @FXML
    public void initialize(){
        // Inicializa as colunas da tabela com os dados do Equipamento
        colId.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getId()).asObject());
        colNome.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNome()));
        colCategoria.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCategoria()));
        colData.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDataCadastro()));
        colDetalhes.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDetalhes()));


        // Inicializa as opções de seleção das categorias
        categoriaSelector.setItems(categorias_disponiveis);
        categoriaSelectorBusca.setItems(categorias_disponiveis);

        atualizarTabela();
    }

    @FXML
    public void adicionarEquipamento(ActionEvent event) {
        String nome = nomeField.getText();
        String categoria = categoriaSelector.getValue();
        String detalhes = detalhesField.getText(); // Ajustado :)

        if (!nome.isEmpty() && !categoria.isEmpty()) {
            EquipamentoDAO.adicionar(nome, categoria, detalhes);
            atualizarTabela();
            limparCampos(null);
        } else {
            alert("Preencha todos os campos para adicionar.");
        }
    }

    @FXML
    public void atualizarEquipamento(ActionEvent event) {
        String busca = idGerenciarField.getText();
        alterarEquipamento alterar = new alterarEquipamento();
        try {
            String coluna = alterar.escolherColuna();
            if(coluna == null){
                alert("Operação cancelada!");
            } else{
                alterar.alterarValor(
                        parseInt(busca),
                        coluna
                );
            }
        } catch (NumberFormatException e) {
            alert("Digite um ID válido para atualizar.");
        }
    }

    @FXML
    public void buscaPorCategoria(ActionEvent event) {
        String busca = categoriaSelectorBusca.getValue();
        try{
            List buffer = EquipamentoDAO.buscarPorCategoria(busca);
            equipamentos.setAll(buffer);
            totalLabel.setText(equipamentos.size() + " equipamentos");
        } catch(Exception e){
            System.out.println(e);
            alert(e.getMessage());
        }
    }

    @FXML
    public void buscaPorId(ActionEvent event){
        String busca = buscaField.getText();
        try{
            Equipamento buffer = EquipamentoDAO.buscarPorId(parseInt(busca));
            if(buffer == null){
                alert("Equipamento com o ID " + busca + " não consta na tabela");
            } else{
                equipamentos.setAll(buffer);
                totalLabel.setText(equipamentos.size() + " equipamentos");
            }
        } catch(Exception NumberFormatException){
            alert("Insira um ID válido!");
        }
    }

    @FXML
    public void buscaPorNome(ActionEvent event) {
        nomeField.clear();
        buscaField.clear();
    }

    @FXML
    public void removerEquipamento(ActionEvent event) {
        String busca = idGerenciarField.getText();
        try {
            int id = parseInt(busca);
            if(EquipamentoDAO.remover(id) == false){
                alert("Equipamento com o ID " + busca + " não consta na tabela");
            } else {
                EquipamentoDAO.remover(id);
                alert("Equipamento Removido!");
                //limparCampos(null);
                atualizarTabela();
                idGerenciarField.clear();
            }
        } catch (NumberFormatException e) {
            alert("Digite um ID válido para remover.");
        }
    }

    @FXML
    public void limparCampos(ActionEvent event) {
        nomeField.clear();
        buscaField.clear();
        detalhesField.clear();
        idGerenciarField.clear();
    }

    @FXML
    private void atualizarTabela() {
        equipamentos.setAll(EquipamentoDAO.listarTodos());
        tabela.setItems(equipamentos);
        totalLabel.setText(equipamentos.size() + " equipamentos");
    }

    private void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    private void carregarDados() {
        // Criação do seletor de arquivos
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        File arquivoSelecionado = fileChooser.showOpenDialog(null);

        if (arquivoSelecionado != null) {
            try {
                EquipamentoDAO.carregarBackup(arquivoSelecionado);
                alert("Dados carregados com sucesso de: " + arquivoSelecionado.getAbsolutePath());
                atualizarTabela();
            } catch (Exception e) {
                alert("Erro ao carregar: " + e.getMessage());
            }
        } else {
            alert("Nenhum arquivo selecionado.");
        }
    }

    @FXML
    private void salvarDados() {
        DirectoryChooser chooser = new DirectoryChooser();

        File selectedDirectory = chooser.showDialog(null);
        if (selectedDirectory != null) {
            File arquivo = new File(selectedDirectory, "equipamentos.csv");

            try {
                EquipamentoDAO.exportarEmArquivo(arquivo);
                alert("Dados salvos com sucesso em: " + arquivo.getAbsolutePath());
            } catch (Exception e) {
                statusPersistencia.setText("Erro ao salvar: " + e.getMessage());
            }
        } else {
            statusPersistencia.setText("Nenhum diretório selecionado.");
        }
    }

    private class alterarEquipamento{
        // lista de colunas disponiveis
        ObservableList<String> colunas_disponiveis = FXCollections.observableArrayList(
                "Nome",
                "Categoria",
                "Detalhes"
        );

        ChoiceDialog atualizar_eq_escolha = new ChoiceDialog("Escolha uma coluna", colunas_disponiveis);

        TextInputDialog atualizar_eq_texto = new TextInputDialog();
        private String escolherColuna(){
            atualizar_eq_escolha.setTitle("Atualizar item");

            // verificador de coluna selecionada
            while(true){
                Optional<String> resultado = atualizar_eq_escolha.showAndWait();

                if(resultado.isPresent()){
                    // se o resultado for diferente do placeholder, o loop é quebrado
                    if(resultado.get() != "Escolha uma coluna"){
                        return resultado.get();
                    }
                    else{
                        alert("Escolha uma coluna para alterar");
                    }
                } else{
                    return null;
                }
            }
        }

        private void alterarValor(int id_linha, String coluna){
            Optional<String> resultado = Optional.empty();
            ChoiceDialog atualizar_eq_novaCategoria = new ChoiceDialog("Escolha uma categoria", categorias_disponiveis);
            if(coluna == "Categoria"){
                atualizar_eq_novaCategoria.setTitle("Escolha a nova categoria");

                resultado = atualizar_eq_novaCategoria.showAndWait();
            } else{
                atualizar_eq_texto.setTitle("Atualizar equipamento");
                atualizar_eq_texto.setContentText("Digite o novo valor");

                resultado = atualizar_eq_texto.showAndWait();
            }

            if(resultado.isPresent()){
                while(true){
                    if(coluna == "Categoria"){
                        // usa o ultilitario para verificar se a categoria selecionada realmente existe
                        if(Verificador.validarCategoria(resultado.get()) == false){
                            alert("Escolha uma categoria valida!");
                            resultado = atualizar_eq_novaCategoria.showAndWait();
                        }else{
                            break;
                        }
                    }else{
                        break;
                    }
                }
                EquipamentoDAO.atualizar(id_linha, coluna, resultado.get());
                atualizarTabela();
                alert("Equipamento alterado com sucesso!");
            }
        }
    }

}
