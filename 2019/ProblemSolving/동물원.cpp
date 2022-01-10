#include <iostream>
#include <cmath>
#include <string>
#include <algorithm>

using namespace std;

int main(void)
{
	int DP[100002][3] = { { 0 } };
	int i, N;

	DP[1][0] = 1;
	DP[1][1] = 1;
	DP[1][2] = 1;

	cin >> N;

	for (i = 2; i <= N; i++)
	{
		DP[i][0] = (DP[i - 1][0] + DP[i - 1][1] + DP[i - 1][2]) % 9901;
		DP[i][1] = (DP[i - 1][0] + DP[i - 1][2]) % 9901;
		DP[i][2] = (DP[i - 1][0] + DP[i - 1][1]) % 9901;
	}

	cout << (DP[N][0] + DP[N][1] + DP[N][2]) % 9901 << endl;
	return 0;
}