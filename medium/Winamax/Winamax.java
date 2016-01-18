import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Player p1 = new Player();
        Player p2 = new Player();
        ArrayList<Card> warPileP1 = new ArrayList<Card> ();
        ArrayList<Card> warPileP2 = new ArrayList<Card> ();
        int n = in.nextInt(); // the number of cards for player 1
        for (int i = 0; i < n; i++) {
            String cardp1 = in.next(); // the n cards of player 1
            p1.deck.add(new Card (cardp1.substring(0,cardp1.length()-1),
                cardp1.substring(cardp1.length()-1)));
        }
        int m = in.nextInt(); // the number of cards for player 2
        //System.err.println("p2");
        for (int i = 0; i < m; i++) {
            String cardp2 = in.next(); // the m cards of player 2
            p2.deck.add(new Card(cardp2.substring(0,cardp2.length()-1),
                cardp2.substring(cardp2.length()-1)));
        }
        //System.err.println(p1.deck.size());
        //System.err.println(p2.deck.size());
        //System.err.println("=============");
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        //FIGHT
        int rounds = 0;
        while((p1.deck.size()>0)&&(p2.deck.size()>0)){
            System.err.println(rounds+" "+p1.deck.size()+" "+p2.deck.size()+"****");
            int res = winFight(p1, p2);
            if(res == 1){
                System.err.println("p1 win");
                warPileP1.add(p1.deck.remove(0));
                warPileP2.add( p2.deck.remove(0));
                p1.deck.addAll(warPileP1);
                p1.deck.addAll(warPileP2);
                warPileP1.clear();
                warPileP2.clear();
                rounds++;
            }else if(res == -1){
                System.err.println("p2 win");
                warPileP1.add(p1.deck.remove(0));
                warPileP2.add(p2.deck.remove(0));
                p2.deck.addAll(warPileP1);
                p2.deck.addAll(warPileP2);
                warPileP1.clear();
                warPileP2.clear();
                rounds++;
            }else{
                System.err.println("tie");
                for(int i = 0 ; i < 4 ;i++){
                    if((p1.deck.size()>0)&&(p2.deck.size()>0)){
                        warPileP1.add(p1.deck.remove(0));
                        warPileP2.add(p2.deck.remove(0));
                    }else{
                        System.out.println("PAT");
                        return;
                    }
                }
            }
            
        }
        System.err.println(rounds+" "+p1.deck.size()+" "+p2.deck.size());
        System.err.println("========================");
        if(p1.deck.size()>0){
            System.out.println(1+" "+rounds);
        }else{
            System.out.println(2+" "+rounds);
        }
    }
    public static int winFight(Player p1, Player p2){
        //System.err.println(p1.deck.size());
        //System.err.println(p2.deck.size());
        System.err.println(p1.deck.get(0).cardVal()+" "+p2.deck.get(0).cardVal());
        if(p1.deck.get(0).value > p2.deck.get(0).value){return 1;}
        else if(p1.deck.get(0).value < p2.deck.get(0).value){return -1;}
        else{return 0;}
    }
}


class Card{
    String val;
    String type;
    int value;
    public Card(String val, String type){
        this.val = val;
        this.type = type;
        this.value = cardVal();
    }
    public int cardVal(){
        if (val.equals("J")){return 11;}
        if (val.equals("Q")){return 12;}
        if (val.equals("K")){return 13;}
        if (val.equals("A")){return 14;}
        else{return Integer.parseInt(val);}
    }
}

class Player{
    ArrayList<Card> deck;
    public Player(){
        this.deck = new ArrayList<Card> ();
    }
}
