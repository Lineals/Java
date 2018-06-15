package Controleur;
import Metier.*;
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

    public HashMap<Article,Integer> stock;


    /**
     * 
     */
    public ArrayList<Commande> commandes;

    private Boutique(){
        this.stock=new HashMap<Article,Integer>();
        this.benefice=0;
        this.CA=0;
        this.coutFonctionnement=0;
        this.clients=new ArrayList<Client>();
        this.commandes=new ArrayList<Commande>();
    }
    public static Boutique getInstance(){return INSTANCE;}
    /**
     * @return
     */
    public String AfficherClients() {
        String res="";
        for (Client cl:
             clients) {

            res+="\n"+ cl.toString();
        }
        return res;
    }

    /**
     * @return
     */
    public Collection<Commande> AfficherCommandes() {
        return this.commandes;
    }

    /**
     * @param IdClient 
     * @return
     */
    public Collection<Commande> AfficherCommandesClient(Integer IdClient) {
        return this.commandes;
    }
    public void modifierStock(Article article,int delta){
        if(this.stock.containsKey(article)){
            this.stock.put(article,this.stock.get(article)+delta);
        }

    }

    /**
     * permet d'ajouter un article ou de définir le nombre d'occurences d'un article déjà existant
     * @param article
     * @param quantite
     */
    public void ajouterArticle(Article article, int quantite){
        this.stock.put(article,quantite);
    }
    public void ajouterClient(Client client){
        if(!this.clients.contains(client)){
            this.clients.add(client);
        }
    }
    public Client getClient(int id){
        for (Client cl:
             clients) {
            if(cl.getId()==id){
                return cl;
            }

        }
        return null;
    }
    public void ajouterCommande(Commande commande){
        if(this.commandes.contains(commande)){
            this.commandes.add(commande);
        }
    }

}