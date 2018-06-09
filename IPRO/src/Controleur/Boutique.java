package Controleur;
import Metier.*;
import java.util.*;

/**
 * 
 */
public class Boutique {

    /**
     * Default constructor
     */
    public Boutique() {
    }


    /**
     * 
     */
    private float CoutFonctionnement;

    /**
     * 
     */
    private float CA;

    /**
     * 
     */
    private float Benefice;


    /**
     * 
     */
    public ArrayList<Client> Clients ;



    /**
     * 
     */
    public Stock Stock;

    /**
     * 
     */
    public ArrayList<Commande> Commandes;

    /**
     * 
     */
    public Set<Article> Articles;


    /**
     * @return
     */
    public Collection<Client> AfficherClients() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Collection<Commande> AfficherToutesCommandes() {
        // TODO implement here
        return null;
    }

    /**
     * @param IdClient 
     * @return
     */
    public Collection<Commande> AfficherCommandesClient(Integer IdClient) {
        // TODO implement here
        return null;
    }

}