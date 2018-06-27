package Metier;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import Controleur.*;

/**
 * 
 */
public class Commande {


    /**
     * 
     */
    public static final AtomicInteger count= new AtomicInteger(0);

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
    public Client Client;
    
    public Commande(Client client, Integer id, LocalDateTime time, 
    				float reduc, float prix, boolean isend) {
    	this.id = id;
    	this.date = time;
    	this.tauxReduc = reduc;
    	this.fraisDePort = prix;
    	this.estFinalisee = isend;
    	this.Client = client;
    }
    

    public Commande(Client client) {
        this.id = count.getAndIncrement();
        this.date = LocalDateTime.now();
        this.lignes = new ArrayList<Ligne>();
        this.tauxReduc = 0;
        this.fraisDePort = 0;
        this.estFinalisee = false;
        this.Client=client;
    }

    /**
     * @return
     */
    public double calculerPrix() {
        // TODO implement here
        return 0.0d;
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
        for (Ligne art: lignes) {
            prixT+=art.getPrix();
        }
        return prixT-(prixT*tauxReduc)+getFraisDePort();
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

    public Client getClient() {
        return Client;
    }

    public void setClient(Client client) {
        Client = client;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", date=" + date +
                ", lignes=" + lignes +
                ", tauxReduc=" + tauxReduc +
                ", fraisDePort=" + fraisDePort +
                ", prixTotal=" + prixTotal +
                ", estFinalisee=" + estFinalisee +
                ", Client=" + Client +
                '}';
    }

    private class Ligne {
        /**
         * Default constructor
         */
        public Ligne(int qte, Sellable sale) {
        	this.quantite = qte;
        	this.article = sale;
        	this.prix = this.quantite * this.article.getPrice();
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
            return this.prix;
        }

        @Override
        public String toString() {
            return "Ligne{" +
                    "quantite=" + quantite +
                    ", article=" + article +
                    ", prix=" + prix +
                    '}';
        }
    }
}