#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include <iostream>
#include <fstream>
#include <string>
#include <algorithm>
#include <vector>

int binarySearch(int* arr, int left, int right, int find); 
int compare(const void * a, const void * b);

int main(void)
{
	int i,j,len,temp=0;
	int n,m;
	int arr1[100002], arr2[100002];
	
	scanf("%d",&n);
	for(i=0;i<n;i++)
	{
		scanf("%d",&arr1[i]);
	}
	len=i;
	qsort(arr1, n, sizeof(int), compare);
	scanf("%d",&m);
	for(i=0;i<m;i++)
	{
		scanf("%d",&arr2[i]);
		if(binarySearch(arr1,0,len,arr2[i]))
		{
			printf("%d\n",1);
		}
		else
		{
			printf("%d\n",0);
		}
	}
	return 0;
}


int binarySearch(int* arr, int left, int right, int find) 
{
    while (left <= right) {
        int mid = (left + right) / 2;
        if (arr[mid] == find) {
            return 1;
        }
        else if (arr[mid] < find) {
            left = mid + 1;
        }
        else if (arr[mid] > find) {
            right = mid - 1;
        }
    }
    return 0;

}

int compare(const void * a, const void * b)
{
	return (*(int*)a > *(int*)b); 
}