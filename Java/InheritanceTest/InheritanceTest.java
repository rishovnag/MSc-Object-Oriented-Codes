import java.util.*;

class Shape {
	public Shape() {
		System.out.println("Base class constructor");
	}

	public double calcVolume() {
		return 0.0d;
	}

	public void showVolume(double x) {
		System.out.println("Volume = " + x);
	}
}

class Cube extends Shape {
	int side;
	public Cube() {
		super();
	}

	public Cube(int side) {
		this.side = side;
	}

	public double calcVolume() {
		return side * side * side;
	}
}

class RectangularPrism extends Shape {
	int length, breadth, height;
	public RectangularPrism() {
		super();
	}

	public RectangularPrism(int l, int b, int h) {
		length = l;
		breadth = b;
		height = h;
	}

	public double calcVolume() {
		return length * breadth * height;
	}
}

class Sphere extends Shape {
	int radius;
	public Sphere() {
		super();
	}

	public Sphere(int r) {
		radius = r;
	}

	public double calcVolume() {
		return 4/3 * Math.PI * Math.pow(radius, 3);
	}
}

class InheritanceTest {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter side of cube: ");
		Cube c = new Cube(sc.nextInt());
		c.showVolume(c.calcVolume());

		System.out.print("Enter details for rectangular prism:\nLength: ");
		int l = sc.nextInt();
		System.out.print("Breadth: ");
		int b = sc.nextInt();
		System.out.print("Height: ");
		int h = sc.nextInt();
		RectangularPrism rp = new RectangularPrism(l, b, h);
		rp.showVolume(rp.calcVolume());

		System.out.print("Enter radius of sphere: ");
		Sphere s = new Sphere(sc.nextInt());
		s.showVolume(s.calcVolume());

		sc.close();
	}
}