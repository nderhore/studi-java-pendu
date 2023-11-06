package fr.studi.game;


import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        this.afficherMotCourant();
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
        System.out.println(" Les lettres utilisées , sont : " + this.lettresUtilisees.toString());
    }

    private boolean estLettreValide(char lettre){
        return Character.isLetter(lettre);
    }

}
