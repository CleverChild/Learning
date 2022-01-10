#include <iostream>
#include <cmath>
#include <cstring>
#include <algorithm>

using namespace std;

int main(void)
{
	int N,i;
	int dp[1001];

	cin >> N;

	dp[0] = 1;
	dp[1] = 1;

	for (i = 2; i <= N; i++)
	{
		dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
	}

	cout << dp[N] << endl;

	return 0;
}