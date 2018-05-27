package factures;

import java.util.ArrayList;

import stock.Article;
import stock.Lot;
import stock.Saleable;

public class Facture {
	private static int facture_id = 1;
	private int facture_id_local;
	private String client;
	private String date;
	private double price;
	
	private ArrayList<Ligne> liste_produit = new ArrayList<Ligne>();
	
	public Facture(String client, String date) {
		this.client = client;
		this.date = date;
		this.facture_id_local = facture_id;
		facture_id++;
	}
	
	public void newEntry(int qte, Saleable sale) {
		Ligne entry = new Ligne(qte, sale);
		liste_produit.add(entry);
		this.calcPrice();
	}
	
	public void calcPrice() {
		double calc = 0;
		for (Ligne ligne: liste_produit) {
			calc += ligne.getPrice();
		}
		this.price = calc;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Facture: " + this.facture_id_local);
		sb.append("\tClient: " + this.client);
		sb.append("\tDate: " + this.date + "\n");
		for (Ligne ligne: liste_produit) {
			sb.append(ligne.toString());
			sb.append("\n");
		}
		sb.append("Prix total: " + this.price);
		return sb.toString();
	}
	
	private class Ligne {
		private int qte;
		private Saleable sale;
		
		private Ligne(int qte, Saleable sale) {
			this.qte = qte;
			this.sale = sale;
		}
		
		private double getPrice() {
			return this.qte * this.sale.getPrice();
		}
		
		@Override
		public String toString() {
			String res=String.format("%-15s %-15s %-35s %-15s %-8.2f %-8.2f",
	                this.qte,
	                this.sale.getRef(),
	                this.sale.getName(),
	                this.sale.getBrand(),
	                this.sale.getPrice(),
	                this.getPrice());
	        return res;
	    }

	}
	
}
