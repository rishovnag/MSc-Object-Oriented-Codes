/*

1. Create a class Student having attributes Roll, Name, Course, Subject, Semester. Create a collection of Students.

Display the students of course B.Tech, Subject=CS, Sem=3

*/

import java.util.*;

class Student {
	int roll;
	String name;
	String course;
	String subject;
	int semester;

	public int getRoll() {
		return this.roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse() {
		return this.course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getSemester() {
		return this.semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public Student(int roll, String name, String course, String subject, int semester) {
		this.roll = roll;
		this.name = name;
		this.course = course;
		this.subject = subject;
		this.semester = semester;
	}



	public String toString() {
		return (
			"\nName: " + name + 
			"\nRoll number: " + roll +
			"\nCourse: " + course +
			"\nSubject: " + subject +
			"\nSemester: " + semester
		);
	}
}

class StudentColletionTest {
	public static void main(String args[]) {
		ArrayList<Student> st = new ArrayList<Student>();
		st.add(new Student(1, "Hari", "BTech", "CS", 3));
		st.add(new Student(2, "Soumik", "MSc", "CS", 2));
		st.add(new Student(3, "Rishov", "MSc", "CS", 3));
		st.add(new Student(4, "Sukant", "BTech", "ECE", 2));
		st.add(new Student(5, "Debo", "MSc", "CS", 2));
		st.add(new Student(7, "Ayan", "BTech", "CS", 3));

		for(
			Iterator<Student> i = st.iterator(); 
			i.hasNext();
		) {
			Student x = i.next();
			if(
				x.getCourse().equals("BTech") && 
				x.getSemester() == 3 && 
				x.getSubject().equals("CS")) {
				System.out.println(x);
			}			
		}
	}
}