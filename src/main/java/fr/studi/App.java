package fr.studi;

import fr.studi.game.Pendu;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    private static void initializeGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le mot à trouver : ");
        String mot = scanner.next();
        System.out.println("Entrez le nombre d'erreur maximum : ");
        int erreur = checkInput(scanner);
        Pendu jeuPendu = new Pendu(mot.toLowerCase(),erreur);
        jeuPendu.jouer();
    }

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
        stage.setTitle("Jeu du Pendu");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pendu-gui.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root,300,400);
        stage.setScene(scene);
        stage.show();

        //initializeGame();

    }


}
