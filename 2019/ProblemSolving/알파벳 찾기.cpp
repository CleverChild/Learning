#include <iostream>
#include <cmath>
#include <cstring>
#include <algorithm>

using namespace std;

int main()
{
	char S[101];
	char alpha_list[101] = "";
	int ans[101];
	fill_n(ans, 101, -1);
	int idx=0;

	cin >> S;
	for (int i = 0; i < strlen(S); i++)
	{
		alpha_list[i] = S[i];
	}

	idx = strlen(alpha_list) - 1;

	for (int i = strlen(alpha_list) - 1; i >= 0; i--)
	{
		ans[alpha_list[i] - 97] = idx;
		idx--;
	}

	for (int i = 0; i < 26; i++)
	{
		cout << ans[i] << ' ';
	}

	return 0;
}
