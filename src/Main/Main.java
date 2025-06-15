package Main;

// JavaFX
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.FileNotFoundException; // complemento de exceções
import java.net.URL; // lib de URL, converte o caminho do fxml para uma URL

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/ui/GUI/view/equipamento_view.fxml"));

            URL fxmlUrl = getClass().getResource("/ui/GUI/view/equipamento_view.fxml");
            if (fxmlUrl == null) {
                throw new FileNotFoundException("Arquivo FXML não encontrado no caminho: /ui/GUI/view/equipamento_view.fxml");
            }

            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gerenciamento de Equipamentos");
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
        // carregar interface grafica
        launch(args);
    }
}
