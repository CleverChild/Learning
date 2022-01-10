#include <iostream>
#include <cmath>
#include <algorithm>
using namespace std;

int main(void)
{
	//idx 는 {1,2,3,4,5} 에서 {2,3,4}로 넘어갈때 사용하며, 
	//arr[501]를 [1]부터 하게끔 위한 변수임
	int N, K, save_standard_deviation_cnt = 0, idx = 0;
	long long arr[501], arr_total = 0;
	long double  m = 0;

	cin >> N >> K;
	//int 형인 N이나 K로 나누면 정밀도에서 오류 발생. 따라서 long double divider을 선언해줌.
	long double  divider = 0, dispersion_total = 0, standard_deviation = 2147483647, temp_standard_deviation = 0;

	for (int i = 0; i < N; i++)
	{
		cin >> arr[i];
	}

	while (idx <= (N - K))
	{
		for (int i = K; i <= N - idx; i++)
		{
			// K의 값에 따라 divider를 다르게 설정해야함.  ex ) {1,2,3} {1,2,3,4} {1,2,3,4,5}
			divider = i;
			// 배열 요소 합 구하기
			for (int j = 0; j < i; j++)
			{
				arr_total += arr[j + idx];
			}
			// 평균 구하기
			m = arr_total / divider;

			// (a1 - m)^2 + (a2 - m)^2 + … + (aN - m)^2
			for (int kk = 0; kk < i; kk++)
			{
				dispersion_total += pow((arr[kk + idx] - m), 2);
			}

			//제곱근(sqrt)해서 표준편차 구하기
			temp_standard_deviation = sqrt((dispersion_total / divider));
			standard_deviation = min(standard_deviation, temp_standard_deviation);
			arr_total = 0;
			dispersion_total = 0;
			m = 0;
		}
		idx++;
	}

	cout << fixed; cout.precision(11);
	cout << standard_deviation << endl;
}