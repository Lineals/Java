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
    public void Facture(Commande commande) {
        this.id = count.getAndIncrement();
        this.date= LocalDateTime.now();
        this.commande=commande;
        this.client=commande.getClient();
        this.prix = new Pair<>(commande.getPrixTotal(),commande.getPrixTotal()*1.20);
    }

    /**
     * @return
     */
    public String genererFacture() {
        String res;
        res="id: "+this.id+" date "+this.date+" id commande: "+this.commande.getId()+" id client "+this.client.getId()+
                "\n Prix HT: "+this.prix.getKey()+" prix TTC: "+this.prix.getValue();
        return res;
    }

}