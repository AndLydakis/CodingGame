#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * ---
 * Hint: You can use the debug stream to print initialTX and initialTY, if Thor seems not follow your orders.
 **/
int main()
{
    int lightX; // the X position of the light of power
    int lightY; // the Y position of the light of power
    int initialTX; // Thor's starting X position
    int initialTY; // Thor's starting Y position
    cin >> lightX >> lightY >> initialTX >> initialTY; cin.ignore();
    int thorX = initialTX;
    int thorY = initialTY;
    // game loop
    while (1) {
        int remainingTurns;
        int directionX;
        int directionY;
        
        if(thorX>lightX){
            if(thorY>lightY){
                cout << "NW" << endl;
                thorX--;
                thorY--;
            }
            else if(thorY==lightY){
                cout<<"W"<<endl;
                 thorX--;
            }
            else{
                cout<<"SW"<<endl;
                 thorX--;
                 thorY++;
            }
        }
        else if(thorX<lightX){
            if(thorY>lightY){
                cout << "NE" << endl;
                thorX++;
                thorY--;
            }
            else if(thorY==lightY){
                cout<<"E"<<endl;
                 thorX++;
            }
            else{
                cout<<"SE"<<endl;
                 thorX++;
                 thorY++;
            }
        }
        else{
             if(thorY>lightY){
                cout << "N" << endl;
                thorY--;
             }
            else{
                cout << "S" << endl;
                thorY++;
            }
        }
        cin >> remainingTurns; cin.ignore();

        // Write an action using cout. DON'T FORGET THE "<< endl"
        // To debug: cerr << "Debug messages..." << endl;

         // A single line providing the move to be made: N NE E SE S SW W or NW
    }
}
