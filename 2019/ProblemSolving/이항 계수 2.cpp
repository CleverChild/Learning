#include <iostream>
#include <cmath>
#include <cstring>
#include <algorithm>

using namespace std;

int main()
{
	long long dp[1001][1001];
	fill(&dp[0][0], &dp[1001 - 1][1001], 1);
	long long N, K;
	int i, j;
	const long long divider = 10007;

	cin >> N >> K;

	if (N != K)
	{
		for (i = 2; i <= N; i++)
		{
			for (j = 1; j < i; j++)
			{
				dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % divider;
			}
		}
		cout << dp[N][K] << endl;
	}
	else
	{
		cout << 1 << endl;
	}

	return 0;
}