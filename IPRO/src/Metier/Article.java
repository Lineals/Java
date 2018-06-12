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
    public void Article(String nom, String marque, String reference, Double coutObtention, Double prix) {
        this.boutique=Controleur.Boutique.getInstance();
        this.coutObtention=coutObtention;
        this.marque=marque;
        this.nom=nom;
        this.reference=reference;
        this.prix=prix;
    }

    /**
     * Renvoi la référence de l'article
     */
    public String getReference() {
        return this.reference;
    }

    /**
     * Nouvelle reference de l'article
     * @param ref
     */
    public void setReference(String ref) {
        this.reference=ref;
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