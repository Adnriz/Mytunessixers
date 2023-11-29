package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainControllor {

    public void playMedia() {

    }

    public void pauseMedia() {

    }

    public void previousMedia() {

    }

    public void nextMedia() {

    }
@FXML
    private void addNewSong(ActionEvent actionEvent) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SongManager.fxml"));
        Parent root = loader.load();
        SongControllor songControllor = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Song Manager");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
