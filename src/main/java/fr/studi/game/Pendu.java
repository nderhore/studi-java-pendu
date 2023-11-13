package fr.studi.game;


import fr.studi.dao.ScoreDAO;
import fr.studi.dao.impl.ScoreDAOImpl;
import fr.studi.pojo.Score;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class Pendu {

    // mot à trouver
    private String mot;

    //mot actuel
    private StringBuilder motCourant;

    // nombre max erreur
    private int maxErreur;

    //nombre erreur courante
    private int erreur;

    // lettre qui ont été saisies
    private StringBuilder lettresUtilisees;

    public Pendu(String mot, int erreur){
        this.mot = mot;
        this.erreur = 0;
        this.lettresUtilisees = new StringBuilder();
        this.maxErreur = erreur;
        this.motCourant = new StringBuilder("_".repeat(mot.length()));
    }

    public void jouer(){
        Scanner scanner = new Scanner(System.in);

        // si mon nombre d'erreur actuel est inférieur au nbr max
        // je peux jouer
        while(erreur < maxErreur){

            //rappel des précédentes information
            afficherMotCourant();
            afficherLettresUtilisees();
            afficherErreur();

            System.out.println("Entrez une lettre : ");
            char lettreSaisie = scanner.next().toLowerCase()
                    .charAt(0);

            //verification de la légitimité de la lettre
            if(estLettreValide(lettreSaisie) && !lettresUtilisees.toString().contains(String.valueOf(lettreSaisie))){
                lettresUtilisees.append(lettreSaisie);
                if(mot.contains(String.valueOf(lettreSaisie))){
                    mettreAJourMotCourant(lettreSaisie);
                } else {
                    erreur++; // erreur = erreur + 1;
                }
            }else {
                System.out.println("Lettre invalide ou déjà utilisée. Réessayez.");
            }

            //affichage d'une phrase si j'ai trouvé le mot
            if (motCourant.toString().equals(mot)){
                afficherMotCourant();
                System.out.println("Bravo, vous avez gagné !");
                //sauvegarde du score
                ScoreDAO scoreDAO = new ScoreDAOImpl();
                System.out.println("entrez votre pseudo : ");
                String pseudo = scanner.next();
                scoreDAO.sauvegarderScore(new Score(erreur,pseudo));
                System.exit(0);
            }
        }

            // affichage d'une phrase indiquant que j'ai perdu
            System.out.println("Désolé, vous avez perdu, le mot etait : " + mot);
            scanner.close();
    }

    private void afficherErreur() {
        System.out.println("Vous avez " + erreur + " erreurs sur " + maxErreur + ".");
    }

    private void mettreAJourMotCourant(char lettreSaisie) {
        for(int i = 0; i < mot.length(); i ++){
            if(mot.charAt(i) == lettreSaisie ){
                motCourant.setCharAt(i,lettreSaisie);
            }
        }
    }

    private void afficherMotCourant(){

        //en une ligne
        /*System.out.println("Le mot actuel est : " + IntStream.range(0,motCourant.length())
                .mapToObj(index -> motCourant.charAt(index) + ( index < motCourant.length() - 1 ? " ," : ""))
                .collect(Collectors.joining()));*/

        //en plusieurs ligne
        StringBuilder motAvecVirgule  = new StringBuilder();
        for(int i = 0; i<motCourant.length() ; i++){
            char lettre = this.motCourant.charAt(i);
            motAvecVirgule.append(lettre);

            if (i < motCourant.length() -1){
                motAvecVirgule.append(",");
            }
        }
        System.out.println("Le mot actuel est : " + motAvecVirgule);

    }

    private void afficherLettresUtilisees(){
        System.out.println("Les lettres déjà utilisées , sont : " + this.lettresUtilisees.toString());
    }

    private boolean estLettreValide(char lettre){
        return Character.isLetter(lettre);
    }

}
