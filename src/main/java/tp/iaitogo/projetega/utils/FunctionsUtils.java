package tp.iaitogo.projetega.utils;

import tp.iaitogo.projetega.entities.Compte;

import java.util.Random;

public class FunctionsUtils {

    public static final String ALPHANUMERIQUES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateNumeroCompte(Compte compte){
        Random random = new Random();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            builder.append(ALPHANUMERIQUES.charAt(random.nextInt(ALPHANUMERIQUES.length())));
        }

        builder.append(compte.getDateCreation().getYear());

        return builder.toString();
    }
}
