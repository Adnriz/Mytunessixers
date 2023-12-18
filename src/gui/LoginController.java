package gui;

import com.sun.tools.javac.Main;
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
            MainControllor mainControllor = loader.getController();
            mainStage.setScene(new Scene(root));
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception appropriately
        }
    }
    // test test dur den nu
}