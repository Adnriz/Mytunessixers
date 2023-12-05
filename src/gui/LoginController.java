package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {

    public void Loginbtn(ActionEvent actionEvent) throws Exception {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainWindow.fxml"));
            Parent root = loader.load();
            MainControllor mainControllor = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("Login Manager");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

