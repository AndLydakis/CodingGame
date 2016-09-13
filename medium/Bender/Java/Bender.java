import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public String[] directions = {"SOUTH", "EAST", "NORTH", "WEST"};
    class Block{
        char type;
        boolean visited;
        int x;
        int y;

        public Block()
    }

    class Bender{
        int status;
        char dir;
        int pos_x;
        int pos_y;

        public Bender(){
            this.status = 0;
            this.dir = 'S';
        }
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Bender bender = new Bender();
        int L = in.nextInt();
        int C = in.nextInt();
        Block[][] map = new Block[L][C]
        in.nextLine();
        for (int i = 0; i < L; i++) {
            String row = in.nextLine();
            for (int j = 0; j < C; j++){
                if(row[j]=='S'){

                }else if(row[j]=='E'){

                }else if(row[j]=='N'){

                }else if(row[j]=='W'){

                }else if(row[j]=='T'){

                }else if(row[j]=='B'){

                }else if(row[j]=='@'){

                }else if(row[j]=='$'){

                }else if(row[j]=='#'){

                }else{

                }
            }
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println("answer");
    }

    public static void
}