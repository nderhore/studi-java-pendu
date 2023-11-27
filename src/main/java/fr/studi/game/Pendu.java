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

    private boolean gamewin;

    public Pendu(String mot, int erreur){
        this.mot = mot;
        this.erreur = 0;
        this.lettresUtilisees = new StringBuilder();
        this.maxErreur = erreur;
        this.motCourant = new StringBuilder("_".repeat(mot.length()));
        this.gamewin = false;
    }

    public void setLetter(char lettreSaisie){
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
                this.gamewin = true;
                /*ScoreDAO scoreDAO = new ScoreDAOImpl();
                System.out.println("entrez votre pseudo : ");
                String pseudo = scanner.next();
                scoreDAO.sauvegarderScore(new Score(erreur,pseudo));*/
            }
        }


    public  String afficherErreur() {
        return ("Vous avez " + erreur + " erreurs sur " + maxErreur + ".");
    }

    private void mettreAJourMotCourant(char lettreSaisie) {
        for(int i = 0; i < mot.length(); i ++){
            if(mot.charAt(i) == lettreSaisie ){
                motCourant.setCharAt(i,lettreSaisie);
            }
        }
    }

    public String afficherMotCourantAvecVirgule(){

        StringBuilder motAvecVirgule  = new StringBuilder();
        for(int i = 0; i<motCourant.length() ; i++){
            char lettre = this.motCourant.charAt(i);
            motAvecVirgule.append(lettre);

            if (i < motCourant.length() -1){
                motAvecVirgule.append(",");
            }
        }
        return motAvecVirgule.toString();

    }

    public boolean estLettreValide(char lettre){
        return Character.isLetter(lettre) && !lettresUtilisees.toString().contains(String.valueOf(lettre));
    }

    public boolean gameWin() {
        return this.gamewin;
    }
}
