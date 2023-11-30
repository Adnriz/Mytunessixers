package gui;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.security.cert.Extension;

public class SongControllor {


    public void fileChoose(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
        //Checker om den givende fil er godkent
        if (selectedFile != null) {
            String modifiedFileName = selectedFile.getName().toLowerCase();
            if (modifiedFileName.endsWith(".mp3") || modifiedFileName.endsWith(".wav")) {
                System.out.println("Virker");
            }
            else {
                    System.out.println("forkert fil type");
                }
            }
        else {
            System.out.println("Virker ikke");
        }


    }
}
