<<<<<<< HEAD
package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        // Perform login actions

        // Get the stage (window) that contains the button
        Stage loginStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Close the login window
        loginStage.close();

        // Open the main window
        openMainWindow();
    }

    private void openMainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainWindow.fxml"));
            Parent root = loader.load();
            Stage mainStage = new Stage();
            mainStage.setScene(new Scene(root));
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception appropriately
        }
    }
}


=======
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

>>>>>>> 1a437232f5a6abb0ca632cc2c5a48f2613167a44
