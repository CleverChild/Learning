#include <iostream>
#include <algorithm>
using namespace std;
 
int dp[2][100001] = {0};
int a[2][100001] = {0};
int testCase;

int main()
{
	cin >> testCase;

	while(testCase--)
	{
		int n;
		cin >> n;

		for(int i=0;i<2;i++)
			for(int j=1;j<=n;j++)
				cin >> a[i][j];

		for(int i=1;i<=n;i++)
		{
			dp[0][i] = max(dp[0][i-1], dp[1][i-1] + a[0][i]);
			dp[1][i] = max(dp[1][i-1], dp[0][i-1] + a[1][i]);
		}
		cout << max(dp[0][n], dp[1][n]) <<endl;
	}
	
    return 0;
}