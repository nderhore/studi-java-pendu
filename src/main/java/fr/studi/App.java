package fr.studi;

import fr.studi.game.Pendu;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Bienvenu dans le jeu du pendu !" );
        initializeGame();

        // un mot à trouver, nombre erreur
    }

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

}
