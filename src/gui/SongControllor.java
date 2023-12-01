package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


public class SongControllor {

    private static final String MUSIC_SAVER = ".idea/Music";


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

}
