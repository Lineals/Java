package Metier;

import Metier.Sellable;

import java.util.*;

/**
 * 
 */
public class Lot implements Sellable {

    /**
     * Default constructor
     */
    public Lot() {
    }

    /**
     * 
     */
    private String nom;

    /**
     * 
     */
    private Float prix;

    /**
     * 
     */
    private String reference;

    /**
     * 
     */
    private Integer nombre;

    /**
     * 
     */
    private double pourcent;

    /**
     * 
     */

    public Article Articles;

    /**
     * 
     */
    public void Lot() {
        // TODO implement here
    }

    /**
     * @return
     */
    public double calculerPrix() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @return
     */
    public String genererNom() {
        // TODO implement here
        return "";
    }

    /**
     *
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param ref
     */
    public void setReference(String ref) {
        reference=ref;
    }

    @Override
    public double getPrice() {
        return prix;
    }

    @Override
    public String getRef() {
        return reference;
    }

    @Override
    public String getName() {
        return nom;
    }

    @Override
    public String getBrand() {
        return null;
    }
}