package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Optional;

public class ControllerFindWordGUI { //Handle Event for find word interface
    @FXML
    private AnchorPane PaneFW;
    @FXML
    private TextField SearchTF;
    @FXML
    private TextArea ShowMeaning;
    private Word wordBeforeChange = new Word();

    public void setTextForSearchTF(String s) {
        SearchTF.setText(s);
    }

    public void setTextForShowMeaning(String s) {
        ShowMeaning.setText(s);
    }

    public void HandleEventTTButton(ActionEvent event) {
        try {
            SearchTF.setText("");
            ShowMeaning.setText("");
            AnchorPane pane = FXMLLoader.load(getClass().getResource("TransText.fxml"));
            PaneFW.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void HandleEventSearch() {
        try {
            String WordWantFind = SearchTF.getText();
            if (WordWantFind.length() == 0) {
                ShowMeaning.setText("You dont type anything!");
            } else {
                Word findedWord = Main.dict.findWord(SearchTF.getText());
                if (findedWord != null) {
                    ShowMeaning.setText(findedWord.getWord() + "          " + findedWord.getPronun() + "\n" + "\n");
                    ShowMeaning.appendText(findedWord.getMeaning());
                } else {
                    ShowMeaning.setText("Cant find this word!");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void HandleChangeWord(ActionEvent event) {
        wordBeforeChange = null;
        if (SearchTF.getText().length() > 0) {
            wordBeforeChange = Main.dict.findWord(SearchTF.getText());
        }
        if (wordBeforeChange != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangeMeaning.fxml"));
                Parent root = loader.load();
                ControllerChangeMeaningGUI controllerChangeMeaningGUI = loader.getController();
                controllerChangeMeaningGUI.setTextForWordField(wordBeforeChange.getWord());
                controllerChangeMeaningGUI.setTextForPronunField(wordBeforeChange.getPronun());
                controllerChangeMeaningGUI.setTextForMeaningField(wordBeforeChange.getMeaning());
                controllerChangeMeaningGUI.setWordBeforeChange(wordBeforeChange);
                Main.window.setScene(new Scene(root));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void HandleEventDeleteWord(ActionEvent event) {
        if (SearchTF.getText().length() > 0 && Main.dict.findWord(SearchTF.getText()) != null) {
            //show selections to confirm your request
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Do you want to delete this word?");
            alert.setContentText("Choose your option");

            ButtonType buttonTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonTypeYes) {//save and return main interface
                for (int i = 0; i < Main.dict.length; i++) {
                    if (Main.dict.WordInDict[i].getWord().equals(SearchTF.getText())) {
                        for (int j = i + 1; j < Main.dict.length; j++) {
                            Main.dict.WordInDict[j - 1] = Main.dict.WordInDict[j];
                        }
                        Main.dict.length -= 1;
                        break;
                    }
                }
                //show alert confirm
                Alert confirmYourRequest = new Alert(Alert.AlertType.INFORMATION);
                confirmYourRequest.setContentText("This word was deleted sucessfully");
                Optional<ButtonType> result2 = confirmYourRequest.showAndWait();

                //save new dict to dictionary
                Main.dict.updateDataToDictionaryFile();

                SearchTF.setText("");
                ShowMeaning.setText("");
            }
        }
    }

    public void HandleEventAddNewWord(ActionEvent event) {
        try {
            SearchTF.setText("");
            ShowMeaning.setText("");
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AddNewWord.fxml"));
            PaneFW.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void HandleKeyPressedSearchTF(KeyEvent keyEvent) throws Exception {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            HandleEventSearch();
        }
    }

    public void HandleEventTextToSpeech(ActionEvent event) throws Exception {
        String text = SearchTF.getText();
        Translator.pronunciation(text);
    }
}
