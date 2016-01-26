#include <stdlib.h>
#include <stdio.h>
#include <string.h>

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
 struct Link{
	int a;
	int b;
	int active;
};
 
int main()
{
    int N; // the total number of nodes in the level, including the gateways
    int L; // the number of links
    int E; // the number of exit gateways
    scanf("%d%d%d", &N, &L, &E);
	struct Link links[L];
	int gateways[E];
    for (int i = 0; i < L; i++) {
        int N1; // N1 and N2 defines a link between these nodes
        int N2;
        scanf("%d%d", &N1, &N2);
		links[i].a = N1;
		links[i].b = N2;
		links[i].active = 1;
		fprintf(stderr,"%d %d %d\n",links[i].a, links[i].b, links[i].active);
    }
    for (int i = 0; i < E; i++) {
        int EI; // the index of a gateway node
        scanf("%d", &EI);
		gateways[i]=EI;
    }

    // game loop
    while (1) {
		int found = 0;
        int SI; // The index of the node on which the Skynet agent is positioned this turn
        scanf("%d", &SI);
		for(int i = 0; i < L;i++){
			if((links[i].a == SI||links[i].b == SI)&&(links[i].active==1)){				
				for(int j = 0; j<E ; j++){
					if((links[i].a == SI && gateways[j]==links[i].b)||
						(links[i].b == SI && gateways[j]==links[i].a)){
							links[i].active = 0;
							found = 1;
							printf("%d %d\n",links[i].a, links[i].b);
							break;
						}
				}
			}
		}
		
		if(found==0){
			for(int i = 0; i<L ;i++){
				for(int j = 0; j<E; j++){
					if(links[i].active==1 && (links[i].b==gateways[j]||links[i].a==gateways[j])){
						printf("%d %d\n",links[i].a, links[i].b);
						found = 1;
						links[i].active = 0;
						break;
					}
				}
				if(found == 1){break;}
			}
		}
		
		if(found==0){
			for(int i = 0 ; i < L; i++){
				if(links[i].active==1 && (links[i].b==SI || links[i].a==SI)){
					found = 1;
					links[i].active = 0;
					printf("%d %d \n",links[i].a, links[i].b);
					break;
				}
			}
		}
			
		
			
		if(found == 0){
			for(int i = 0 ; i<L; i++){
				if(links[i].active == 1){
					found = 1;
					links[i].active = 0;
					printf("%d %d\n",links[i].a, links[i].b);
					break;
				}
			}
		}
    }
    return 0;
}



struct Link *
createLink(int a, int b){
	struct Link *n;
	n = malloc(sizeof(struct Link));
	n->a = a;
	n->b = b;
	n->active = 1;
	return n;
}

void
destroyLink(struct Link *n){
	free(n);
}