package Controleur;

import Metier.Client;

public class DemoBoutique {
    public static void main(String[] args) {
        Boutique maboutique = Boutique.getInstance();
        maboutique.ajouterClient(new Client("lineal","remy","1 avenue de la resistance"));
        maboutique.ajouterClient(new Client("toast","theo","1 rue de la resistance"));
        maboutique.ajouterClient(new Client("oropo","emilio","1 square de la resistance"));
        System.out.println(maboutique.AfficherClients());
        Client lin = maboutique.getClient(1);
        System.out.println(lin.toString());

    }
}
