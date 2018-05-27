package stock;

public abstract class Article implements Saleable{
	private String ref;
	private String name;
	private String brand;
	private double price;
	
	public String getRef() {
		return this.ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	protected Article(String ref, String name, String brand, double price) {
		this.ref = ref;
		this.name = name;
		this.brand = brand;
		this.price = price;
	}
	
	
}
