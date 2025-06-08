package control;

/**
 * Jeremias 29:11
 * */

import core.Equipamento;
import core.EquipamentoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EquipamentoControl {

    @FXML private TextField nomeField;
    @FXML private TextField tipoField;
    @FXML private TextField buscaField;
    @FXML private TableView<Equipamento> tabela;
    @FXML private TableColumn<Equipamento, Integer> colId;
    @FXML private TableColumn<Equipamento, String> colNome;
    @FXML private TableColumn<Equipamento, String> colTipo;
    @FXML private Label totalLabel;

    private ObservableList<Equipamento> equipamentos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Inicializa as colunas da tabela com os dados do Equipamento
        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getId()).asObject());
        colNome.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNome()));
        colTipo.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCategoria()));

        atualizarTabela();
    }

    @FXML
    public void adicionarEquipamento(ActionEvent event) {
        String nome = nomeField.getText();
        String tipo = tipoField.getText();
        String detalhes = "N/A"; // Pode ajustar depois

        if (!nome.isEmpty() && !tipo.isEmpty()) {
            EquipamentoDAO.adicionar(nome, tipo, detalhes);
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
            int id = Integer.parseInt(busca);
            String novoNome = nomeField.getText();
            String novoTipo = tipoField.getText();

            if (!novoNome.isEmpty()) {
                EquipamentoDAO.atualizar(id, "nome", novoNome);
            }
            if (!novoTipo.isEmpty()) {
                EquipamentoDAO.atualizar(id, "categoria", novoTipo);
            }

            atualizarTabela();
            limparCampos(null);
        } catch (NumberFormatException e) {
            alert("Digite um ID válido para atualizar.");
        }
    }

    @FXML
    public void removerEquipamento(ActionEvent event) {
        String busca = buscaField.getText();
        try {
            int id = Integer.parseInt(busca);
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
        tipoField.clear();
        buscaField.clear();
    }

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
