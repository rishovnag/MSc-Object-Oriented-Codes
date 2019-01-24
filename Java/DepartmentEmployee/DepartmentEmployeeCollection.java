import java.util.*;
import java.io.*;

enum Status {
	ACTIVE, RETIRED, RESIGNED, TERMINATED;
}

class Department {
	private int deptID;
	private String deptName;
	private String HODName;

	public Department(int deptID, String deptName, String HODName) {
		this.deptID = deptID;
		this.deptName = deptName;
		this.HODName = HODName;
	}
	
	@Override
	public String toString() {
		return "\n{" +
		"\n\tdeptID='" + getDeptID() + "'," +
		"\n\tdeptName='" + getDeptName() + "'," +
		"\n\tHODName='" + getHODName() + "'," +
		"\n}";
	}
	
	public int getDeptID() {
		return this.deptID;
	}
	
	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}
	
	public String getDeptName() {
		return this.deptName;
	}
	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public String getHODName() {
		return this.HODName;
	}
	
	public void setHODName(String HODName) {
		this.HODName = HODName;
	}
}

class Employee {
	private int empID;
	private String empName;
	private int age;
	private String address;
	private String DOB;
	private String DOJ;
	private double salary;
	private String designation;
	private int deptID;
	private String baseLocation;
	private Status status;

	public Employee(int empID, String empName, int age, String address, String DOB, String DOJ, double salary, String designation, int deptID, String baseLocation, Status status) {
		this.empID = empID;
		this.empName = empName;
		this.age = age;
		this.address = address;
		this.DOB = DOB;
		this.DOJ = DOJ;
		this.salary = salary;
		this.designation = designation;
		this.deptID = deptID;
		this.baseLocation = baseLocation;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "\n{" +
		"\n\tempID='" + getEmpID() + "'," +
		"\n\tempName='" + getEmpName() + "'," +
		"\n\tage='" + getAge() + "'," +
		"\n\taddress='" + getAddress() + "'," +
		"\n\tDOB='" + getDOB() + "'," +
		"\n\tDOJ='" + getDOJ() + "'," +
		"\n\tsalary='" + getSalary() + "'," +
		"\n\tdesignation='" + getDesignation() + "'," +
		"\n\tdeptID='" + getDeptID() + "'," +
		"\n\tbaseLocation='" + getBaseLocation() + "'," +
		"\n\tstatus='" + getStatus() + "'" +
		"\n}";
	}
	
	public int getEmpID() {
		return this.empID;
	}
	
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	
	public String getEmpName() {
		return this.empName;
	}
	
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDOB() {
		return this.DOB;
	}
	
	public void setDOB(String DOB) {
		this.DOB = DOB;
	}
	
	public String getDOJ() {
		return this.DOJ;
	}
	
	public void setDOJ(String DOJ) {
		this.DOJ = DOJ;
	}
	
	public double getSalary() {
		return this.salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public String getDesignation() {
		return this.designation;
	}
	
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public int getDeptID() {
		return this.deptID;
	}
	
	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}
	
	public String getBaseLocation() {
		return this.baseLocation;
	}
	
	public void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
	}
	
	public Status getStatus() {
		return this.status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
}

class QueryHelper {
	Scanner sc;
	ArrayList<Department> dept;
	ArrayList<Employee> emp;

	public ArrayList<Department> getDepartments() {		
		try {
			sc = new Scanner(new File("Department.csv"));
			sc.nextLine();
		} catch(FileNotFoundException e) { System.out.println(e); }

		dept = new ArrayList<Department>();

		while(sc.hasNextLine()) {
			try {
				String contents[] = sc.nextLine().split(",");
				dept.add(new Department(
					Integer.parseInt(contents[0]),
					contents[1],
					contents[2]
				));
			} catch(Exception e) { System.out.println("ERR: " + e); }
		}

		sc.close();

		return dept;
	}

	public ArrayList<Employee> getEmployees() {		
		try {
			sc = new Scanner(new File("Employee.csv"));
			sc.nextLine();
		} catch(FileNotFoundException e) { System.out.println(e); }

		emp = new ArrayList<Employee>();

		while(sc.hasNextLine()) {
			try {
				String contents[] = sc.nextLine().split(",");				
				emp.add(new Employee(
					Integer.parseInt(contents[0]),
					contents[1],
					Integer.parseInt(contents[2]),
					contents[3],
					contents[4],
					contents[5],
					Double.parseDouble(contents[6]),
					contents[7],
					Integer.parseInt(contents[8]),
					contents[9],
					Status.valueOf(contents[10])
				));
			} catch(Exception exc) { System.out.println("ERR: " + exc); }
		}

		sc.close();

		return emp;
	}

	public void displayEmployees(int dept) {
		emp.forEach(employee -> {
			if(employee.getDeptID() == dept) {
				System.out.println(employee);
			}
		});
	}

	public void displayEmployees(String loc) {
		emp.forEach(employee -> {
			if(employee.getBaseLocation().equalsIgnoreCase(loc)) {
				System.out.println(employee);
			}
		});
	}

	public void displayEmployees(double sal) {
		emp.forEach(employee -> {
			if(employee.getSalary() >= sal) {
				System.out.println(employee);
			}
		});
	}
}

class DepartmentEmployee {
	public static void main(String args[]) {
		QueryHelper query = new QueryHelper();
		Scanner sc = new Scanner(System.in);

		/* READ FROM DEPARTMENT FILE */
		ArrayList<Department> d = query.getDepartments();
		ArrayList<Employee> e = query.getEmployees();

		System.out.println("All department details:");
		d.forEach(value -> System.out.println(value));

		System.out.print("Enter a department ID: ");
		int deptChoice = 0;
		outer: while(true) {
			String deptIDInput = sc.nextLine();
			for(int i=0; i<d.size(); i++) {
				try {
					if(d.get(i).getDeptID() == Integer.parseInt(deptIDInput)) {
						deptChoice = Integer.parseInt(deptIDInput);
						break outer;
					}
				} catch(NumberFormatException exc) { System.out.print("Enter a valid number: "); }				
			}
			System.out.print("Enter a valid department ID: ");
		}

		System.out.println("\nEmployees in that department:");
		query.displayEmployees(deptChoice);

		System.out.println("\nEmployees based in Kolkata:");
		query.displayEmployees("Kolkata");

		System.out.println("\nEmployees with salary greater than 35k:");
		query.displayEmployees(35000.0);
	}
}