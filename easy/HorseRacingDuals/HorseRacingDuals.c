#include <stdlib.h>
#include <stdio.h>
#include <string.h>

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
int cmpfunc (const void * a, const void * b)
{
   return ( *(int*)a - *(int*)b );
}
int min(int a, int b){
	if(a<b){return a;}
	else{return b;}
}

int main()
{
    int N;
	
    scanf("%d", &N);
	int diff[N];
    for (int i = 0; i < N; i++) {
        //int Pi;
        scanf("%d", &diff[i]);
    }
	qsort(diff, N, sizeof(int), cmpfunc);
	int minDiff = diff[1]-diff[0];
	for (int i = 2 ; i != N ; i++) {
        minDiff = min(minDiff, diff[i]-diff[i-1]);
    }
    // Write an action using printf(). DON'T FORGET THE TRAILING \n
    // To debug: fprintf(stderr, "Debug messages...\n");

    printf("%d\n", minDiff);

    return 0;
}

