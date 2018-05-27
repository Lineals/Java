package factures;

import stock.Lot;
import stock.Papier;
import stock.Stylo;
import stock.Stylo.Couleur;

public class FactureTest {
	public static void main(String[] args) {
		Stylo s1 = new Stylo("s1", "Stylo Jade", "WaterTruc", 50, Couleur.BLEU);
		Papier r1 = new Papier("r1", "Ramette haute qualité", "Flouefontaine", 9.5, 80);
		Stylo s2 = new Stylo("s2", "Stylo or", "WaterTruc", 100, Couleur.ROUGE);
		Lot l1 = new Lot("l1", 5, 10, s2);
		
		Facture f1 = new Facture("ENSIIE", "10 Avril 2018");
		f1.newEntry(10, s1);
		f1.newEntry(15, r1);
		f1.newEntry(2, l1);
		
		System.out.println(f1);
	}
}
