import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginWindow.fxml"));
        Parent rootScene = loader.load();
        primaryStage.setScene(new Scene(rootScene));
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setTitle("MyTunes - The number 1 music player for windows");

    }


}
