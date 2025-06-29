package Main;

import core.EquipamentoDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.net.URL;

/**
 * Classe principal da aplicação JavaFX para gerenciamento de equipamentos.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Carrega os dados do arquivo CSV
            EquipamentoDAO.carregarDoArquivo();

            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource("/ui/GUI/view/equipamento_view.fxml");
            if (fxmlUrl == null) {
                throw new FileNotFoundException("Arquivo FXML não encontrado: /ui/GUI/view/equipamento_view.fxml");
            }

            loader.setLocation(fxmlUrl);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gerenciamento de Equipamentos");

            // Ao fechar a janela, salva os dados no arquivo
            primaryStage.setOnCloseRequest(event -> {
                EquipamentoDAO.salvarEmArquivo();
            });

            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Erro ao iniciar a aplicação:");
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro Fatal");
            alert.setHeaderText("Não foi possível carregar a interface");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
