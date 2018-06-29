package Controleur;

import Metier.*;
import Dao.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Controleur {
	
	/**
	 * String liste de client séparé par des sauts de lignes
	 * @return String
	 */
    public static String afficherClients() {
        String res="";
        for (Client cl: Boutique.getInstance().clients) {

            res+=cl.toString()+"\n";
        }
        return res;
    }
    
    /**
	 * String liste de commandes séparé par des sauts de lignes
	 * @return String
	 */
    public static String afficherCommandes(){
        String res="";
        for (Commande com : Boutique.getInstance().commandes){
            res+=com.toString()+"\n";
        }
        return res;
    }
    
    /**
	 * String liste des articles ainsi que la quantité en stock
	 * @return String
	 */
    public static String afficherStock(){
        String res = "";
        HashMap<Sellable,Integer> stock= Boutique.getInstance().stock;
        Iterator it =  stock.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            res+=(pair.getKey()+" = "+pair.getValue());
            it.remove();
        }
        return res;
    }
    
    /**
	 * String liste de commandes d'un client donné en parametre
	 * @param idClient Id du client 
	 * @return String
	 */
    public static String afficherCommandesClient(int idClient){
        String res="";
        Client cli = getClientById(idClient);
        for(Commande com : cli.getCommandes()){
            res+=com.toString()+"\n";
        }
        return res;
    }
    
    /**
     * Retourne le client avec l'id en param
     * @param idClient integer, id du client
     * @return Client ou null
     */
    public static Client getClientById(int idClient){
    	Client client = null;
        for(Client cl : Boutique.getInstance().clients){
            if(cl.getId()==idClient){
                client = cl;
            }
        }
        return client;
    }
    
    /**
     * Retourne la commande avec l'id
     * @param idCommande id de la commande
     * @return Commande ou null
     */
    public static Commande getCommandeById(int idCommande){
    	Commande commande = null;
        for (Commande com : Boutique.getInstance().commandes){
            if(com.getId()==idCommande){
                commande = com;
            }
        }
        return commande;
    }
    
    /**
     * Retourne la liste des commandes d'un client
     * @param name String, nom du client
     * @return Liste commande
     */
    public static ArrayList<Commande> getCommandeByClientName(String name){
    	ArrayList<Commande> commande = new ArrayList<>();
        for (Commande com : Boutique.getInstance().commandes){
            if(com.getClient().getNom().equals(name)){
                commande.add(com);
            }
        }
        return commande;
    }
    
    /**
     * retourne le sellable associé à l'id passé en paramêtre
     * @param ref String, ref du sellable
     * @return sellable ou null
     */
    public static Sellable getArticleByReference(String ref){
    	Sellable article = null;
        for(Sellable art : Boutique.getInstance().stock.keySet()){
            if(art.getRef().equals(ref)){
                article = art;
            }
        }
        return article;
    }
    
    /**
     * retourne une liste de client avec le nom passé en paramêtre
     * @param string, nom du client
     * @return ArrayList<Client>
     */
    public static ArrayList<Client> getClientByName(String string){
    	ArrayList<Client> clients = new ArrayList<>();
    	for(Client cl : Boutique.getInstance().clients){
            if(cl.getNom().equals(string)){
                clients.add(cl);
            }
        }
    	return clients;	
    }
    
    /**
     * Supprime un client de la boutique et de la BDD
     * @param client client à supprimer
     */
    public static void deleteClient(Client client) {
    	ClientDAO clientDAO = new ClientDAO(DbConnector.getDbConnector());
    	Boutique.getInstance().clients.remove(client);
    	clientDAO.delete(client);
    }
    
    /**
     * Supprime une commande de la boutique
     * @param commande, commmande a supprimer
     */
    public static void deleteCommande(Commande commande) {
    	CommandeDAO commandeDAO = new CommandeDAO(DbConnector.getDbConnector());
    	Boutique.getInstance().commandes.remove(commande);
    	commandeDAO.delete(commande);
    }
    
    
}
