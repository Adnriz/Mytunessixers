package gui;


import BE.Playlist;
import BE.PlaylistSong;
import BE.Song;
import dal.SQLController;
import gui.Model.PlaylistModel;
import gui.Model.SongModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;


public class MainControllor implements Initializable{

    @FXML
    private TextField SearchBar;
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

    private int songNumber = 0;
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
    private TableView<Playlist> playlistView;
    @FXML
    private TableColumn tvPlaylistName;

    @FXML
    private ListView<Song> showSongs;

    private Playlist currentPlaylist;
    private Song currentSong;
    private List<Song> currentSongList;

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
        playlistController.setMainController(this);  // This is the line you have to add
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

            nextButton.setOnAction(event -> nextSong());
            previousButton.setOnAction(event -> previousSong());
            playButton.setOnAction(event -> {
                if (mediaPlayer != null) {
                    mediaPlayer.play();
                }
            });
            pauseButton.setOnAction(event -> {
                if (mediaPlayer != null) {
                    mediaPlayer.pause();
                }
            });
            songTableView.setOnMouseClicked(this::setSong);

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
            update();
            Searcher();
            playlistView.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
                if (nv != null) {
                    try {
                        playlistModel.updateSongsInPlaylist(nv);
                        showSongs.setItems(playlistModel.getObservableSongsInPlaylist());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        public void update(){
            try {
                titleColumn.setCellValueFactory(new PropertyValueFactory<>("songName"));
                artistColumn.setCellValueFactory(new PropertyValueFactory<>("artistName"));
                genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
                tvPlaylistName.setCellValueFactory(new PropertyValueFactory<>("playlistName"));
                songModel.updateSongs();
                songTableView.setItems(songModel.getObservableSongs());
                ObservableList<Song> songs = songModel.getObservableSongs();
                playlistModel.updatePlaylist();
                playlistView.setItems(playlistModel.getObservablePlaylists());
                ObservableList<Playlist> playlists = playlistModel.getObservablePlaylists();
                if(playlistModel.getPlaylist() != null){
                    playlistModel.updateSongsInPlaylist(playlistModel.getPlaylist());
                    showSongs.setItems(playlistModel.getObservableSongsInPlaylist());
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            
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
        } else {
            // If it's last song, reset the song number to 0 for a loop-back mechanism.
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

        private void Searcher(){
        SearchBar.textProperty().addListener((((observable, oldValue, newValue) ->
        {
            try{
                songModel.SearchMethod(newValue);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        })));
    }

        public void ClearBtn(ActionEvent actionEvent) {
    }

    public void moveToPlaylist(ActionEvent actionEvent) {
        if (playlistView.getSelectionModel().getSelectedItem() != null&& songTableView.getSelectionModel().getSelectedItem() != null){
            Song song = (Song) songTableView.getSelectionModel().getSelectedItem();
            Playlist playlist = (Playlist) playlistView.getSelectionModel().getSelectedItem();
            try {
                PlaylistSong playlistSong = new PlaylistSong(playlist.getId(), song.getId());
                playlistModel.addSongToPlaylist(playlistSong);
                update();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deletePlaylist(ActionEvent actionEvent) {
        try {
            Playlist playlist = playlistView.getSelectionModel().getSelectedItem();
            if (playlist != null) {
                playlistModel.deletePlaylist(playlist);
                update();  // If update is responsible for refreshing your table view.
            } else {
                // Handle situation when there is no playlist selected, e.g., give feedback to the user.
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSongs(ActionEvent actionEvent) throws Exception {
        Song song = (Song) songTableView.getSelectionModel().getSelectedItem();
        SongControllor songControllor = new SongControllor();
        songControllor.deleteSong(song);
        update();
    }

    public void removeFromPlaylist(ActionEvent actionEvent){

        try {
            Song song = showSongs.getSelectionModel().getSelectedItem();
            Playlist playlist = playlistView.getSelectionModel().getSelectedItem();
            PlaylistSong playlistSong = new PlaylistSong(playlist.getId(), song.getId());
            playlistModel.removeFromPlaylist(playlistSong);
            update();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setPlaylist(MouseEvent event) throws Exception {
        // check if it's a double click
        if (event.getButton().equals(MouseButton.PRIMARY)
                && event.getClickCount() == 2) {

            // get selected playlist
            Playlist clickedPlaylist = playlistView.getSelectionModel().getSelectedItem();
            // check if it's a new playlist
            if (currentPlaylist == null || !currentPlaylist.equals(clickedPlaylist)) {
                // if it's a different playlist, reset song index and set currentPlaylist
                songNumber = 0;
                currentPlaylist = clickedPlaylist;
            } else {
                // else, it's same playlist, increment to next song
                songNumber++;
            }

            //Received songs from playlist through some GET method.
            List<Song> playlistSongs = playlistModel.getPlaylistSongs(currentPlaylist);

            if (songNumber < playlistSongs.size()) {

                mediaPlayer.stop();

                if (running) {
                    cancelTimer();
                }

                // obtaining song object from playlistSongs
                Song currentSong = playlistSongs.get(songNumber);

                // use getter methods from Song class
                String songLocation = currentSong.getFilePath();
                media = new Media(new File(songLocation).toURI().toString());

                mediaPlayer = new MediaPlayer(media);
                songLabel.setText(currentSong.getSongName());

                mediaPlayer.play();
            }
        }
    }
    public void nextSong() {
        if (currentPlaylist == null) {
            // either return or set a default playlist
            return;
        }

        List<Song> playlistSongs;
        try {
            playlistSongs = playlistModel.getPlaylistSongs(currentPlaylist);
        } catch (Exception e) {
            // handle the exception
            e.printStackTrace();
            return;
        }

        if (songNumber < playlistSongs.size() - 1) {
            songNumber++;
            playSongInPlaylist();
        } else {
            // if there's no next song, either wrap around to the beginning of the playlist
            // songNumber = 0;
            // playSongInPlaylist();

            // or stop the player
            mediaPlayer.stop();
            // and optionally reset the songNumber to -1, so starting the player again will start at the first song
            // songNumber = -1;
        }
    }

    public void previousSong() {
        if (currentPlaylist == null) {
            // either return or set a default playlist
            return;
        }

        if (songNumber > 0) {
            songNumber--;
            playSongInPlaylist();
        } else {
            // if there's no previous song, either wrap around to the end of the playlist
            // try {
            //     songNumber = playlistModel.getPlaylistSongs(currentPlaylist).size() - 1;
            // } catch (Exception e) {
            //     // handle the exception
            //     e.printStackTrace();
            //     return;
            // }
            // playSongInPlaylist();

            // or stop the player
            mediaPlayer.stop();
            // and optionally reset the songNumber to 0, so starting the player again will start at the first song
            // songNumber = 0;
        }
    }

    private void playSongInPlaylist() {
        Song currentSong;
        try {
            currentSong = playlistModel.getPlaylistSongs(currentPlaylist).get(songNumber);
        } catch (Exception e) {
            // handle the exception
            e.printStackTrace();
            return;
        }

        mediaPlayer.stop();

        if (running) {
            cancelTimer();
        }

        String songLocation = currentSong.getFilePath();
        media = new Media(new File(songLocation).toURI().toString());

        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(currentSong.getSongName());

        mediaPlayer.play();
    }
    public void setSong(MouseEvent event) {
        // check if it's a double click
        if (event.getButton().equals(MouseButton.PRIMARY)
                && event.getClickCount() == 2) {

            // get selected song
            Song selectedSong = (Song) songTableView.getSelectionModel().getSelectedItem();

            if (selectedSong != null) {
                // stop currently playing song
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    if (running) {
                        cancelTimer();
                    }
                }

                // play selected song
                String songLocation = selectedSong.getFilePath();
                media = new Media(new File(songLocation).toURI().toString());

                mediaPlayer = new MediaPlayer(media);
                songLabel.setText(selectedSong.getSongName());

                mediaPlayer.play();
            }
        }
    }
}