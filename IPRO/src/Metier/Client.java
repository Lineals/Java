package Metier;

import java.util.*;
import Controleur.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 */
public class Client {

    /**
     * Auto increment pour générer un id unique a chaque client
     */
    public static AtomicInteger count = null;

    /**
     * Id du client
     */
    private Integer id;

    /**
     * Nom du client
     */
    private String nom;

    /**
     * Prenom du client
     */
    private String prenom;

    /**
     * Adresse du client
     */
    private String adresse;

    /**
     * Liste des commandes passés par le client
     */
    private ArrayList<Commande> commandes;
    /**
     * Liste des factures du client client
     */
    private ArrayList<Facture> factures;


    /**
     * Default constructor
     */
    public Client(String nom, String prenom,String adresse) {
    	if (count == null) {
    		count = new AtomicInteger(Boutique.getInstance().getHighestCommandId());
    	}
        this.id = count.incrementAndGet();
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.commandes= new ArrayList<>();
        this.factures= new ArrayList<>();
    }
    
    public Client(String nom, String prenom,String adresse, int id) {
        this.id = id;
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.commandes= new ArrayList<>();
        this.factures= new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public ArrayList<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(ArrayList<Commande> commandes) {
        this.commandes = commandes;
    }

    public ArrayList<Facture> getFactures() {
        return factures;
    }

    public void setFactures(ArrayList<Facture> factures) {
        this.factures = factures;
    }

    @Override
    public String toString() {
        return nom + " "+  prenom;
    }
}