#include <iostream>
#include <algorithm>
using namespace std;
 
int dp[501] = {0};
int testCase;

int main()
{
	int n;
	dp[0] = 0;
	dp[1] = 1;
	dp[2] = 2;
	dp[3] = 4;

    cin >> testCase;
	
	while(testCase--)
	{
		cin >> n;
		
		for(int i=4;i<=n;i++)
		{
			dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
		}
		cout << dp[n] << endl;
	}
	
    return 0;
}