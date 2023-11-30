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
    private anchorPane anchorPane;
    @FXML
    private Label songLabel;
    @FXML
    private Button playButton, pauseButton, previousButton, nextButton;
    @FXML
    private Slider volumeSlider;
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

        timer = new Timer();

        task = new TimerT

                public  void run() {
            running = true;
            double current = mediaPlayer.getCurrentTime().toSeconds();
            double end = media.getDuration().toSeconds();
            songProgressBar.setProgress(current/end);

            if(current/end == 1.0) {
                running = false;
                timer.cancel();
            }
        };

    timer.scheduleAtFixedRate(task, 0, 1000);

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
