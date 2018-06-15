import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Metier.*;

class ClientTest {
	Client Client1 = new Client("lineal","remy","1 avenue de la resistance");
	Client Client2 = new Client("toast","theo","1 rue de la resistance");
	Client Client3 = new Client("oropo","emilio","1 square de la resistance");
    

	@Test
	void testAutoIncrement() {
		assertEquals(1, Client1.getId());
		assertEquals(2, Client2.getId());
		assertEquals(3, Client3.getId());
	}
	
	void testFields() {
		assertEquals(Client1.getNom(), "lineal");
		assertEquals(Client2.getPrenom(),"theo");
		assertEquals(Client3.getAdresse(), "1 square de la resistance");
	}

}
