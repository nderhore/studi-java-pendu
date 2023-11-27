package fr.studi.controller;

import fr.studi.game.Pendu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class JeuPendu {

    private Pendu pendu;

    @FXML
    private Label motEnCours;

    @FXML
    private Label nbError;

    @FXML
    private Label lettresProposees;

    @FXML
    private TextField lettreInput;

    @FXML
    private Label proposeWord;

    public JeuPendu(Pendu pendu){
        this.pendu = pendu;
        updateInterface();
        this.proposeWord.setText("Entrez une lettre :");
    }

    private void updateInterface() {
        
        //on met le mot actuel
        motEnCours.setText(pendu.afficherMotCourantAvecVirgule());
        
        //on met le nombre d'erreur
        nbError.setText(pendu.afficherErreur());
        
        //on met les lettres proposées
        lettresProposees.setText(pendu.getLettresUtilisees().toString());
        
        if(pendu.gameWin() && (pendu.getMaxErreur() >= pendu.getErreur()) ){
            displayAlert("Félicitations ! Vous avez deviné le mot !");
        } else{
            displayAlert("Désolé, vous avez perdu. le mot etait : " + pendu.getMot());
        }
        
        
    }

    private void displayAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public Pendu getPendu() {
        return pendu;
    }

    public void setPendu(Pendu pendu) {
        this.pendu = pendu;
    }

    public void proposeSentence() {
        String lettres = lettreInput.getText();
        if(lettres != null){
            char lettre = lettres.charAt(0);
            if(pendu.estLettreValide(lettre)){
                    pendu.setLetter(lettre);
                    this.updateInterface();
            }else {
                displayAlert("lettre invalide.");
            }

        }

    }
}
