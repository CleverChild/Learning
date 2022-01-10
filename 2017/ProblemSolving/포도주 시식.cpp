#include <iostream>
#include <algorithm>
using namespace std;
int m[10001];
int dp[10001][3];
int n;

int main()
{
    cin >> n;
    
    for (int i = 1; i <= n; ++i)  
        cin >> m[i];

    dp[1][1] = m[1];
    dp[2][0] = m[1];
    dp[2][1] = m[2];
    dp[2][2] = dp[1][1] + m[2];

    for (int i = 3; i <= n; ++i)
    {
        dp[i][0] = max(max(dp[i - 1][1], dp[i - 1][2]),dp[i-1][0]);
        dp[i][1] = dp[i - 1][0] + m[i];
        dp[i][2] = dp[i - 1][1] + m[i]; 
    }

    cout << max(dp[n][0],max(dp[n][1], dp[n][2])) << endl;

    return 0;
}