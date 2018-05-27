package stock;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import stock.Stylo.Couleur;

class LotTest {

	@Test
	void test() {
		try {
			LotStyloNull();
			LotAssert();
			LotNegativ();
		}
		catch (Exception e1) {
			System.out.println(e1);
		}
			
	}
	
	private void LotStyloNull() throws Exception {
		Stylo s1 = new Stylo("AZ", "Super stylo", "WaterTruc", 100, Couleur.ROUGE);
		Lot l1 = new Lot("Lot 1", 10, 20, s1);
		assertNotNull(l1.getNumber());
	}
	
	private void LotNegativ()  throws Exception {
		Stylo s1 = new Stylo("AZ", "Super stylo", "WaterTruc", 100, Couleur.ROUGE);
		Lot l1 = new Lot("Lot 1", 10, -20, s1);
		assertTrue(l1.getPercent()>=0);
	}
	
	private void LotAssert()  throws Exception {
		Stylo s1 = new Stylo("AZ", "Super stylo", "WaterTruc", 100, Couleur.ROUGE);
		Lot l1 = new Lot("Lot 1", 10, 50, s1);
		assertEquals(500, l1.getPrice());
	}

}
