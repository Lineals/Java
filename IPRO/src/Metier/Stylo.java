package Metier;

import java.util.*;

/**
 * 
 */
public class Stylo extends Article {
	public enum Couleur {ROUGE, VERT, BLEU, NOIR};

    /**
     * Default constructor
     */
    public Stylo(String nom, String marque, String reference, double coutObtention, double prix, Couleur couleur) {
    	super(nom, marque, reference, coutObtention, prix);
    	this.color = couleur;
    }
    

    /**
     * 
     */
    private Couleur color;

    /**
     * 
     */
    
    public Couleur getColor() {
		return this.color;
	}
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "Stylo " + this.color; 
    }

}