package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.Optional;

/**
 * conntroller handle event for change meaning word
 */
public class ControllerChangeMeaningGUI {
    @FXML
    AnchorPane PaneCM;
    @FXML
    TextField Word;
    @FXML
    TextField Pronun;
    @FXML
    TextArea Meaning;
    Word wordBeforeChange;

    public void setWordBeforeChange(Word wordBeforeChange) {
        this.wordBeforeChange = wordBeforeChange;
    }

    public void setTextForWordField(String s) {
        Word.setText(s);
    }

    public void setTextForPronunField(String s) {
        Pronun.setText(s);
    }

    public void setTextForMeaningField(String s) {
        Meaning.setText(s);
    }

    /**
     * events when change word.
     */
    public void SaveEvent(ActionEvent event) throws Exception {

        //show selections to confirm your request
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to save this word?");
        alert.setContentText("Choose your option");

        ButtonType buttonTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonTypeYes) {//save and return main interface
            wordBeforeChange.setWord(Word.getText());
            wordBeforeChange.setMeaning(Meaning.getText());
            wordBeforeChange.setPronun(Pronun.getText());

            //show alert comfirm
            Alert confirmYourRequest = new Alert(Alert.AlertType.INFORMATION);
            confirmYourRequest.setContentText("This word was changed sucessfully");
            Optional<ButtonType> result2 = confirmYourRequest.showAndWait();

            //return main interface
            AnchorPane pane = FXMLLoader.load(getClass().getResource("FindWord.fxml"));
            PaneCM.getChildren().setAll(pane);
        } else if (result.get() == buttonTypeNo) { //if choose no then return main interface and dont save
            AnchorPane pane = FXMLLoader.load(getClass().getResource("FindWord.fxml"));
            PaneCM.getChildren().setAll(pane);
        } else {
        }
        Main.dict.updateDataToDictionaryFile();
    }

    public void CancelButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FindWord.fxml"));
            Parent root = loader.load();
            ControllerFindWordGUI controller2 = loader.getController();
            controller2.setTextForSearchTF(wordBeforeChange.getWord());
            controller2.setTextForShowMeaning(wordBeforeChange.getWord() + "          " + wordBeforeChange.getPronun() + "\n" + "\n" + wordBeforeChange.getMeaning());
            Main.window.setScene(new Scene(root));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
