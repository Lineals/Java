package Metier;

import Metier.Sellable;

import java.util.*;
import Controleur.*;

/**
 * 
 */
public abstract class Article implements Sellable {

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
    public Article(String nom, String marque, String reference, Double coutObtention, Double prix) {
        this.boutique=Boutique.getInstance();
        this.coutObtention=coutObtention;
        this.marque=marque;
        this.nom=nom;
        this.reference=reference;
        this.prix=prix;
    }

    /**
     * Nouvelle reference de l'article
     * @param ref
     */
    public void setReference(String ref) {
        this.reference=ref;
    }

    /**
     * Nouveau nom de l'article
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Nouvelle marque de l'article
     * @param marque
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }
    

    /**
     * Renvoi le co�t d'obtention de l'article
     */
    public double getCost() {
    	return this.coutObtention;
    }
    
    /**
     * Nouveau coût d'obtention de l'article
     * @param coutObtention
     */
    public void setCoutObtention(double coutObtention) {
        this.coutObtention = coutObtention;
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
        return marque;
    }
}