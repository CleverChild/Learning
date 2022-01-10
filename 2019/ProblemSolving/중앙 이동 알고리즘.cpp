#include <iostream>
#include <cmath>
#include <string>
#include <algorithm>

using namespace std;

int main(void)
{
	int N,i=1;
	int row = 3;

	cin >> N;

	while (i < N)
	{
		row += pow(2, i);  //가로 길이 개수가 2^i만큼 계속 늘어나게됨
		i++;
	}

	// 정사각형이므로 넓이를 구한다는 의미로 가로 길이개수를 제곱하면 정답.
	cout << row * row << endl;

	return 0;
}