package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControllerAddNewWordGUI {
    @FXML
    TextField Word;
    @FXML
    TextField Pronun;
    @FXML
    TextArea Meaning;
    @FXML
    AnchorPane PaneNW;

    public void AddEvent(ActionEvent event) {
        if (Word.getText().length() == 0 || Pronun.getText().length() == 0 || Meaning.getText().length() == 0) {
            Alert errorNotification = new Alert(Alert.AlertType.INFORMATION);
            errorNotification.setTitle("Notification");
            errorNotification.setHeaderText("Word or pronun or meaning you type is illegal");
            errorNotification.showAndWait();
        } else if (Main.dict.findWord(Word.getText()) != null) {
            Alert Notification = new Alert(Alert.AlertType.INFORMATION);
            Notification.setTitle("Warning!!");
            Notification.setHeaderText("This word was in dictionary, if you want to change, you can search this word and press on changeButton");
            Notification.showAndWait();
        } else {
            Word newWord = new Word();
            newWord.setWord(Word.getText());
            newWord.setPronun(Pronun.getText());
            newWord.setMeaning(Meaning.getText());
            Main.dict.length += 1;
            Main.dict.WordInDict[Main.dict.length - 1] = newWord;

            Alert Notification = new Alert(Alert.AlertType.INFORMATION);
            Notification.setTitle("Notification");
            Notification.setHeaderText("You add new word successfully");
            Notification.showAndWait();

            //add update to dictionary data;
            Main.dict.updateDataToDictionaryFile();

            //return main GUI
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("FindWord.fxml"));
                PaneNW.getChildren().setAll(pane);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void CancelButton(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("FindWord.fxml"));
            PaneNW.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
