#include <iostream>
#include <cmath>
#include <string>
#include <algorithm>

using namespace std;

int main(void)
{
	long long arr[5001], total = 9223372036854775807;
	int N;
	int i;
	int fix, start, end;
	int a, b, c;

	cin >> N;

	for (i = 0; i < N; i++)
	{
		cin >> arr[i];
	}

	sort(arr, arr + N);

	for (i = 0; i < N - 2; i++)
	{
		fix = i;
		start = fix + 1;
		end = N - 1;
		
		while (true)
		{
			if (start == end) break;
			
			if (abs(arr[fix] + arr[start] + arr[end]) < total)
			{
				a = i;
				b = start;
				c = end;
			}

			total = min(total, abs(arr[fix] + arr[start] + arr[end]));

			if (arr[fix] + arr[start] + arr[end] > 0)
			{
				end--;
			}
			else if (arr[fix] + arr[start] + arr[end] < 0)
			{
				start++;
			}
			else
			{
				i = N;
				break;
			}
		}
	}

	cout << arr[a] << ' ' << arr[b] << ' ' << arr[c] << endl;

	return 0;
}