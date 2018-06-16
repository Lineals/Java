package Metier;

import java.util.*;

/**
 * 
 */
public interface Sellable {

    /**
     * Renvoi le prix de l'article
     */
    double getPrice();

    /**
     * Renvoi la référence de l'article
     */
    String getRef();

    /**
     * Renvoi le nom de l'article
     */
    String getName();

    /**
     * Renvoi la marque de l'article
     */
    String getBrand();

}