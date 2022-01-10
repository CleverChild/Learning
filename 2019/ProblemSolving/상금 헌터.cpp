#include <iostream>
using namespace std;

int main(void)
{
	int a, b, N,total=0;

	cin >> N;
	for (int i = 0; i < N; i++)
	{
		total = 0;
		cin >> a;
		cin >> b;
		if (a < 2 && a)
		{
			total += 5000000;
		}
		else if (a < 4 && a)
		{
			total += 3000000;
		}
		else if (a < 7 && a)
		{
			total += 2000000;
		}
		else if (a < 11 && a)
		{
			total += 500000;
		}
		else if (a < 16 && a)
		{
			total += 300000;
		}
		else if (a < 22 && a)
		{
			total += 100000;
		}

		if (b < 2 && b)
		{
			total += 5120000;
		}
		else if (b < 4 && b)
		{
			total += 2560000;
		}
		else if (b < 8 && b)
		{
			total += 1280000;
		}
		else if (b < 16 && b)
		{
			total += 640000;
		}
		else if (b < 32 && b)
		{
			total += 320000;
		}

		cout << total << endl;
	}
}