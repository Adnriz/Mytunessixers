package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainControllor {


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

        public void NewPlaylist (ActionEvent actionEvent) throws Exception {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PlaylistWindow.fxml"));
            Parent root = loader.load();
            PlaylistController playlistController = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("Playlist Manager");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }




