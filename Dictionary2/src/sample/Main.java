package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage window;
    static Dictionary dict = new Dictionary();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            window = primaryStage;
            Parent root = FXMLLoader.load(getClass().getResource("FindWord.fxml"));
            window.setScene(new Scene(root));
            window.setTitle("Dictionary");
            window.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}