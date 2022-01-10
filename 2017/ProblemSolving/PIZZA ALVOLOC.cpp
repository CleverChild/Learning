#include <stdio.h>
#include <string.h>

int main(void)
{
	double x1,y1;
	double x2,y2;
	double x3,y3;
	double x4,y4;
	double den,den2,den3,tc,td,ta,tb,res,res2;

	scanf("%lf %lf",&x1,&y1);
	scanf("%lf %lf",&x2,&y2);
	scanf("%lf %lf",&x3,&y3);
	scanf("%lf %lf",&x4,&y4);

	//den = (x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)/(y4, - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
	//den2 = (x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)/(y4, - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
	//den3 = (y1-y3) * (x4-x3) - (x1-x3) * (y4-y3) / (x2-x1) * (y4-y3) - (y2-y1) * (x4-x3);
	tc = (x1 - x2) * (y3 - y1) + (y1 - y2) * (x1 - x3);

	td = (x1 - x2) * (y4 - y1) + (y1 - y2) * (x1 - x4);

	ta = (x3 - x4) * (y1 - y3) + (y3 - y4) * (x3 - x1);

	tb = (x3 - x4) * (y2 - y3) + (y3 - y4) * (x3 - x2);

	res=tc*td;
	res2=ta*tb;
	//printf("%lf\n",tc*td);
	//printf("%lf\n",ta*tb);

	//printf("%lf\n",den);
	//printf("%lf\n",den2);
	//printf("%lf\n",den3);

	if( res<0 && res2<0 )
	{
		puts("1");
	}
	else
	{
		puts("0");
	}

	return 0;
}