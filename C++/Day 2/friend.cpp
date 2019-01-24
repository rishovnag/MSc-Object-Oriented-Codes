#include <iostream>
using namespace std;
class SportsData;
class SubjectData
{
	private:
		int marksC;
		int marksDS;
		float avgmarks;
	public:	
		void setmarks(int,int);
		void calculate();	
	friend void comparison(SportsData,SubjectData);
};
class SportsData
{
	private:
		int footballScore;
		int cricketScore;
		float avgscore;
	public: 
		void setscore(int,int);
		void calculate();	
	friend void comparison(SportsData,SubjectData);
};

void SubjectData::setmarks(int a, int b)
{
	marksC=a;
	marksDS=b;
}

void SubjectData::calculate()
{
	avgmarks=(marksC+marksDS)/2;
}

void SportsData::setscore(int a, int b)
{
	footballScore=a;
	cricketScore=b;
}

void SportsData::calculate()
{
	avgscore=(footballScore+cricketScore)/2;
}

void comparison(SportsData obj2, SubjectData obj1)
{
	if(obj2.avgscore>obj1.avgmarks)
		cout << "Score is greater";
	else 
		cout << "Marks is greater";
}

int main()
{
	SubjectData obj1;
	SportsData obj2;
	obj1.setmarks(80,70);
	obj1.calculate();
	obj2.setscore(3,150);
	obj2.calculate();
	comparison(obj2,obj1);
}
