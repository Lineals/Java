package stock;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.util.Scanner;

import stock.Stylo.Couleur;

public class StockDemo {
	public static void main(String[] args) {


		InputStream in = System.in;
		InputStreamReader isr = new InputStreamReader(in, Charset.forName("UTF-8"));
		BufferedReader reader = new BufferedReader(isr);
		
		Scanner sc = new Scanner(System.in);
		Stylo s1 = new Stylo(getStr(sc), buffered(reader), "WaterTruc", getInt(sc), Couleur.ROUGE);
		Papier s2 = new Papier("WQ", "HQ Paper", "Clairefontaine", 1900, 80);
		Lot l1 = new Lot("Lot 1", 10, 20, s1);
		Lot l2 = new Lot("Lot 2", 20, 20, s2);
		
		Stock st1 = new Stock(s1, 10);
		try {
			st1.addArticle(s2, 5);
			st1.addArticle(s1, 1);
			System.out.println("Low in stock " + st1.getLowStock(20000));
		} catch (InvalidParameterException e) { System.out.println("Quantité négative");}
		System.out.println(st1);
		sc.close();
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static String getStr(Scanner sc) {
		 String s = sc.next();
		 return s;
	}
	
	private static int getInt(Scanner sc) {
		 int i = sc.nextInt();
		 return i;
	}
	
	private static String buffered(BufferedReader reader){

		String line = "";
		try {
			line = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
}
