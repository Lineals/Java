package stock;

public class Lot implements Saleable{

	private String name;
	private double price;

	private String ref;
	private int number;
	private double percent;
	private Article component;
	
	public Lot(String ref, int number, float percent, Article component) {
		this.ref = ref;
		this.number = number;
		if (percent < 0) {
			percent = 0;
		}
		this.percent = percent;
		this.component = component;
	
		this.genName();
	}
	
	private void genName() {
		this.name = "Lot de " + this.number + this.component;
	}
	
	private void calcPrice() {
		this.price = this.number * (this.component.getPrice() * ((100 - this.percent) / 100));

	}
	
	@Override
	public String toString() {
		this.genName();
		return this.name;
	}
	
	public String getRef() {
		return this.ref;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		System.out.println("Number of article set to " + number);
		this.number = number;
	}

	public double getPercent() {
		return this.percent;
	}

	public void setPercent(float percent) {
		System.out.println("Discount set to : " + percent);
		this.percent = percent;
	}

	public double getPrice() {
		this.calcPrice();
		return this.price;
	}

	@Override
	public String getName() {
		this.genName();
		return this.name;
	}

	@Override
	public String getBrand() {
		return this.component.getBrand();
	}
}
