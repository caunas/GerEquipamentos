package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Caminho absoluto baseado na URL que você obteve
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/ui/GUI/view/equipamento_view.fxml"));

            // Verificação extra
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
        launch(args);
    }
}
