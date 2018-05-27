package client;

import java.util.Scanner;

import stock.Stock;

public class ClientDemo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Client c1 = new Client("Jacques");
		Client c2 = new Client("André");
		
		ClientList cl = new ClientList();
		
		cl.addClient(c1);
		cl.addClient(c2);
		
		System.out.println(cl.nbClient());
		
		cl.sortAlphab();
		System.out.println(cl);
		
		
		sc.close();
	}
}
