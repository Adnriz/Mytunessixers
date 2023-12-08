package gui;

import dal.SQLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SongControllor {

    @FXML
    private TextField nameOfSong;
    @FXML
    private TextField nameOfArtist;
    @FXML
    private TextField durationOfSong;
    @FXML
    private TextField genreOfTheSong;
    private SQLController sqlController;
    private static final String MUSIC_SAVER = ".idea/Music";




    private void durationConverter() {

    }

    public void fileChoose(ActionEvent actionEvent) throws IOException {

        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
        //Checker om den givende fil er godkent
        if (selectedFile != null) {
            String modifiedFileName = selectedFile.getName().toLowerCase();
            if (modifiedFileName.endsWith(".mp3") || modifiedFileName.endsWith(".wav")) {
                System.out.println("Virker");
                saveSong(selectedFile);



                String titleOfSong = nameOfSong.toString();
                String artistOfSong = nameOfArtist.toString();
                String genreOfSong = genreOfTheSong.toString();
                String filePathOfSong = selectedFile.getPath().toString();
                int duration = 0;
                //addToDB(titleOfSong, artistOfSong, duration, filePathOfSong);


                stage.close();

            }
            else {
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/WrongFileView.fxml"));
                Parent root = loader.load();
                WrongFileControllor wrongFileControllor = loader.getController();
                primaryStage.setTitle("Error 404");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
                primaryStage.setResizable(false);
                }
            }
        else {
            System.out.println("Virker ikke");
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
/*
    private void addToDB(String title, String artist, int duration, String filepath) {
        try (Connection connection = DriverManager.getConnection()){
            String query = "INSERT INTO Music (title, artist, duration, filepath) VALUES(????)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, artist);
                preparedStatement.setInt(3, duration);
                preparedStatement.setString(4, filepath);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected>0) {
                    System.out.println("Song added without fail");
                }
                else {
                    System.out.println("fail, something went wrong");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

 */

}
