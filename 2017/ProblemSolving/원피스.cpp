#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void)
{
	char *str;
	char *str2;
	int cnt=0;

	str=(char *)malloc(100000);
	str2=(char *)malloc(17);
	scanf("%s",str);
	scanf("%s",str2);

	while(str=strstr(str,str2))
	{
		str+=strlen(str2);
		cnt++;
	}

	printf("%d\n",cnt);
	return 0;
}
