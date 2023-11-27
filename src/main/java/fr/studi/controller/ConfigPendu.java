package fr.studi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfigPendu {

    @FXML
    public TextField errorInput;

    @FXML
    private TextField wordInput;

    private boolean isConfigValid = false;

    private int errorAuthorized = 0;

    private String word = "";

    @FXML
    public void validerConfiguration() {
        this.getErrorAuthorize();
        this.word = this.wordInput.getText();
        this.isConfigValid = true;
        closePopin();
    }

    private void closePopin() {
        Stage stage = (Stage) errorInput.getScene().getWindow();
        stage.close();
    }

    public boolean isConfigValid() {
        return isConfigValid;
    }

    private void getErrorAuthorize(){
        try{
            this.errorAuthorized = Integer.parseInt(errorInput.getText());
        }catch(NumberFormatException e){
            System.out.println("WARN : is not a number. Set to 0");
        }
    }

    public void setConfigValid(boolean configValid) {
        isConfigValid = configValid;
    }

    public int getErrorAuthorized() {
        return errorAuthorized;
    }

    public void setErrorAuthorized(int errorAuthorized) {
        this.errorAuthorized = errorAuthorized;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
