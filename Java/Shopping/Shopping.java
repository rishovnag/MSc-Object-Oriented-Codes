/*
Simulate a shopping scenario
*/

import java.util.*;
import java.io.*;

class Product {
	private String name;
	private int id;
	private double price;
	private int quantity;

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

	private String filename;

	public Stock(String filename) {
		this.filename = filename;
	}

	Product[] readList() throws Exception {

		int numberOfProducts = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
			while(br.readLine() != null) numberOfProducts++;
			br.close();
		} catch(Exception e) {}
		
		Product	products[] = new Product[numberOfProducts - 1];

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
  
  void updateStock(Product products[]) {
		try {
			PrintStream ps = new PrintStream(new File(filename));
			ps.println("Product_id	Name	Price	Quantity");
			for(int i=0; i<products.length; i++) {
				if(products[i] == null) continue;
				ps.println(products[i].getID() + "\t" + products[i].getName() + "\t" + products[i].getPrice() + "\t" + products[i].getQuantity());
			}
		} catch(Exception e) {
			System.out.println("\nERR: stock could not be updated");
			System.exit(0);
		}
	}

	void showList(Product products[]) {
		System.out.println("\nAVAILABLE ITEMS\n---------------\n\nID\tName\t\tPrices\t\tQuantity\n");

		for(int i=0; i<products.length; i++) {			
			if(products[i].getQuantity() < 1) continue; // the items with quantity == 0 need not be displayed

			System.out.println(
				products[i].getID() + "\t" + 
				products[i].getName() + "\t" + 
				products[i].getPrice() + "\t\t" + 
				products[i].getQuantity()
			);
		}
	}
}

class Bill {
	private Product cart[];
	private String filename;
	private boolean empty;

	Bill(String filename) {
		this.filename = filename;
		empty = true;
	}

	void setNumberOfItems(int size) {
		cart = new Product[size];
	}

	void addItem(Product p) {
		empty = false;
		if(cart[p.getID()] != null) { // same item is being added to the cart again
			cart[p.getID()].setQuantity(
				cart[p.getID()].getQuantity() + p.getQuantity()
			);
		} else {
			cart[p.getID()] = p;
		}		
	}

	void showCart() {
		System.out.println("\nITEMS BOUGHT\n---------------\n\nID\tName\t\tPrices\t\tQuantity\n");

		for(int i=0; i<cart.length; i++) {	
			try {		
				System.out.println(
					cart[i].getID() + "\t" + 
					cart[i].getName() + "\t" + 
					cart[i].getPrice() + "\t\t" + 
					cart[i].getQuantity()
				);
			} catch(Exception e) {}
		}
	}

	boolean isCartEmpty() {
		return empty;
	}

	void createBill() { // writes the shopping cart to the file
		double total = 0;
		try {
			PrintStream ps = new PrintStream(new File(filename));
			ps.println("Product_id	Name	Price	Quantity");
			for(int i=0; i<cart.length; i++) {
				if(cart[i] == null) continue;
				ps.println((cart[i].getID() + 1) + "\t" + cart[i].getName() + "\t" + cart[i].getPrice() + "\t" + cart[i].getQuantity());
				total += cart[i].getPrice() * cart[i].getQuantity();
			}
		} catch(Exception e) {
			System.out.println("\nList of items could not be written to a file");
			System.exit(0);
		}
		System.out.println("Amount to be paid: " + total);
		System.out.println("List of items have been written to \'invoice.shop\' successfully");
	}
}

class Order {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		Stock s = new Stock("stock.shop");	
		Bill b = new Bill("invoice.shop");
		int id, quantity;	

		Product products[] = s.readList();
		b.setNumberOfItems(products.length);

		while(true) {
			s.showList(products);
			System.out.print("Enter the item ID you wish to purchase (0: exit): ");
			id = sc.nextInt() - 1; // array index starts from 0; id starts from 1 (in the 'stock' file)

			if(id < 0) break; //exit condition

			System.out.print("Quantity: ");
			quantity = sc.nextInt();

			if(products[id].getQuantity() < quantity) {
				System.out.println("\nPlease order less than " + products[id].getQuantity() + " units of " + products[id].getName());
			} else {
				products[id].setQuantity(products[id].getQuantity() - quantity);

				Product temp = new Product(
					id,
					products[id].getName(),
					products[id].getPrice(),
					quantity
				);

				b.addItem(temp);

				b.showCart(); // display current shopping cart
			}

		}

		if(!b.isCartEmpty()) {
			System.out.print("Do you wish to purchase the current list of items? (Y/y: yes) ");
			String choice = sc.next();
			if(choice.equalsIgnoreCase("Y")) {
        b.createBill();
        s.updateStock(products);
			}
		}

		sc.close();
	}
}