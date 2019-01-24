#include<iostream>
#include<stdio.h>
using namespace std;

class Complex
{
	private:
		
		double rp;
		double ip;
	
	public:
		Complex()
		{
			rp=0;
			ip=0;
		}
		
		Complex(double real,double img)
		{
			rp=real;
			ip=img;
		}
		
		void add(Complex c1,Complex c2)
		{
			rp=c1.rp+c2.rp;
			ip=c1.ip+c2.ip;
		}
		
		void sub(Complex c1,Complex c2)
		{
			rp=c1.rp-c2.rp;
			ip=c1.ip-c2.ip;
		}
		
		void printadd()
		{
			cout<<"The Addition of the Complex Numbers are::"<<rp<<"+"<<ip<<"i"<<"\n";
		}
		void printsubtract()
		{
			cout<<"The Substraction of the Complex Numbers are::"<<rp<<"-"<<ip<<"i"<<"\n";
		}
};
		
int main()
{
			double r,i;
			cout<<"Enter the real part of 1'st Number'::";
			cin>>r;
			cout<<"Enter the imaginary of part of 1'st' Number::";
			cin>>i;
			Complex c1(r,i);
			cout<<"Enter the real part of 2nd Number'::";
			cin>>r;
			cout<<"Enter the imaginary of part of 2nd Number::";
			cin>>i;
			Complex c2(r,i); 
			Complex c3;
			c3.add(c1,c2);
			c3.printadd();
			c3.sub(c1,c2);
			c3.printsubtract();	
			return 0;		
}
