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
     * Code de référence de l'article
     */
    private String reference;

    /**
     * Nom de l'article
     */
    private String nom;

    /**
     * Marque de l'article
     */
    private String marque;

    /**
     * Prix de l'article
     */
    public double prix;

    /**
     * Cout d'obtention de l'article
     */
    private double coutObtention;

    /**
     * Boutique à laquelle appartient l'article
     */
    public Boutique boutique;
    /**
     * Boutique à laquelle appartient l'article
     */

    /**
     * Constructeur par défaut de l'article
     */
    public void Article() {
        // TODO implement here
    }

    /**
     * Renvoi la référence de l'article
     */
    public void getReference() {
        // TODO implement here
    }

    /**
     * Nouvelle reference de l'article
     * @param ref
     */
    public void setReference(String ref) {
        // TODO implement here
    }
    /**
     * Renvoi le nom de l'article
     * @return nom
     */
    public String getNom() {
        return nom;
    }
    /**
     * Nouveau nom de l'article
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Renvoi la marque de l'article
     * @return marque
     */
    public String getMarque() {
        return marque;
    }

    /**
     * Nouvelle marque de l'article
     * @param marque
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * Renvoi le coût d'obtention del'article
     * @return coutObtention
     */
    public double getCoutObtention() {
        return coutObtention;
    }

    /**
     * Nouveau coût d'obtention de l'article
     * @param coutObtention
     */
    public void setCoutObtention(double coutObtention) {
        this.coutObtention = coutObtention;
    }
}