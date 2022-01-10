#include <iostream>
#include <cmath>
#include <string>
#include <algorithm>

using namespace std;

int main(void)
{
	long long DP[91][2] = { { 0 } };
	int i, N;

	cin >> N;

	DP[1][0] = 0;
	DP[1][0] = 1;
	DP[2][0] = 1;
	DP[2][1] = 0;

	for (i = 3; i <= N; i++)
	{
		DP[i][0] = DP[i - 1][0] + DP[i - 1][1];
		DP[i][1] = DP[i - 1][0];
	}

	cout << DP[N][0] + DP[N][1] << endl;
}
