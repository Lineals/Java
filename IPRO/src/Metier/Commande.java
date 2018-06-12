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
    private ArrayList<Ligne> articles;

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
    public Boutique Boutique;

    /**
     * 
     */
    public Client Client;

    public Commande(ArrayList<Ligne> articles, double tauxReduc, Client client) {
        this.id = count.getAndIncrement();
        this.date = LocalDateTime.now();
        this.articles = articles;
        this.tauxReduc = tauxReduc;
        this.fraisDePort = 0;
        this.prixTotal = 0;
        this.estFinalisee = false;
        this.Boutique= Controleur.Boutique.getInstance();
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
        return articles;
    }

    public void setArticles(ArrayList<Ligne> articles) {
        this.articles = articles;
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
        for (Ligne art: articles) {
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
}