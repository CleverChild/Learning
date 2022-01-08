#include <iostream>
#include <cmath>
#include <string>
#include <algorithm>

using namespace std;

char input_data[51][51];

int black_check(int x, int y,int i, int j)
{
	char map1[8][8] = {{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},
	{'B','W','B','W','B','W','B','W'}, {'W','B','W','B','W','B','W','B'},
	{'B','W','B','W','B','W','B','W'}, {'W','B','W','B','W','B','W','B'},
	{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'} };

	if (input_data[x][y] != map1[x-i][y-j])
	{
		return 1;
	}
	else
	{
		return 0;
	}
}

int white_check(int x, int y, int i, int j)
{
	char map2[8][8] = {{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},
	{'W','B','W','B','W','B','W','B'} ,{'B','W','B','W','B','W','B','W'},
	{'W','B','W','B','W','B','W','B'} ,{'B','W','B','W','B','W','B','W'},
	{'W','B','W','B','W','B','W','B'} ,{'B','W','B','W','B','W','B','W'}};

	if (input_data[x][y] != map2[x-i][y-j])
	{
		return 1;
	}
	else
	{
		return 0;
	}
}

int main(void)
{
	int i, j, x, y;
	int N, M;
	int ans=999;
	int cnt1 = 0, cnt2 = 0;
	
	cin >> N >> M;

	for (i = 0; i < N; i++)
	{
		for (j = 0; j < M; j++)
		{
			cin >> input_data[i][j];
		}
	}

	for (j = 0; j <= M - 8; j++)
	{
		for (i = 0; i <= N - 8; i++)
		{
			for (x = i; x < i + 8; x++)
			{
				for (y = j; y < j + 8; y++)
				{
					cnt1 += black_check(x, y, i, j);
					cnt2 += white_check(x, y, i, j);
				}
			}
			ans = min(ans,min(cnt1, cnt2));
			cnt1 = 0;
			cnt2 = 0;
		}
	}
	
	cout << ans << endl;

	return 0;
}