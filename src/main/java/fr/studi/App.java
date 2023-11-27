package fr.studi;

import fr.studi.controller.ConfigPendu;
import fr.studi.controller.JeuPendu;
import fr.studi.game.Pendu;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App extends Application
{

    private static int checkInput(Scanner scanner){
        int reponse = 0;
        boolean check = false;
        while(!check) {
            try {
                reponse = scanner.nextInt();
                check = true;
            } catch (InputMismatchException e) {
                System.out.println("La saisie n'est pas correcte. Réessayez :");
                scanner.next();
            }
        }
        return reponse;
    }

    @Override
    public void start(Stage stage) throws Exception {
        ConfigPendu configPendu = displayConfigScreen();

        if(configPendu != null && configPendu.isConfigValid()){
            displayPrincipalScreen(stage,configPendu.getWord(), configPendu.getErrorAuthorized());
        } else{
            // le joueur a annulé sa configuration, ou, config incorrecte
            stage.close();
        }

    }

    private void displayPrincipalScreen(Stage primaryStage,
                                        String word,
                                        int error) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/jeu-pendu.fxml"));
            Parent root = loader.load();
            JeuPendu penduController = loader.getController();
            penduController.setPendu(new Pendu(word,error));

            Scene scene = new Scene(root,400,200);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Jeu du pendu");
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ConfigPendu displayConfigScreen(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/config-pendu.fxml"));
            Parent root = loader.load();

            Stage configStage = new Stage();
            configStage.initModality(Modality.APPLICATION_MODAL);
            configStage.setTitle("Configuration du jeu");
            configStage.setScene(new Scene(root));

            ConfigPendu configPendu = loader.getController();
            configStage.showAndWait();

            return configPendu;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
