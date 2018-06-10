package Metier;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import Controleur.*;

/**
 * 
 */
public class Commande {

    /**
     * Default constructor
     */
    public Commande() {
    }

    /**
     * 
     */
    public static AtomicInteger count;

    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Date date;

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


    /**
     * @return
     */
    public double calculerPrix() {
        // TODO implement here
        return 0.0d;
    }

}