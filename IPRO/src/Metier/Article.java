package Metier;

import Metier.Sellable;

import java.util.*;
import Controleur.*;

/**
 * 
 */
public abstract class Article implements Sellable {

    /**
     * Default constructor
     */
    public Article() {
    }

    /**
     * 
     */
    private String reference;

    /**
     * 
     */
    private String nom;

    /**
     * 
     */
    private String marque;

    /**
     * 
     */
    public double prix;

    /**
     * 
     */
    private double coutObtention;

    /**
     * 
     */
    public Lot Lot;

    /**
     * 
     */
    public Boutique boutique;

    /**
     * 
     */
    public void Article() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getReference() {
        // TODO implement here
    }

    /**
     * @param ref
     */
    public void setReference(String ref) {
        // TODO implement here
    }

}