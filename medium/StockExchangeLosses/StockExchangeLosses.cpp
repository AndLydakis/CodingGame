#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
int main()
{
    int n;
    int max = -1;
    int min = pow(2,31);
    int maxDif = 0;
    cin >> n; cin.ignore();
    for (int i = 0; i < n; i++) {
        int v;
        cin >> v; cin.ignore();
        if(v>max){
            max=v;
        }else if( - abs(max-v)<maxDif){
            maxDif = -abs(max-v);
        }
    }
    // Write an action using cout. DON'T FORGET THE "<< endl"
    // To debug: cerr << "Debug messages..." << endl;
    cout << maxDif << endl;
}
