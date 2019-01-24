import java.util.*;

interface Stack {
	// access specifiers are not mentioned since mentioning them will prevent subclasses from using specifiers with less access:

	void push(double x);
	double pop();
	void display();
}

class StandardStack implements Stack {
	private int top;
	private double stack[];

	public StandardStack(int size) {
		top = -1;
		stack = new double[size];
	}

	public void push(double x) {
		if(top + 1 == stack.length) {
			System.out.println("ERR: Stack overflow");			
		} else {
			stack[++top] = x;
		}
	}

	public double pop() {
		if(top == -1) {
			System.out.println("ERR: Stack underflow");			
		} else {
			return stack[top--];
		}
		return Integer.MIN_VALUE;
	}

	public void display() {
		for(int i=top; i>=0; i--) {
			System.out.print(stack[i] + " | ");
		}
		System.out.println();
	}
}

class StackPlus implements Stack {
	private int top;
	private double stack[];

	public StackPlus(int initSize) {
		top = -1;
		stack = new double[initSize];
	}

	public void push(double x) {
		if(top + 1 == stack.length) {
			if(stack.length > 1000) {
				System.out.println("ERR: No more memory");
				return;
			}
			double tempStack[] = new double[stack.length * 2];
			for(int i=0; i<stack.length; i++) {
				tempStack[i] = stack[i];
			}
			stack = tempStack;
		}
		stack[++top] = x;
	}

	public double pop() {
		if(top == -1) {
			System.out.println("ERR: Stack underflow");			
		} else {
			return stack[top--];
		}
		return Integer.MIN_VALUE;		
	}

	public void display() {
		for(int i=top; i>=0; i--) {
			System.out.print(stack[i] + " | ");
		}
		System.out.println();
	}
}

class ExtendedStack {
	static Scanner sc;
	public static void main(String args[]) {
		sc = new Scanner(System.in);
		Stack s;

		while(true) {
			System.out.print("1. Standard Stack\n2. Extended Stack\n3. Exit\n\nChoice: ");
			int choice = sc.nextInt();
			switch(choice) {
				case 1 : {
					System.out.print("Enter size of stack: ");
					s = new StandardStack(sc.nextInt());
					implement(s);
					break;
				}
				case 2 : {
					System.out.print("Enter initial size of stack: ");
					s = new StackPlus(sc.nextInt());
					implement(s);
					break;
				}
				case 3 : {
					System.exit(0);
				}
				default : {
					System.out.println("ERR: Invalid input");
				}
			}
		}
	}

	public static void implement(Stack stack) {
		while(true) {
			System.out.print("1. Push\n2. Pop\n3. Display\n4. Exit\n\nEnter choice: ");
			int choice = sc.nextInt();

			if(choice == 1) {
				System.out.print("Enter push element: ");
				stack.push(sc.nextDouble());
			} else if(choice == 2) {
				double t = stack.pop();
				if(t != Integer.MIN_VALUE) {
					System.out.println(t);
				}
			} else if(choice == 3) {
				stack.display();
			} else {
				break;
			}
		}
	}
}