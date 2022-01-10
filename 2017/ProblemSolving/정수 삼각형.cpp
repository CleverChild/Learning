#include <iostream>
#include <algorithm>
using namespace std;
 
int dp[501][501] = {0};
int n;
  
int main()
{
    cin >> n;
	
	for(int i=0;i<n;i++)
	{
		for(int j=0;j<i+1;j++)
		{
			cin >> dp[i][j];
		}
	}

	for(int i=1;i<n;i++)
	{
		for(int j=0;j<i+1;j++)
		{
			if(!j)
			{ 
				dp[i][j] += dp[i-1][j]; 
			}
			else if(j==i) 
			{
				dp[i][j] += dp[i-1][j-1];
			} 
			else 
			{
				dp[i][j] = max(dp[i][j] + dp[i-1][j-1], dp[i][j] + dp[i-1][j]);
			}
		}
	}

	int v = 0;

	for(int j=0;j<n;j++)
	{
		if(v < dp[n-1][j])
		{
			v = dp[n-1][j];
		}
	}

	cout << v << endl;

    return 0;
}