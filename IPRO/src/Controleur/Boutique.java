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
    /**
     * Attribut privé pour le singleton
     * 
     */
    private static Boutique INSTANCE = null;
    
    /**
     * Coût de fonctionnement de la voutique
     * 
     */
    private float coutFonctionnement;

    /**
     * Chiffre affaire
     */
    private float CA;

    /**
     * Benefice
     */
    private float benefice;


    /**
     * Liste de clients
     */
    public ArrayList<Client> clients ;


    /**
     * Stock de la boutique, lie un sellable avec une quantité
     */

    public HashMap<Sellable,Integer> stock;


    /**
     * Liste de commandes
     */
    public ArrayList<Commande> commandes;

    /**
     * Initiliase une nouvelle boutique
     */
    private Boutique(){
        this.stock=new HashMap<>();
        this.benefice=0;
        this.CA=0;
        this.coutFonctionnement=0;
        this.clients=new ArrayList<>();
        this.commandes=new ArrayList<>();
    }
    
    /**
     * Singleton : Retourne l'instance et la crée si elle n'existe pas;
     * @return Boutique object
     */
    public static Boutique getInstance(){
    	if(INSTANCE == null) {
    		INSTANCE = new Boutique();
    	}
    	return INSTANCE;
	}

    /**
     * Modifie la quantité d'un sellable dans le hashmap stock
     * @param article 
     * @param delta Quantité à ajouter ou retirer
     */
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
        if(Controleur.getArticleByReference(article.getRef())!=null){
            this.stock.put(article,quantite);
            if(article instanceof Article){
                new ArticleDAO(DbConnector.getDbConnector()).update(article);
            }
            else if(article instanceof Lot){
                new LotDAO(DbConnector.getDbConnector()).update(article);
            }
            new StockDAO(DbConnector.getDbConnector()).update(article, quantite);

        }
        else{


            this.stock.put(article,quantite);
            if(article instanceof Article){
                new ArticleDAO(DbConnector.getDbConnector()).create(article);
            }
            else if(article instanceof Lot){
                new LotDAO(DbConnector.getDbConnector()).create(article);
            }
            new StockDAO(DbConnector.getDbConnector()).create(article, quantite);
        }
    }
    
    /**
     * Ajoute un nouveau client, si le client existe déjà, le modifie
     * @param client Client à ajouter / modifier
     */
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
    /**
     * getter liste clients
     * @return ArrayList<Client>
     */
    public ArrayList<Client> getClients(){
        return clients;
    }
    
    
    /**
     * Getter hashmap stock
     * @return HashMap<Sellable, Integer>
     */
    public HashMap<Sellable, Integer> getStock() {
        return stock;
    }

    /**
     * Getter liste commandes
     * @return ArrayList<Commande>
     */
    public ArrayList<Commande> getCommandes() {
        return commandes;
    }
    
    /**
     * Retourne l'id de commande le plus élevé dans l'arraylist commandes
     * @return int
     */
    public int getHighestCommandId() {
    	int value = 0;
    	for (Commande commande: this.commandes) if(commande.getId() > value) value = commande.getId();
    	return value;
    }
    
    /**
     * Retourne l'id de commande le plus élevé dans l'arraylist clients
     * @return int
     */
    public int getHighestClientId() {
    	int value = 0;
    	for (Client client: this.clients) if(client.getId() > value) value = client.getId();
    	return value;
    }

    /**
     * Ajoute une nouvelle commande dans la boutique
     * Effectue aussi une requête d'ajout vers la base de donnée
     * @param commande Commande à ajouter
     */
    public void ajouterCommande(Commande commande){
        if(!this.commandes.contains(commande)){
            this.commandes.add(commande);
            new CommandeDAO(DbConnector.getDbConnector()).create(commande);
        }
    }
    
    /**
     * Getter Cout de fonctionnement
     * @return float coutFonctionnement
     */
    public float getCoutFonctionnement() {
        return coutFonctionnement;
    }

    /**
     * Set cout fonctionnement
     * @param coutFonctionnement Cout de fonctionnement
     */
    public void setCoutFonctionnement(float coutFonctionnement) {
        this.coutFonctionnement = coutFonctionnement;
    }

    /**
     * get chiffre affaire
     * @return CA chiffre affaire
     */
    public float getCA() {
        return CA;
    }
    
    /**
     * set chiffre affaire
     * @param CA
     */
    public void setCA(float CA) {
        this.CA = CA;
    }
    /**
     * get benefice (chiffre affaire - coût fonctionnement)
     * @return benefice
     */
    public float getBenefice() {
        return this.CA-this.coutFonctionnement;
    }
    
    /**
     * Recupère les données de la base et rempli les attributs de Boutique
     */
    public void feedAll() {
    	Connection connection = DbConnector.getDbConnector();
    	this.clients = new ClientDAO(connection).findAll();
    	ArrayList<Sellable> arrayList = new ArticleDAO(connection).findAll();
    	for (Sellable sellable: arrayList) {
    		this.stock.put(sellable, 0);
    	}
    	arrayList = new LotDAO(connection).findAll();
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