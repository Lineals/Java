package stock;

public class Stylo extends Article{
	public enum Couleur {ROUGE, VERT, BLEU, NOIR};
	private Couleur color; 

	public Stylo(String ref, String name, String brand, double price, Couleur color) {
		super(ref, name, brand, price);
		this.color = color;
	}
	
	public String toString() {
		return " Stylo" + " " + this.color + " " + this.getName();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean out = false;
		if (obj instanceof Stylo) {
			out = this.getName() == ((Stylo) obj).getName();
		}
		return out;
	}

}
