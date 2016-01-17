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
    int L;
    cin >> L; cin.ignore();
    int H;
    cin >> H; cin.ignore();
    string T;
    getline(cin, T);
    locale loc;
    for (std::string::size_type i=0; i<T.length(); ++i){
        T[i]=std::toupper(T[i],loc);}
    vector<string> vec;        
    for (int i = 0; i < H; i++) {
        string ROW;
        getline(cin, ROW);
        vec.push_back(ROW);
    }
    cerr << T[0]-64<< endl;
    //cerr<< vec.size()<<endl;
    int position = 0;
    for(int a = 0; a < H ;a++){
        //for (int i = 0; i <25*L;i++){
            for(int j=0; j <T.length(); j++){
                if(isalpha(T[j])){position = ((T[j]-64)-1)*L ;}
                else{position = 26*L;}
                for(int k = position ;k<position+L;k++){
                    cout<<vec.at(a)[k];
                }
               
            }
            cout<<endl;
        //}
    }
    
    // Write an action using cout. DON'T FORGET THE "<< endl"
    // To debug: cerr << "Debug messages..." << endl;
}
