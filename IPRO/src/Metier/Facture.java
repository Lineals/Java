package Metier;

import javafx.util.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 */
public class Facture {
	
	public static final double TVA = 1.20;

    /**
     * 
     */
    public static AtomicInteger count = new AtomicInteger(0);

    /**
     *
     */
    public Commande commande;

    /**
     *
     */
    public Client client;

    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private LocalDateTime date;

    /**
     * 
     */
    private Pair<Double,Double> prix;

    /**
     * 
     */
    public Facture(Commande commande) {
        this.id = count.getAndIncrement();
        this.date= LocalDateTime.now();
        this.commande=commande;
        this.client=commande.getClient();
        this.prix = new Pair<>(commande.getPrixTotal(),commande.getPrixTotal() * TVA);
    }

    /**
     * @return
     */
    public String genererFacture() {
    	StringBuilder strBuild = new StringBuilder();
    	strBuild.append("ID: " + this.id);
        strBuild.append("\nDate: " + this.date);
        strBuild.append("\nID commande: " + this.commande.getId());
        strBuild.append("\nID client: " + this.client);
        strBuild.append("\nPrix HT: " + this.prix.getKey());
        strBuild.append("\nPrix TTC: " + this.prix.getValue());
        return strBuild.toString();
    }

}