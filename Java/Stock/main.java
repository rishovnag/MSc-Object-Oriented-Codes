

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
			ps.println("Id	Name	Price	Quantity");
			for(int i=0; i<products.length; i++) {
				if(products[i] == null) continue;
				ps.println(products[i].id + "\t" + products[i].name + "\t" + products[i].price + "\t" + products[i].quantity);
			}
		} catch(Exception e) {
			System.out.println("\nERR: unable to update");
			System.exit(0);
		}
	}

	void showList(Product products[]) {
		System.out.println("\nAVAILABLE ITEMS\nID\tName\t\tPrice\t\tQuantity\n");

		for(int i=0; i<products.length; i++) {			
			if(products[i].quantity < 1) continue; 

			System.out.println(
				products[i].id + "\t" + 
				products[i].name + "\t" + 
				products[i].price + "\t\t" + 
				products[i].quantity
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
		if(cart[p.id] != null) { 
			cart[p.id].quantity += p.quantity;
		} else {
			cart[p.id] = p;
		}		
	}

	void showCart() {
		System.out.println("\nITEMS IN CART\nID\tName\t\tPrice\t\tQuantity\n");

		for(int i=0; i<cart.length; i++) {	
			try {		
				System.out.println(
					(cart[i].id+1) + "\t" + 
					cart[i].name + "\t" + 
					cart[i].price + "\t\t" + 
					cart[i].quantity
				);
			} catch(Exception e) {}
		}
	}

	boolean isCartEmpty() {
		return empty;
	}

	void createBill() {
		try {
			PrintStream ps = new PrintStream(new File(filename));
			ps.println("Id	Name		Price	Quantity");
			for(int i=0; i<cart.length; i++) {
				if(cart[i] == null) continue;
				ps.println((cart[i].id+1) + "\t" + cart[i].name + "\t" + cart[i].price + "\t" + cart[i].quantity);
			}
		} catch(Exception e) {
			System.out.println("\nUnable to update");
			System.exit(0);
		}
		System.out.println(" Created \'bill.txt\' successfully");
	}
}

class Order {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		Stock s = new Stock("stock.txt");	
		Bill b = new Bill("bill.txt");
		int id, quantity;	

		Product products[] = s.readList();
		b.setNumberOfItems(products.length);

		while(true) {
			s.showList(products);
			System.out.print("Enter the item ID (Enter 0 to exit): ");
			id = sc.nextInt() - 1; 

			if(id < 0) break; 

			System.out.print("Enter the Quantity: ");
			quantity = sc.nextInt();

			if(products[id].quantity < quantity) {
				System.out.println("\nQuantity of the certain order unavilable ");
			} else {
				products[id].quantity -= quantity;

				Product temp = new Product();
				temp.id = id;
				temp.name = products[id].name;
				temp.price = products[id].price;
				temp.quantity = quantity;

				b.addItem(temp);

				b.showCart(); 
			}

		}

		if(!b.isCartEmpty()) {
			System.out.print(" Press Y/y to purchase ");
			String choice = sc.next();
			if(choice.equalsIgnoreCase("Y")) {
				b.createBill();
				s.updateStock(products);
			}
		}

		sc.close();
	}
}