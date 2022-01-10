#include <iostream>
#include <cmath>
#include <cstring>
#include <algorithm>

using namespace std;

int main(void)
{
	int N,i;
	int cnt = 0, total = 0, save_i=0;

	cin >> N;

	// N을 이용해서 total을 구하고
	for (i = 1; total < N; i++)
	{
		total += i;
	}

	// i가 몇 까지 돌았는지 체크해서 save_i에 저장한다.
	// ex) N = 2,3이라면 save_i는 2까지만
	// ex) N = 4,5,6이라면 save_i는 3까지만
	save_i = i - 1;

	// i값은 total로 다시 바뀌며,
	// total을 이용해서 cnt를 구한다.
	for (i = total; i > N; i--)
	{
		cnt += 1;
	}
	//이 cnt로 분자(또는 분모)값을 쉽게 결정할 수 있다.

	// i가 짝수일때와 홀수일때를 구분해서 출력순서만 바꿔주면 끝.
	if (save_i % 2 == 0)
	{
		cout << save_i - cnt << '/' << save_i - (save_i - cnt) + 1 << endl;
	}
	else
	{
		cout << save_i - (save_i - cnt) + 1 << '/' << save_i - cnt << endl;
	}

	return 0;
}