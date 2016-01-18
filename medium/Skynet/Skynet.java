import java.util.*;
import java.io.*;
import java.math.*;


/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int found = 0;
        ArrayList<Link> links = new ArrayList<Link>();
        ArrayList<Integer> gateways = new ArrayList<Integer>();
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways
        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();
            links.add(new Link(N1, N2));
        }
        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            gateways.add(new Integer(EI));
        }

        // game loop
        while (true) {
            found = 0;
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            for(Link l : links){
                if((l.a == SI || l.b ==SI)&&(l.active ==1)){
                    //System.err.println(SI+" "+l.a+" "+l.b+" "+l.active+" a");
                    for(int g : gateways){
                        System.err.println("g :"+g);
                        if((l.a == SI && g== l.b) || (l.b == SI && g == l.a)){
                            System.err.println(SI+" "+l.a+" "+l.b+" "+l.active+" a");
                            System.out.println(l.a+" "+l.b);
                            l.active = 0;
                            found = 1;
                            break;
                       }
                    }
                }
            }
             if(found == 0){
                for(Link l : links){
                    //System.err.println(SI+" "+l.a+" "+l.b+" "+l.active+" b");
                    if(l.active == 1 && (l.b == SI || l.a == SI)){
                        System.err.println(SI+" "+l.a+" "+l.b+" "+l.active+" c");
                        System.out.println(l.a+" "+l.b);
                        found = 1;
                        l.active = 0;
                        break;
                   }
                }
            }
            
            if(found == 0){
                for(Link l : links){
                    for(int g : gateways){
                        if(l.active == 1 && (l.b == g || l.a == g)){
                            System.err.println(SI+" "+l.a+" "+l.b+" "+l.active+" b");
                            System.out.println(l.a+" "+l.b);
                            found = 1;
                            l.active = 0;
                            break;
                        }
                    }
                    if(found ==1){break;}
                }
            }
           
            
            if(found == 0){
                 for(Link l : links){
                     if(l.active == 1){
                        System.err.println(SI+" "+l.a+" "+l.b+" "+l.active+" d");
                        System.out.println(l.a+" "+l.b);
                        found = 1;
                        l.active = 0;
                        break;
                     }
                }
                
            }
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            //System.out.println("0 1"); // Example: 0 1 are the indices of the nodes you wish to sever the link between
        }
    }
}

class Link{
    public int a;
    public int  b;
    public int active;
    public Link(int a, int b){
        this.a = a;
        this.b = b;
        this.active = 1;
    }
}
