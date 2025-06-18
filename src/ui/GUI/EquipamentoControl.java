package ui.GUI;

/**
 * Jeremias 29:11
 * Autores: Robert, Cauan
 **/

import core.Equipamento;
import core.EquipamentoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class EquipamentoControl {

    // campos de formulario
    @FXML private TextField nomeField;
    @FXML private TextField buscaField;
    @FXML private ChoiceBox<String> categoriaSelector;
    @FXML private ChoiceBox<String> categoriaSelectorBusca;
    @FXML private TextField detalhesField;

    // tabela e colunas
    @FXML private TableView<Equipamento> tabela;
    @FXML private TableColumn<Equipamento, Integer> colId;
    @FXML private TableColumn<Equipamento, String> colNome;
    @FXML private TableColumn<Equipamento, String> colCategoria;
    @FXML private TableColumn<Equipamento, String> colData;
    @FXML private TableColumn<Equipamento, String> colDetalhes;

    // labels
    @FXML private Label totalLabel;


    private ObservableList<Equipamento> equipamentos = FXCollections.observableArrayList();
    private ObservableList<String> categorias_disponiveis = FXCollections.observableArrayList(
            "Construção",
            "Manutenção",
            "Informática"
    );

    @FXML
    public void initialize() {
        // Inicializa as colunas da tabela com os dados do Equipamento
        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getId()).asObject());
        colNome.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNome()));
        colCategoria.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCategoria()));
        colData.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getDataCadastro()));
        colDetalhes.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getDetalhes()));

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
        String busca = buscaField.getText();

        try {
            int id = parseInt(busca);
            String novoNome = nomeField.getText();
            //String novoTipo = tipoField.getText();

            if (!novoNome.isEmpty()) {
                EquipamentoDAO.atualizar(id, "nome", novoNome);
            }
            /*
            if (!novoTipo.isEmpty()) {
                EquipamentoDAO.atualizar(id, "categoria", novoTipo);
            }
             */

            atualizarTabela();
            limparCampos(null);
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
            equipamentos.setAll(buffer);
            totalLabel.setText(equipamentos.size() + " equipamentos");
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
        String busca = buscaField.getText();
        try {
            int id = parseInt(busca);
            EquipamentoDAO.remover(id);
            atualizarTabela();
            limparCampos(null);
        } catch (NumberFormatException e) {
            alert("Digite um ID válido para remover.");
        }
    }

    @FXML
    public void limparCampos(ActionEvent event) {
        nomeField.clear();
        buscaField.clear();
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
}
