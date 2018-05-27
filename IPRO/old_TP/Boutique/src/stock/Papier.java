package stock;

public class Papier extends Article{
	private int weight;

	public Papier(String ref, String name, String brand, double d, int weight) {
		super(ref, name, brand, d);
		this.weight = weight;
	}
	
	public String toString() {
		return " Ramette" + " " + this.weight + " " + this.getName();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean out = false;
		if (obj instanceof Papier) {
			out = this.getName() == ((Papier) obj).getName();
		}
		return out;
	}

}
