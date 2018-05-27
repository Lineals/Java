package client;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;

import javax.naming.directory.NoSuchAttributeException;

public class ClientList {
	private ArrayList<Client> list;
	
	public ClientList() {
		this.list = new ArrayList<>();
	}
	
	public ClientList(ArrayList<Client> list) {
		this.list = list;
	}
	
	public boolean addClient(Client c1) {
		boolean to_add = true;
		if (!this.list.contains(c1)) {
			this.list.add(c1);
		}
		else {
			to_add = false;
		}
		return to_add;
	}
	
	public boolean removeClient(int id) {
		boolean removed;
		try {
			Client client = getClientFromId(id);
			removed = true;
			this.list.remove(client);
		} catch (NoSuchElementException e) {
			removed = false;
		}
		return removed;
	}
	
	public int nbClient() {
		return this.list.size();
	}
	
	public Client getClientFromId(int id) throws NoSuchElementException {
		Client client = null;
		for (Client elem: this.list) {
			if (elem.getID() == id) {
				client = elem;
				break;
			}
		}
		if (client == null) {
			throw new NoSuchElementException("ID : " + id + " n'a pas pu être trouvé");
		}
		else {
			return client;
		}
		
	}
	
	public String toString() {
		String str = "";
		for (Client elem: this.list) {
			str += elem.toString() + "\n";
		}
		return str;
	}
	
	public void sortAlphab() {
		Collections.sort(this.list, new ClientCompare());
	}
}
