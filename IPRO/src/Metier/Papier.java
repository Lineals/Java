package Metier;

import Metier.Article;

import java.util.*;

/**
 * 
 */
public class Papier extends Article {

    /**
     * Default constructor
     */
    public Papier(String nom, String marque, String reference, double coutObtention, double prix, double poids) {
    	super(nom, marque, reference, coutObtention, prix);
    	this.poids = poids;
    }

    /**
     * 
     */
    private Double poids;
    
    public double getPoids() {
		return this.poids;
	}
    
    public String toString() {
    	return "Papier " + this.getName() + " " + this .getPoids() + " " + this.getRef();
    }

}