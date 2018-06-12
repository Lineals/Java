package Metier;

import java.util.*;

/**
 * 
 */
public class Ligne {

    /**
     * Default constructor
     */
    public Ligne() {
    }

    /**
     * 
     */
    private Integer quantite;

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
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}