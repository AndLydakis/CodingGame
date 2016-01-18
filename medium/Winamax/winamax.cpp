#include <iostream>
#include <string.h>
#include <deque>
#include <algorithm>

using namespace std;

class Card{
    public:
        int value;
        Card(string s);
        Card(int v);
};

Card::Card(string s){
    
    string val = s.substr(0, s.length()-1);
    //cerr<<val<<endl;
    if(val=="A"){value = 14;}
    else if (val=="K") {value = 13;}
    else if (val=="Q"){value = 12;}
    else if (val=="J") {value = 11;}
    else value = atoi(val.c_str());
    /*
    if (s.compare("A")==0) {value = 14;}
    else if (s.compare("K")==0) {value = 13;}
    else if (s.compare("Q")==0) {value = 12;}
    else if (s.compare("J")==0) {value = 11;}
    else value = atoi(val.c_str());
    */
    //cerr<<value<<endl;
    //cerr<<"---------"<<endl;
}

Card::Card(int v){
    value = v;
}

class Player{
    public:
        std::deque<Card> deck;
        Player();
};
Player::Player(){}
/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
int winFight(Player p1, Player p2);

int main()
{
    int n; // the number of cards for player 1
    int rounds=0;
    cin >> n; cin.ignore();
    Player p1 ;
    Player p2 ;
    std::deque<Card> warPileP1;
    std::deque<Card> warPileP2;
    for (int i = 0; i < n; i++) {
        string cardp1; // the n cards of player 1
        cin >> cardp1; cin.ignore();
        p1.deck.push_back(Card(cardp1));
    }
    int m; // the number of cards for player 2
    cin >> m; cin.ignore();
    for (int i = 0; i < m; i++) {
        string cardp2; // the m cards of player 2
        cin >> cardp2; cin.ignore();
        p2.deck.push_back(Card(cardp2));
    }
    cerr<<p1.deck.size()<<" "<<p2.deck.size()<<endl;
    // Write an action using cout. DON'T FORGET THE "<< endl"
    // To debug: cerr << "Debug messages..." << endl;
    while((p1.deck.size()>0)&&(p2.deck.size()>0)){
        cerr<<p1.deck.size()<<" "<<p2.deck.size()<<endl;
        int res = winFight(p1, p2);
        if(res==1){
            //cerr<<"p1 win"<<endl;
            warPileP1.push_back(p1.deck.front());
            p1.deck.pop_front();
            warPileP2.push_back(p2.deck.front());
            p2.deck.pop_front();
            p1.deck.insert(p1.deck.end(), warPileP1.begin(), warPileP1.end());
            p1.deck.insert(p1.deck.end(), warPileP2.begin(), warPileP2.end());
            warPileP1.clear();
            warPileP2.clear();
            rounds++;
        }else if (res==-1){
            //cerr<<"p2 win"<<endl;
            warPileP1.push_back(p1.deck.front());
            p1.deck.pop_front();
            warPileP2.push_back(p2.deck.front());
            p2.deck.pop_front();
            p2.deck.insert(p2.deck.end(), warPileP1.begin(), warPileP1.end());
            p2.deck.insert(p2.deck.end(), warPileP2.begin(), warPileP2.end());
            warPileP1.clear();
            warPileP2.clear();
            rounds++;
        }else{
            //cerr<<"tie"<<endl;
            for(int i = 0; i<4; i++){
                if((p1.deck.size()>0)&&(p2.deck.size()>0)){
                        warPileP1.push_back(p1.deck.front());
                        p1.deck.pop_front();
                        warPileP2.push_back(p2.deck.front());
                        p2.deck.pop_front();
                    }else{
                        cout<<"PAT"<<endl;
                        return 0;
                    }
            }
        }
    }
    if(p1.deck.size()>0){
        cout <<1<<" "<<rounds<<endl;
        return 0;
    }else if(p2.deck.size()>0){
        cout <<2<<" "<<rounds<<endl;
        return 0;
    }else{
        cout << "PAT" << endl;
        return 0;}
}


int winFight(Player p1, Player p2){
        //System.err.println(p1.deck.size());
        //System.err.println(p2.deck.size());
        //std::cerr<<" "<<p1.deck.front().value<<" "<<p2.deck.front().value<<endl;
        if(p1.deck.front().value > p2.deck.front().value){return 1;}
        else if(p1.deck.front().value < p2.deck.front().value){return -1;}
        else{return 0;}
    }

