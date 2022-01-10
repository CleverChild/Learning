#include <iostream>
#include <cmath>
#include <string>
#include <algorithm>

using namespace std;

long long solve(int N)
{
	long long DP[103][13], res = 0;
	int i, j;

	DP[1][0] = 0;
	for (i = 1; i < 10; i++)
	{
		DP[1][i] = 1;
	}

	for (i = 2; i <= N; i++)
	{
		for (j = 0; j < 10; j++)
		{
			// 끝자리가 0 일 때
			if (!j)
			{
				DP[i][j] = DP[i - 1][j + 1] % 1000000000;
			}
			// 끝자리가 9 일 때
			else if (j == 9)
			{
				DP[i][j] = DP[i - 1][j - 1] % 1000000000;
			}
			// 끝자리가 1~8 일 때
			else
			{
				DP[i][j] = (DP[i - 1][j + 1] + DP[i - 1][j - 1]) % 1000000000;
			}
		}
	}
	
	for (i = 0; i < 10; i++)
	{
		res += DP[N][i] % 1000000000;
	}
	return res % 1000000000;
}

int main(void)
{
	int N;

	cin >> N;
	if (N == 1)
	{
		cout << 9 << endl;
	}
	else
	{
		cout << solve(N) << endl;
	}

	return 0;
}