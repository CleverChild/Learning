#include <iostream>
#include <algorithm>
using namespace std;
 
int dp[501][2] = {0};
int stair[501] = {0};
int n;
  
int main()
{
    cin >> n;
	
	for(int i=0;i<n;i++)
	{
		cin >> stair[i];	
	}

	dp[0][0] = dp[0][1] = stair[0];
	dp[1][0] = stair[0] + stair[1];  //1칸전
	dp[1][1] = stair[1];

	for(int i=2;i<n;i++)
	{
		dp[i][0] = dp[i-1][1] + stair[i];  //1칸전
		dp[i][1] = max(dp[i-2][0], dp[i-2][1]) + stair[i]; //2칸전
	}

	cout << max(dp[n-1][0],dp[n-1][1]) << endl; 
    return 0;
}