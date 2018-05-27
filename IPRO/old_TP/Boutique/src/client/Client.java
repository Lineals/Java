package client;

import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.Comparator;
import java.util.Objects;

/**
 * Cette classe est utilisée pour représenter un client.
 *
 * @author Remy Mollandin
 */
public class Client implements Comparable {
	/**
    * Nom du client
    */
	private String name;
	
	/**
	* Addresse du client
    */
	private String address;
	
	/**
	* ID Client global à la classe
    */
	private static int id_class = 0;
	
	/**
	* ID du client
    */
	private int id;

	/**
	* Constructeur de la classe Client
	* 
	* @param name Nom du client	
    */
	public Client(String name) {
		this.name = name;
		this.id = id_class;
		id_class++;
	}
	
	/**
	* Getter variable name
	* 
	* @return Retourne le nom
    */
	public String getName() {
		return this.name;
	}
	
	
	/**
	* Getter variable addresse
	* 
	* @return Retourne l'addresse
    */
	public String getAddress() {
		return address;
	}


	/**
	* Setter addresse
	* 
	* @param address String représentant l'adresse du client
    */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	* Getter variable id
	* 
	* @return Retourne l'id
    */
	public int getID() {
		return id;
	}
	
	/**
	* Affiche l'id et le nom
    */
	public void display() {
		System.out.println(this.id + " " + this.name);
	}
	
	public boolean equals(Object o) {
		boolean resp = false;
		if (o instanceof Client) {
			resp = this.id == ((Client) o).getID();
		}
		return resp;
	}
	
	public int hashCode() {
		return Objects.hash(this.id);
	}
	
	@Override
	public String toString() {
		return "ID : " + this.id + " Name : " + this.name;
	}

	@Override
	public int compareTo(Object o) throws IllegalArgumentException{
		int res;
		if (o instanceof Client) {
			if(((Client) o).getID() == this.id) {
				res = 0;
			}
			else if (((Client) o).getID() > this.id) {
				res = 1;
			}
			else {
				res = -1;
			}
		}
		else {
			throw new IllegalArgumentException("Argument n'est pas une instance de Client");
		}
		return res;
	}
}

