#define	min(x,y) ((x) < (y) ? (x) : (y))

#include <stdio.h>

int main(void)
{
    int color[2001][2001] = {0,};
    int dp[2001] = {0,};
    int i,j,r,g,b,n;
    
    scanf("%d",&n);
    
    for(i=0;i<n;i++)
    {
        for(j=0;j<3;j++)
        {
            scanf("%d",&color[i][j]);    
        }
    }

    for(i=0;i<n;i++)
    {
        dp[i] = color[0][i];
    }

    for(i=1;i<n;i++)
    {
        r = dp[0];
        g = dp[1];
        b = dp[2];
        dp[0] = min(g, b) + color[i][0];
        dp[1] = min(r, b) + color[i][1];
        dp[2] = min(r, g) + color[i][2];
    }


    printf("%d\n",min(dp[0], min(dp[1], dp[2])));
    return 0;
}