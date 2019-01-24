/* 

	number of subjects (final), array of marks (from user), array of credits, average percentage (calculate)
	display methods in sub and super classes	
	
	marks->grade conversion:
	90-100: O (10 points)
	70-89: E (8.5 points)
	50-69: A (7 points)
	40-49: B (5 points)
	Below 40: F (0 points)

	SGPA = credits(i) .* grade(i) / 18 
	[i = 0 to 4]
	
	if marks < 40, fail
	method: calculate result

*/

import java.util.*;

abstract class Student {
	String name, course, type;
	int roll, semesterNumber, yearOfExam;

	final int numberOfSubjects = 5;

	public Student(		
		String n, String c, String t, 
		int r, int sN, int yOE
	) {
		name = n;
		course = c;
		semesterNumber = sN;
		roll = r;
		t = type;
		yOE = yearOfExam;
	}

	public void displayPersonalDetails() {		
		p("Name: " + name);
		p("Course: " + course);
		p("Semester number: " + semesterNumber);
		p("Roll: " + roll);
		p("Type: " + type);
		p("Year of exam: " + yearOfExam);
	}

	public void p(String x) {
		System.out.println(x);
	}

	abstract void calculateResult();
}

class StudentMSc extends Student {
	private int marks[];

	public StudentMSc(
		String n, String c, String t, 
		int r, int sN, int yOE,
		int marks[]
	) {
		super(
			n, c, t,
			r, sN, yOE
		);
		
		this.marks = marks;
	}

	public void calculateResult() {
		int sum = 0;
		boolean failStatus = false;
		for(int i=0; i<marks.length; i++) {
			sum = sum + marks[i];
			if(marks[i] < 40) {
				p("Subject #" + (i+1) + ": Fail");				
				failStatus = true;
			}
		}
		p("Percentage: " + (sum/marks.length));
		if(failStatus) p("Status: Failed");
	}
}

class StudentBTech extends Student {
	private int marks[];
	private int credits[];
	private boolean failStatus;

	public StudentBTech(
		String n, String c, String t, 
		int r, int sN, int yOE,
		int marks[]
	) {
		super(
			n, c, t,
			r, sN, yOE
		);

		this.marks = marks;
		this.credits = new int[]{3, 3, 4, 4, 4};
		failStatus = false;
	}

	public double slabOut(int marks, int credits, int srNo) {
		char grade = '\0';
		double points = 0;

		if(marks >= 90) {
			grade = 'O';
			points = 10;
		} else if(marks >= 70) {
			grade = 'E';
			points = 8.5;
		} else if(marks >=50) {
			grade = 'A';
			points = 7;
		} else if(marks >= 40) {
			grade = 'B';
			points = 5;
		} else {
			grade = 'F';
			failStatus = true;
			points = 0;
		}

		p("Subject #" + srNo + ": " + marks + " [Grade: " + grade + "; Points: " + points + "]");
		return points;
	}

	public void calculateResult() {
		int sum = 0;
		int totalCredits = 0;
		double SGPA = 0;
		for(int i=0; i<marks.length; i++) {
			sum = sum + marks[i];
			totalCredits = credits[i];

			SGPA += slabOut(marks[i], credits[i], (i+1));
		}
		p("SGPA = " + (SGPA / totalCredits));
		if(failStatus) p("Status: Failed");
	}
}

class AbstractMarks {
	public static void main(String args[]) {
		String name, course = "", type = "";
		int roll, semesterNumber, yearOfExam, courseType;

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter student\'s details: ");
		System.out.print("Name: ");
		name = sc.nextLine();
		System.out.print("Course (1: MSc/2: BTech): ");
		courseType = sc.nextInt();

		if(courseType == 1) course += "MSc";
		else if(courseType == 2) course += "BTech";
		else course += "none";

		System.out.print("Semester number: ");
		semesterNumber = sc.nextInt();
		System.out.print("Roll number: ");
		roll = sc.nextInt();
		System.out.print("Year of exam: ");
		yearOfExam = sc.nextInt();

		if(semesterNumber % 2 == 0) {
			type += "Even";
		} else {
			type += "Odd";
		}
		// type = semesterNumber % 2 == 0 ? "Even" : "Odd";
		
		if(course.equalsIgnoreCase("MSc")) {

			System.out.println("\nEnter marks of 5 subjects:");
			int marks[] = new int[5];
			for(int i=0; i<5; i++) {
				marks[i] = sc.nextInt();
			}

			StudentMSc student = new StudentMSc(
				name, course, type, roll, semesterNumber, yearOfExam, marks
			);			

			student.displayPersonalDetails();
			student.calculateResult();		

		} else if(course.equalsIgnoreCase("BTech")) {

			System.out.println("\nEnter marks of 5 subjects:");
			int marks[] = new int[5];
			for(int i=0; i<5; i++) {
				marks[i] = sc.nextInt();
			}

			StudentBTech student = new StudentBTech(
				name, course, type, roll, semesterNumber, yearOfExam, marks
			);

			student.displayPersonalDetails();
			student.calculateResult();

		} else {
			System.out.println("Error in input");
		}
	}
}