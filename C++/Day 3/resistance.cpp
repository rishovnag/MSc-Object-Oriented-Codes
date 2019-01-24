#include<iostream>
#include<stdio.h>
using namespace std;
class resist
{
	private:	
		float r1,r2,r;
	public:
		char connection;
		resist()
		{
			r1=0;
			r2=0;
			r=0;
		}
		resist(float a, float b)
		{
			r1=a;
			r2=b;
			r=0;	
		}	
		void calculate()
		{
			if(connection=='s')
				r=r1+r2;
			else if(connection=='p')
				r=(r1*r2)/(r1+r2);
			else
				cout<<"\nWrong Choice...";
		}
		void display()
		{
			cout<<"\n\tR1="<<r1<<"\tR2="<<r2<<"\tConnection Type:";
			if(connection=='s')
				cout<<"Serial";
			else if(connection=='p')
				cout<<"Parallel";
			else
				cout<<"Tata";	
			cout<<"Effective Resistance="<<r;		
		}			
};

int main()
{
	char c;
	float x1,x2;
	cout<<"Do you want to enter the values(y/n)?";
	cin>>c;
	getchar();
	if(c=='y')
	{
		resist obj1;
		cout<<"Enter R1:";
		cin>>x1;
		cout<<"Enter R2:";
		cin>>x2;
		obj1 = resist(x1,x2);
		cout<<"\nEnter Connection Type(s/p):";
		getchar();
		cin>>obj1.connection;
		obj1.calculate();
		obj1.display();
	}
	else if(c=='n')
	{
		resist obj2;
		obj2 = resist();
		cout<<"\nEnter Connection Type(s/p):";
		cin>>obj2.connection;
		obj2.calculate();
		obj2.display();
	}
	else
		cout<<"Tata";
	return 0;
}



