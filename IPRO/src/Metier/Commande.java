package Metier;

import java.time.LocalDateTime;
import java.util.*;
import java.util.PrimitiveIterator.OfDouble;
import java.util.concurrent.atomic.AtomicInteger;

import Controleur.Boutique;

/**
 * 
 */
public class Commande {


    /**
     * 
     */
    public static AtomicInteger count = null;

    /**
     * 
     */
    private final Integer id;

    /**
     * 
     */
    private final LocalDateTime date;

    /**
     * 
     */
    private ArrayList<Ligne> lignes;

    /**
     * 
     */
    private double tauxReduc;

    /**
     * 
     */
    private double fraisDePort;

    /**
     * 
     */
    private double prixTotal;

    /**
     * 
     */
    private Boolean estFinalisee;

    /**
     * 
     */
    public Client client;

    public Commande(Client client, Integer id, LocalDateTime time,
    				float reduc, float prix, boolean isend) {
    	this.id = id;
    	this.date = time;
    	this.tauxReduc = reduc;
    	this.fraisDePort = prix;
    	this.estFinalisee = isend;
    	this.client = client;
    	this.lignes= new ArrayList<>();
    }


    public Commande(Client client, 	float reduc, float fpd) {
    	if (count == null) {
    		count = new AtomicInteger(Boutique.getInstance().getHighestCommandId());
    	}
        this.id = count.incrementAndGet();
        this.date = LocalDateTime.now();
        this.tauxReduc = reduc;
        this.fraisDePort = fpd;
        this.estFinalisee = false;
        this.client = client;
        this.lignes=new ArrayList<>();
    }


    /**
     * @return
     */
    public double calculerPrix() {
        double prix = 0;
        for( Ligne ligne : lignes)
        {
            prix+=ligne.prix;
        }
        prix-=prix*tauxReduc;
        prix+=fraisDePort;
        return prix;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public ArrayList<Ligne> getArticles() {
        return lignes;
    }

    public void setArticles(ArrayList<Ligne> lignes) {
        this.lignes = lignes;
    }

    public void setFraisDePort(double FraisDePorts){
        fraisDePort=fraisDePort;
    }

    public double getTauxReduc() {
        return tauxReduc;
    }

    public void setTauxReduc(double tauxReduc) {
        this.tauxReduc = tauxReduc;
    }

    public double getFraisDePort() {
        return fraisDePort;
    }

    public double getPrixTotal() {
        int prixT=0;
        if (!lignes.isEmpty()){
            for (Ligne art: lignes) {
                prixT+=art.getPrix();
            }
        }

        return prixT-(prixT*(tauxReduc/100))+fraisDePort;
    }


    public Boolean getEstFinalisee() {
        return estFinalisee;
    }

    public void setEstFinalisee(Boolean estFinalisee) {
        this.estFinalisee = estFinalisee;
    }
    
    public void addSellable(Sellable sell, int Qte) {
    	this.lignes.add(new Ligne(Qte, sell));
    }

    public void removeLigne(Ligne ligne){
        this.lignes.remove(ligne);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    @Override
    public String toString() {
        return client + " : " + prixTotal + " €";
    }

    public class Ligne {
        /**
         * Default constructor
         */
        public Ligne(int qte, Sellable sale) {
        	this.quantite = qte;
        	this.article = sale;
        	this.prix = 0;
        }

        /**
         * 
         */
        private Integer quantite;

        
        private Sellable article;
        /**
         * 
         */
        private double prix;

        public Integer getQuantite() {
            return quantite;
        }

        public void setQuantite(Integer quantite) {
            this.quantite = quantite;
        }

        public double getPrix() {
            return this.quantite * this.article.getPrice();
        }
        
        public Sellable getSell() {
        	return this.article;
        }
        public Sellable getArticle() {
            return this.article;
        }

        @Override
        public String toString() {
            return article +    "x" + quantite ;
        }
    }
}