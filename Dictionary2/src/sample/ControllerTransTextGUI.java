package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Handle event for transtext interface
 */
public class ControllerTransTextGUI {
    @FXML
    AnchorPane PaneTT;
    @FXML
    TextArea textAreaFirst;
    @FXML
    TextArea textAreaSecond;
    @FXML
    Label labelFirst;
    @FXML
    Label labelSecond;

    public void HandleEventFWButton(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("FindWord.fxml"));
            PaneTT.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void transferEng_Vie(ActionEvent event) {
        String tmp = labelFirst.getText();
        labelFirst.setText(labelSecond.getText());
        labelSecond.setText(tmp);
        //change translate
    }

    public void handleEventTranslate(ActionEvent event) throws Exception{
        if (labelFirst.getText().equals("Eng:")) {
            textAreaSecond.setText(Translator.TranslateAString(textAreaFirst.getText(), "en", "vi"));
        } else {
            textAreaSecond.setText(Translator.TranslateAString(textAreaFirst.getText(), "vi", "en"));
        }
    }
}
