package Metier;

import java.util.*;

import org.junit.platform.commons.util.ToStringBuilder;

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
    	this.color = color;
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

}