import java.io.*;

class Stack {
	private double stackValues[];
	private int top;

	public Stack(int arrSize) {
		top = -1;
		stackValues = new double[arrSize];
	}

	public void push(double x) {
		if (top + 1 == stackValues.length) {
			System.out.println("Stack overflow");
		} else {
			stackValues[++top] = x;
		}
	}

	public double pop() {
		if (top == -1) {
			System.out.println("Stack is empty");
			return Integer.MIN_VALUE;
		} else {
			return stackValues[top--];
		}
	}

	public void display() {
		if (top != -1) {
			for (int i = 0; i <= top; i++) {
				System.out.print(stackValues[i] + " | ");
			}
			System.out.println();
		} else {
			System.out.println("Stack is empty");
		}
	}
}

class StackImplementation {
	public static void main(String args[]) {
		Stack stack = new Stack(3);
		BufferedReader br = null;
		int choice;

		try {
			br = new BufferedReader(new InputStreamReader(System.in));
		} catch (Exception e) {
			System.out.println(e);
		}

		while (true) {
			System.out.println("Enter your choice:\n1. Push\n2. Pop\n3. Display\n4. Stop");
			try {
				choice = Integer.parseInt(br.readLine());
			} catch (Exception e) {
				System.out.println("Enter a proper choice");
				continue;
			}
			switch (choice) {
			case 1: {
				System.out.print("Value to be pushed: ");
				double x;
				try {
					x = Double.parseDouble(br.readLine());
				} catch (Exception e) {
					System.out.println("This stack can accept floating point values only");
					break;
				}
				stack.push(x);
				break;
			}

			case 2: {
				double popped = stack.pop();
				if (popped != Integer.MIN_VALUE) {
					System.out.println("Popped value: " + popped);
				}
				break;
			}

			case 3:
				stack.display();
				break;

			case 4:
				System.exit(0);

			default:
				System.out.println("Enter a valid choice");
			}
		}
	}
}