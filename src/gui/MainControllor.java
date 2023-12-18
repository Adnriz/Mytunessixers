package gui;


import BE.Playlist;
import BE.Song;
import dal.SQLController;
import gui.Model.PlaylistModel;
import gui.Model.SongModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;


public class MainControllor implements Initializable{

    @FXML
    private Pane pane;
    @FXML
    private Label songLabel;
    @FXML
    private Button playButton, pauseButton, resetButton, previousButton, nextButton;
    @FXML
    private ComboBox<String> speedBox;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ProgressBar songProgressBar;
    private Media media;
    private MediaPlayer mediaPlayer;

    private File directory;
    private File[] files;

    private ArrayList<File> songs;

    private int songNumber;
    private int[] speeds = {25, 50, 75, 100, 125, 150, 175, 200};

    private Timer timer;
    private TimerTask task;

    private boolean running;

    private SQLController sqlController;

    @FXML
    private TableColumn titleColumn;
    @FXML
    private TableColumn artistColumn;
    @FXML
    private TableColumn genreColumn;
    public ListView<Song> lstSongs;
    @FXML
    private TableView songTableView;
    private SongModel songModel;
    private PlaylistModel playlistModel;
    @FXML
    private TableView playlistView;
    @FXML
    private TableColumn tvPlaylistName;

    @FXML
    private TableView<Song> tableView;
    public MainControllor() throws Exception {
        try {
            songModel = new SongModel();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.songModel = new SongModel();
         try {
        playlistModel = new PlaylistModel();
    }
        catch (Exception e) {
        e.printStackTrace();
    }
        this.playlistModel = new PlaylistModel();
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

    public void NewPlaylist (ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PlaylistWindow.fxml"));
        Parent root = loader.load();
        PlaylistController playlistController = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Playlist Manager");
        stage.setScene(new Scene(root));
        stage.show();
    }

        @Override
        public void initialize(URL arg0, ResourceBundle arg1) {
            songs = new ArrayList<File>();

            directory = new File(".idea/Music");

            files = directory.listFiles();

            if(files != null) {

                for(File file : files) {

                    songs.add(file);
                }
            }

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            songLabel.setText(songs.get(songNumber).getName());

            for(int i = 0; i < speeds.length; i++) {

                speedBox.getItems().add(Integer.toString(speeds[i])+"%");
            }

            speedBox.setOnAction(this::changeSpeed);

            volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {

                @Override
                public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {

                    mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
                }
            });

            songProgressBar.setStyle("-fx-accent: #00FF00;");

            // Initialize the person table with the two columns.
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("songName"));
            artistColumn.setCellValueFactory(new PropertyValueFactory<>("artistName"));
            genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

            tvPlaylistName.setCellValueFactory(new PropertyValueFactory<>("playlistName"));

            // add data from observable list
            songTableView.setItems(songModel.getObservableSongs());
            ObservableList<Song> songs = songModel.getObservableSongs();

            playlistView.setItems(playlistModel.getObservablePlaylists());
            ObservableList<Playlist> playlists = playlistModel.getObservablePlaylists();

        }

        public void playMedia() {

            beginTimer();
            changeSpeed(null);
            mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            mediaPlayer.play();
        }

        public void pauseMedia() {

            cancelTimer();
            mediaPlayer.pause();
        }

        public void resetMedia() {

            songProgressBar.setProgress(0);
            mediaPlayer.seek(Duration.seconds(0));
        }

        public void previousMedia() {

            if(songNumber > 0) {

                songNumber--;

                mediaPlayer.stop();

                if(running) {

                    cancelTimer();
                }

                media = new Media(songs.get(songNumber).toURI().toString());
                mediaPlayer = new MediaPlayer(media);

                songLabel.setText(songs.get(songNumber).getName());

                playMedia();
            }
            else {

                songNumber = songs.size() - 1;

                mediaPlayer.stop();

                if(running) {

                    cancelTimer();
                }

                media = new Media(songs.get(songNumber).toURI().toString());
                mediaPlayer = new MediaPlayer(media);

                songLabel.setText(songs.get(songNumber).getName());

                playMedia();
            }
        }

        public void nextMedia() {

            if(songNumber < songs.size() - 1) {

                songNumber++;

                mediaPlayer.stop();

                if(running) {

                    cancelTimer();
                }

                media = new Media(songs.get(songNumber).toURI().toString());
                mediaPlayer = new MediaPlayer(media);

                songLabel.setText(songs.get(songNumber).getName());

                playMedia();
            }
            else {

                songNumber = 0;

                mediaPlayer.stop();

                media = new Media(songs.get(songNumber).toURI().toString());
                mediaPlayer = new MediaPlayer(media);

                songLabel.setText(songs.get(songNumber).getName());

                playMedia();
            }
        }

        public void changeSpeed(ActionEvent event) {

            if(speedBox.getValue() == null) {

                mediaPlayer.setRate(1);
            }
            else {

                mediaPlayer.setRate(Integer.parseInt(speedBox.getValue().substring(0, speedBox.getValue().length() - 1)) * 0.01);
            }
        }

        public void beginTimer() {

            timer = new Timer();

            task = new TimerTask() {

                public void run() {

                    running = true;
                    double current = mediaPlayer.getCurrentTime().toSeconds();
                    double end = media.getDuration().toSeconds();
                    songProgressBar.setProgress(current/end);

                    if(current/end == 1) {

                        cancelTimer();
                    }
                }
            };

            timer.scheduleAtFixedRate(task, 100, 500);
        }

        public void cancelTimer() {

            running = false;
            timer.cancel();
        }

    }