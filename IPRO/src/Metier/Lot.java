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
    public Lot(String ref, int number, double percent, Article comp) {
    	if (percent >= 100) { percent = 100; }
    	this.reference = ref;
    	this.nombre = number;
    	this.pourcent = percent;
    	this.article = comp;
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

    public Article article;

    /**
     * @return
     */
    public double calculerPrix() {
        return this.nombre * (this.article.getPrice() * (1 - (this.pourcent / 100)));
    }

    /**
     * @return
     */
    public String genererNom() {
        return "Lot de " + this.nombre + this.article;
    }

    /**
     * @param ref
     */
    public void setReference(String ref) {
        reference=ref;
    }

    @Override
    public double getPrice() {
        return this.calculerPrix();
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

    
    public int getNum() {
    	return this.nombre;
    }
    
    public double getTaux() {
    	return this.pourcent;
    }
    
    public Article getArticle() {
    	return this.article;
    }

	@Override
	public double getCost() {
		return this.article.getCost() * this.nombre;
	}
	
	@Override
	public String toString() {
		
		return "Lot de " + this.article.getName() + " " + this.reference;
	}
}