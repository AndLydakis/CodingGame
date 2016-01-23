#include <iostream>
#include <sstream>
#include <string>
#include <cstring>
#include <vector>
#include <algorithm>
#include <iterator>

using namespace std;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
 
void GoThroughRoom(int XI, int YI, string POS, int TYPE);

int main()
{
    int W; // number of columns.
    int H; // number of rows.
    cin >> W >> H; cin.ignore();
    int rooms[W][H];
    vector<std::string> tokens;
    for (int i = 0; i < H; i++) {
        string LINE; // represents a line in the grid and contains W integers. Each integer represents one room of a given type.
        getline(cin, LINE);
        std::istringstream buf(LINE);
        int j = 0;
        for(istream_iterator<std::string> beg(buf);
            beg!=istream_iterator<std::string>( ); beg++){
                rooms[j][i]=stoi(*beg);
                j++;
            }
        cerr<<endl;
    }
    int EX; // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).
    cin >> EX; cin.ignore();

    // game loop
    while (1) {
        int XI;
        int YI;
        string POS;
        cin >> XI >> YI >> POS; cin.ignore();
        GoThroughRoom(XI, YI, POS, rooms[XI][YI]);
    }
}

void GoThroughRoom(int XI, int YI, string POS, int TYPE){
    switch(TYPE){
            case 0:
                break;
            case 1:
                YI++;
                break;
            case 2:
                if(POS=="LEFT"){XI++;}
                else if(POS=="RIGHT"){XI--;}
                break;
            case 3:
                YI++;
                break;
            case 4:
                if(POS=="TOP"){XI--;}
                else if(POS=="RIGHT"){YI++;}
                break;
            case 5:
                if(POS=="TOP"){XI++;}
                else if(POS=="LEFT"){YI++;}
                break;
            case 6:
                if(POS=="LEFT"){XI++;}
                else if(POS=="RIGHT"){XI--;}
                break;
            case 7:
                YI++;
                break;
            case 8:
                YI++;
                break;
            case 9:
                YI++;
                break;
            case 10:
                if(POS=="TOP"){XI--;}
                break;
            case 11:
                if(POS=="TOP"){XI++;}
                break;
            case 12:
                YI++;
                break;
            case 13:
                YI++;
                break;
            default:
                break;
        }
    cout<<XI<<" "<<YI<<endl;
}


