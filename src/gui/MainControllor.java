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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainControllor {

    @FXML
    private Label songLabel;
    @FXML
    private Button playButton, pauseButton, previousButton, nextButton;

    @FXML
    private ProgressBar songProgressBar;

    private int songNumber;

    private Timer timer;
    private TimerTask task;
    private boolean running;



    public MainControllor() {
    }

    public void playMedia() {
        beginTimer();


    }

    public void pauseMedia() {
        stopTimer();

    }

    public void previousMedia() {


    }

    public void nextMedia() {

    }

    public void beginTimer() {


    }

    public void stopTimer() {

        running = false;
        timer.cancel();

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
