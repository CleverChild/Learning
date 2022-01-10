#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(void)
{
    int T, len, cnt = 0;
    char *str;
    char *str2;
    int i, j = 0;
    char temp;

    scanf("%d", &T);

    str = (char *)malloc(1000000);
    str2 = (char *)malloc(1000000);

	while (T--)
	{
		scanf("%s", str);
		scanf("%s", str2);
		//puts("");
		len = strlen(str2);

		for (i = 0; i<len; i++)
		{
			if (str[i] != str2[i])
			{
				//puts("^^");
				j = i;
				while (j + 1<=len)
				{
					if (str[i] == str2[j] && str[j] == str2[i])
					{
						temp = str[i];
						str[i] = str[j];
						str[j] = temp;
						//printf("%s\n",str);
						cnt++;
						break;
					}
					j++;
				}
			}
		}

		for (i = 0; i<len; i++)
		{
			if (str[i] != str2[i])
			{
				cnt++;
			}
		}

		printf("%d\n", cnt);
        cnt=0;
	}

	return 0;
}
