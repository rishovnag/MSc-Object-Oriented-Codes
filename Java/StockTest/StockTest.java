/*

Class: Product
	Variables: String name, int id, double price, int quantity
	Create constructor

Class: Stock
	Attribute: array_of_products (Array of Product class)
	Create constructor:
		Stock(String filename)
	void showList()

Class: Order
	Product p -> array of products
	double enterOrder(Stock s, PrintStream ps) 

*/

import java.util.*;
import java.io.*;

class Product {
	String name;
	int id;
	double price;
	int quantity;

	public Product() {}
	public Product(int id, String name, double price, int quantity) {
		this.name = name;
		this.id = id;
		this.price = price;
		this.quantity = quantity;
	}

	void setID(int id) {this.id = id;}
	int getID() {return id;}

	void setName(String name) {this.name = name;}
	String getName() {return name;}

	void setPrice(double price) {this.price = price;}
	double getPrice() {return price;}

	void setQuantity(int quantity) {this.quantity = quantity;}
	int getQuantity() {return quantity;}
}

class Stock {

	Product products[];
	String filename;

	public Stock(String filename) {
		this.filename = filename;
		int numberOfProducts = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
			while(br.readLine() != null) numberOfProducts++;
			br.close();
		} catch(Exception e) {}
		
		products = new Product[numberOfProducts - 1];
	}

	Product[] readList() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(new File(filename)));		
		br.readLine();
		String nextLine = br.readLine();
		int i=0;

		while(nextLine != null) {			
			String arr[] = nextLine.split("\t");

			products[i++] = new Product(
				Integer.parseInt(arr[0]), 
				arr[1], 
				Double.parseDouble(arr[2]), 
				Integer.parseInt(arr[3])
			);

			nextLine = br.readLine();
		}		

		br.close();
		return products;
	}

	void showList() {
		System.out.println("\nAVAILABLE ITEMS\n---------------\n\nID\tName\t\tPrices\t\tQuantity\n");

		for(int i=0; i<products.length; i++) {
			System.out.println(
				products[i].id + "\t" + 
				products[i].name + "\t" + 
				products[i].price + "\t\t" + 
				products[i].quantity
			);
		}
	}
}

class Order {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		Stock s = new Stock("order1.stock");		

		Product products[] = s.readList();
		s.showList();

		while(true) {
			s.showList();
			System.out.print("Enter the item ID you wish to purchase: ");
			int id = sc.nextInt();
			System.out.print("Quantity: ");
		}
	}
}