package gui;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.SQLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.beans.Encoder;
import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.Map;


public class SongControllor {

    private SQLController sqlController;
    @FXML
    private TextField nameOfSong;
    @FXML
    private TextField nameOfArtist;
    @FXML
    private TextField durationOfSong;
    @FXML
    private TextField genreOfTheSong;
    @FXML
    private Button fileChoose;


    private static final String MUSIC_SAVER = ".idea/Music";


    public void fileChoose(ActionEvent actionEvent) throws IOException, SQLServerException, UnsupportedAudioFileException {
        if (isFilledOutCheck() == true) {
            FileChooser fileChooser = new FileChooser();
            Stage stage = new Stage();
            File selectedFile = fileChooser.showOpenDialog(stage);
            //Checker om den givende fil er godkent
            if (selectedFile != null) {
                String modifiedFileName = selectedFile.getName().toLowerCase();
                if (modifiedFileName.endsWith(".mp3") || modifiedFileName.endsWith(".wav")) {
                    System.out.println("Virker");
                    saveSong(selectedFile);

                    //DB stuff
                    String titleOfSong = nameOfSong.getText();
                    String artistOfSong = nameOfArtist.getText();
                    String genreOfSong = genreOfTheSong.getText();
                    String filePathOfSong = getFilePath(selectedFile).toString();
                    String duration = durationOfSong.getText();
                    addToDB(titleOfSong, artistOfSong, duration, filePathOfSong, genreOfSong);

                    //closing the window
                    fileChoose.getScene().getWindow().hide();

                } else {
                    Stage primaryStage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/WrongFileView.fxml"));
                    Parent root = loader.load();
                    WrongFileControllor wrongFileControllor = loader.getController();
                    primaryStage.setTitle("Error 404");
                    primaryStage.setScene(new Scene(root));
                    primaryStage.show();
                    primaryStage.setResizable(false);
                }
            } else {
                System.out.println("Virker ikke");
            }
        }
    }

    private boolean isFilledOutCheck() {
        if (!genreOfTheSong.getText().isEmpty() && !nameOfSong.getText().isEmpty() && !nameOfArtist.getText().isEmpty() && !durationOfSong.getText().isEmpty()) {
            fileChoose.setDisable(false);
        return true;
    }
        else {
            fileChoose.setDisable(true);
            return false;
        }
    }
    private void saveSong(File selectedFile) {
        //Gemmer den mappe som filen skal ligges i
        File saveFolder = new File(MUSIC_SAVER);
        //tjekker om mappen findes
        if (!saveFolder.exists()) {
            System.out.println("Mappen findes ikke");
        }
        //Opretter en Path
        Path musicFolderPath = saveFolder.toPath().resolve(selectedFile.getName());

        try {
            //Gemmer filen i mappen
            Files.copy(selectedFile.toPath(), musicFolderPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Path getFilePath(File selectedFile) {
        String folder = ".idea/Music";
        String fileName = selectedFile.getName();
        return Paths.get(folder, fileName);

    }

    public String getSongName() {
        return nameOfSong.getText();
    }

    private void addToDB(String title, String artist, String duration, String filepath, String genreOfSong) throws SQLServerException {
        try {
            if (sqlController == null) {
                try {
                    sqlController = new SQLController();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            try (Connection connection = sqlController.getConnection()) {
                String query = "INSERT INTO Music (title, artist, duration, filepath, genre) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, title);
                    preparedStatement.setString(2, artist);
                    preparedStatement.setString(3, duration);
                    preparedStatement.setString(4, filepath);
                    preparedStatement.setString(5, genreOfSong);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Song added without fail");
                    } else {
                        System.out.println("Fail, something went wrong");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } finally {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


