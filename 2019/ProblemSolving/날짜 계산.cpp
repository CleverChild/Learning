#include <iostream>
#include <cmath>
#include <string>
#include <algorithm>

using namespace std;

int main(void)
{
	int E, S, M;
	int a=1, b=1, c=1;
	int year_cnt=0;

	cin >> E >> S >> M;

	while (true)
	{
		if (a == E && b == S && c == M) break;
		if (a >= 15)
		{
			a = 1;
			a--;
		}
		if (b >= 28)
		{
			b = 1;
			b--;
		}
		if (c >= 19)
		{
			c = 1;
			c--;
		}
		a++;
		b++;
		c++;
		year_cnt++;
	}
	
	cout << ++year_cnt << endl;
	
	return 0;
}