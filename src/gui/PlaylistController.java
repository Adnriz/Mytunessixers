package gui;

import BE.Playlist;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.SQLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistController {

    @FXML
    private TableView<Playlist> playlistView;
    @FXML
    private TableColumn<Playlist, String> tvPlaylistName;
    private SQLController sqlController;
    @FXML
    private Button cancelPlaylist;
    @FXML
    private TextField playlistName;
    @FXML
    private Label errorNoNameLabel;
    private MainControllor mainControllor;

    public void setMainController(MainControllor mainController) {
        this.mainControllor = mainController;
    }
    public void addPlaylistbtn(ActionEvent actionEvent) throws SQLServerException {
        if (playlistName.getText().isEmpty()) {
            errorNoNameLabel.setText("Error: Playlist must have a name");
        } else {
            try {
                if (sqlController == null) {
                    try {
                        sqlController = new SQLController();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                try (Connection connection = sqlController.getConnection()) {
                    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Playlist (name) VALUES (?)")) {
                        statement.setString(1, playlistName.getText().toString());
                        int rowsAffected = statement.executeUpdate();
                        if (rowsAffected > 0) {
                            Playlist playlist = new Playlist(playlistName.getText().toString());
                            System.out.println("Playlist added to database");
                        } else {
                            System.out.println("Fail, something went wrong");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Playlist")) {
                     ResultSet resultSet = statement.executeQuery();

                     while (resultSet.next()) {
                            Playlist playlist = new Playlist(resultSet.getString("name"));
                            // Add the retrieved playlist to your TableView
                         playlistView.getItems().add(playlist);
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


    public void cancelPlaylist(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelPlaylist.getScene().getWindow();
        stage.close();
    }
}

