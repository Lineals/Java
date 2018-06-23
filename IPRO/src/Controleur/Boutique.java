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
        this.stock=new HashMap<>();
        this.benefice=0;
        this.CA=0;
        this.coutFonctionnement=0;
        this.clients=new ArrayList<>();
        this.commandes=new ArrayList<>();
    }
    public static Boutique getInstance(){return INSTANCE;}

    public void modifierStock(Article article,int delta){
        if(this.stock.containsKey(article)){
            this.stock.put(article,this.stock.get(article)+delta);
        }
    }

    /**
     * permet d'ajouter un article ou de définir le nombre d'occurences d'un article déjà existant
     * @param article l'article
     * @param quantite la quantite
     */
    public void ajouterArticle(Article article, int quantite){
        this.stock.put(article,quantite);
    }
    public void ajouterClient(Client client){
        if(!this.clients.contains(client)){
            this.clients.add(client);
        }
    }
    public void ajouterCommande(Commande commande){
        if(this.commandes.contains(commande)){
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


    @Override
    public String toString() {
        return "Bénéfice "+INSTANCE.getBenefice()+"\nChiffre d'affaires "+INSTANCE.getCA()+"\nCount fonctionnnement "+INSTANCE.getCoutFonctionnement();
    }
}