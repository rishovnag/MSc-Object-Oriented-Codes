#include<iostream>
#include<stdio.h>
using namespace std;
class StudentData;
class SubjectData;
class SportsData;
class FinalData;

class StudentData
{
	protected:
		int roll;
		int class1;
		float attendance_percentage;
	public:
		void setData();
		void showData();	
};

class SubjectData: virtual public StudentData
{
	protected:
		int marksC;
		int marksDS;
	public: 
		void setData1();
		void showData1();
};

class SportsData: virtual public StudentData
{
	protected:
		int footballscore;
		int cricketscore;
	public: 
		void setScore();
		void showScore();
};

class FinalData: public SportsData, public SubjectData
{
	private: 
		int total;
	public:
		void display();
};

void StudentData::setData()
{
	roll=20;
	class1=2;
	attendance_percentage=98;
}

void StudentData::showData()
{
	cout << roll << endl;
	cout << class1 << endl;
	cout << attendance_percentage << endl;
}

void SubjectData::setData1()
{
	marksC=70;
	marksDS=81;
}

void SubjectData::showData1()
{
	cout << marksC << endl;
	cout << marksDS << endl;
}

void SportsData::setScore()
{
	footballscore=5;
	cricketscore=150;
}

void SportsData::showScore()
{
	cout << footballscore << endl;
	cout << cricketscore << endl;
}

void FinalData::display()
{
	total=footballscore+cricketscore+marksC+marksDS;
	cout << total << endl;
}

int main()
{
	FinalData f;
	f.setData();
	f.showData();
	f.setData1();
	f.showData1();
	f.setScore();
	f.showScore();
	f.display();
}
