#include <iostream>
#include <cmath>
#include <string>
#include <algorithm>

using namespace std;

int main(void)
{
	long long DP[100001], val=-992299, arr[100001];
	int i, N;

	cin >> N;

	for (i = 0; i < N; i++)
	{
		cin >> arr[i];
	}

	DP[0] = arr[0];
    val = DP[0];

	for (i = 1; i < N; i++)
	{
		DP[i] = max(DP[i - 1] + arr[i], arr[i]);
		val = max(val, DP[i]);
	}

	cout << val << endl;
	
	return 0;
}