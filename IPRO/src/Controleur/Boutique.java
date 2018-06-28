package Controleur;
import Metier.*;
import Dao.*;

import java.sql.Connection;
import java.util.*;

/**
 * 
 */
public class Boutique {
    /** Instance unique pré-initialisée */
    private static Boutique INSTANCE = new Boutique();
    /**
     * 
     */
    private float coutFonctionnement;

    /**
     * 
     */
    private float CA;

    /**
     *
     */
    private float benefice;


    /**
     *
     */
    public ArrayList<Client> clients ;


    /**
     * 
     */

    public HashMap<Sellable,Integer> stock;


    /**
     * 
     */
    public ArrayList<Commande> commandes;

    private Boutique(){
        this.stock=new HashMap<>();
        this.benefice=0;
        this.CA=0;
        this.coutFonctionnement=0;
        this.clients=new ArrayList<>();
        this.commandes=new ArrayList<>();
        this.feedAll();
    }
    public static Boutique getInstance(){return INSTANCE;}

    public void modifierStock(Sellable article,int delta){
        if(this.stock.containsKey(article)){
            this.stock.put(article,this.stock.get(article)+delta);
        }
    }

    /**
     * permet d'ajouter un article ou de définir le nombre d'occurences d'un article déjà existant
     * @param article l'article
     * @param quantite la quantite
     */
    public void ajouterArticle(Sellable article, int quantite){
        this.stock.put(article,quantite);
    }
    public void ajouterClient(Client client){
        if(!this.clients.contains(client)){
            this.clients.add(client);
        }
    }
    public ArrayList<Client> getClients(){
        return clients;
    }

    public HashMap<Sellable, Integer> getStock() {
        return stock;
    }

    public ArrayList<Commande> getCommandes() {
        return commandes;
    }

    public void ajouterCommande(Commande commande){
        if(!this.commandes.contains(commande)){
            this.commandes.add(commande);
        }
    }

    public float getCoutFonctionnement() {
        return coutFonctionnement;
    }

    public void setCoutFonctionnement(float coutFonctionnement) {
        this.coutFonctionnement = coutFonctionnement;
    }

    public float getCA() {
        return CA;
    }

    public void setCA(float CA) {
        this.CA = CA;
    }

    public float getBenefice() {
        return this.CA-this.coutFonctionnement;
    }
    
    public void feedAll() {
    	Connection connection = DbConnector.getDbConnector();
    	this.clients = new ClientDAO(connection).findAll();
    	this.commandes = new CommandeDAO(connection).findAll();
    }


    @Override
    public String toString() {
        return "Bénéfice "+INSTANCE.getBenefice()+"\nChiffre d'affaires "+INSTANCE.getCA()+"\nCount fonctionnnement "+INSTANCE.getCoutFonctionnement();
    }
}