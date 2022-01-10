#include <iostream>
#include <cmath>
#include <string>
#include <algorithm>

using namespace std;

int main(void)
{
	int cnt = 0, N, i = 666, j;

	cin >> N;

	while (N)
	{
		j = i;

		while (j)
		{
			if (j % 10 == 6)
			{
				cnt++;
			}
			else // 6이 연속되지 않은경우 cnt를 0으로 함.
			{
				cnt = 0;
			}
			j /= 10;

			if (cnt == 3)
			{
				N--;
				break;
			}
		}
		cnt = 0;
		i++;
	}
	cout << i - 1 << endl;

	return 0;
}