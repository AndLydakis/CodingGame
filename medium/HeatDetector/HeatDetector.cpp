#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <math.h>

using namespace std;

class Coords{
    public:
        int curX;
        int minX;
        int maxX;
        int curY;
        int minY;
        int maxY;
        Coords(int curX, int minX, int maxX, int curY, int minY, int maxY);
        void Up();
        void Down();
        void Right();
        void Left();
        void Print();
        
};

Coords::Coords(int cX, int mnX, int mxX, int cY, int mnY, int mxY){
    curX = cX;
    minX = mnX;
    maxX = mxX;
    curY = cY;
    minY = mnY;
    maxY = mxY;
}
void Coords::Print(){
    cerr<<curX<<" "<<curY<<" "<<minX<<" "<<minY<<" "<<maxX<<" "<<maxY<<endl;
}
void Coords::Up(){
    double prevY = curY;
    maxY = curY-1;
    curY = curY-1 - ceil((maxY - minY)/2);
    if(curY-prevY==0.0){curY--;}
}

void Coords::Down(){// Y INCREASES
    //min width and max width remain same
    double prevY = curY;
    minY = curY+1;
    curY = curY+1 + ceil((maxY - minY)/2);
    if(curY-prevY==0.0){curY++;}
}
    
void Coords::Right(){
    double prevX = curX;
    minX = curX+1;
    curX = curX+1 + ceil((maxX - minX)/2);
    if(curX-prevX==0.0){curX++;}
}

void Coords::Left(){
    double prevX = curX;
    maxX = curX-1;
    curX = curX-1 - ceil((maxX - minX)/2);
    if(curX-prevX==0.0){curX--;}
}
/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
int main()
{
    int W; // width of the building.
    int H; // height of the building.
    cin >> W >> H; cin.ignore();
    int N; // maximum number of turns before game over.
    cin >> N; cin.ignore();
    int X0;
    int Y0;
    cin >> X0 >> Y0; cin.ignore();
    int first = 0;
    Coords c = Coords(X0, 0, W-1, Y0, 0, H-1);
    // game loop
    while (1) {
        string BOMB_DIR; // the direction of the bombs from batman's
                        // current location (U, UR, R, DR, D, DL, L or UL)
        cin >> BOMB_DIR; cin.ignore();
        if(first == 0){
            first = 1;
            if(BOMB_DIR.find("D")!=std::string::npos){
                c.minY = Y0;
            }
            if(BOMB_DIR.find("U")!=std::string::npos){
                c.maxY = Y0;
            }
            if(BOMB_DIR.find("R")!=std::string::npos){
                c.minX = X0;
            }
            if(BOMB_DIR.find("L")!=std::string::npos){
                c.maxX = X0;
            }
            
        }
        c.Print();
        if(BOMB_DIR.compare("D")==0){ 
            c.Down();}
        else if(BOMB_DIR.compare("DR")==0){
            c.Down();
            c.Right();}
        else if(BOMB_DIR.compare("R")==0){
            c.Right();}
        else if(BOMB_DIR.compare("UR")==0){
            c.Up();
            c.Right();}
        else if(BOMB_DIR.compare("U")==0){
            c.Up();}
        else if(BOMB_DIR.compare("UL")==0){
            c.Up();
            c.Left();}
        else if(BOMB_DIR.compare("L")==0){
            c.Left();}
        else if(BOMB_DIR.compare("DL")==0){
            c.Down();
            c.Left();}
        

        cout << c.curX<<" "<<c.curY<< endl; // the location of the next window Batman should jump to.
    }
}


