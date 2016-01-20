#include <stdlib.h>
#include <stdio.h>
#include <string.h>

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
int main()
{
    int n;
    int max = -1;
    int maxDif = 0;
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        int v;
        scanf("%d", &v);
        if(v>max){
            max=v;
        }else if( - abs(max-v)<maxDif){
            maxDif = -abs(max-v);
        }
    }
    printf("%d\n",maxDif);

    return 0;
}
