package Controleur;
import Metier.*;
import javafx.scene.control.Control;
import Dao.*;

import java.sql.Connection;
import java.util.*;

/**
 * 
 */
public class Boutique {
    private static Boutique INSTANCE = null;
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
    }
    public static Boutique getInstance(){
    	if(INSTANCE == null) {
    		INSTANCE = new Boutique();
    	}
    	return INSTANCE;
	}

    public void modifierStock(Sellable article,int delta){
        if(this.stock.containsKey(article)){
            this.stock.put(article,this.stock.get(article)+delta);
        }
    }
    public void retirerStock(Sellable article){
        if(this.stock.containsKey(article)){
            this.stock.remove(article);
        }
    }

    /**
     * permet d'ajouter un article ou de définir le nombre d'occurences d'un article déjà existant
     * @param article l'article
     * @param quantite la quantite
     */
    public void ajouterArticle(Sellable article, int quantite){
        this.stock.put(article,quantite);
/*
        new StockDAO(DbConnector.getDbConnector()).create(article, quantite);
*/
    }
    public void ajouterClient(Client client){
    	ClientDAO clientDAO = new ClientDAO(DbConnector.getDbConnector());
        if(!this.clients.contains(client)){
            this.clients.add(client);
            clientDAO.create(client);
        } else {
        	this.clients.remove(Controleur.getClientById(client.getId()));
        	this.clients.add(client);
        	clientDAO.update(client);
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
    
    public int getHighestCommandId() {
    	int value = 0;
    	for (Commande commande: this.commandes) if(commande.getId() > value) value = commande.getId();
    	return value;
    }
    
    public int getHighestClientId() {
    	int value = 0;
    	for (Client client: this.clients) if(client.getId() > value) value = client.getId();
    	return value;
    }

    public void ajouterCommande(Commande commande){
        if(!this.commandes.contains(commande)){
            this.commandes.add(commande);
            new CommandeDAO(DbConnector.getDbConnector()).create(commande);
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
    	ArrayList<Sellable> arrayList = new ArticleDAO(connection).findAll();
    	for (Sellable sellable: arrayList) {
    		this.stock.put(sellable, 0);
    	}
    	arrayList = new LotDAO(connection).findAll();
    	System.out.println(this.stock);
		for (Sellable sellable: arrayList) {
			this.stock.put(sellable, 0);
		}
    	this.stock = new StockDAO(connection).updateStock(this.stock);
    	this.commandes = new CommandeDAO(connection).findAll();
    }


    @Override
    public String toString() {
        return "Bénéfice "+INSTANCE.getBenefice()+"\nChiffre d'affaires "+INSTANCE.getCA()+"\nCount fonctionnnement "+INSTANCE.getCoutFonctionnement();
    }
}