import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/


class Block{
    char type;
    boolean visited;
    int visCnt;
    int x;
    int y;
    int dest_x;
    int dest_y;

    public Block(int x, int y, char s){
        this.type = s;
        this.x = x;
        this.y = y;
        this.visited = false;
        this.visCnt = 0;
        this.dest_x = -1;
        this.dest_y = -1;
    }
}

class BenderInst{
    int status;
    char dir;
    int pos_x;
    int pos_y;
    int inverted ;

    public BenderInst(int x, int y){
        this.status = 0;
        this.dir = 'S';
        this.pos_x = x;
        this.pos_y = y;
        this.inverted = -1;
    }
}


public class Solution {
    public static int[] cords(char dir){
        int add[] = new int[2];
        if(dir == 'S'){
            add[0]=1;
            add[1]=0;
        }else if(dir == 'E') {
            add[0] = 0;
            add[1] = 1;
        }else if(dir == 'N') {
            add[0] = -1;
            add[1] = 0;
        }else {
            add[0] = 0;
            add[1] = -1;
        }
        return add;
    }

    public static char get_new_dir(int cx, int cy, int nx, int ny){
        if(nx == cx){//Same row
            if(ny == cy+1){
                return 'E';
            }else{
                return 'W';
            }
        }else{
            if(nx == cx+1){
                return 'S';
            }else{
                return 'N';
            }
        }
    }

    public static char blocked_dir(char dir, int blocked, int inverted){
        /*if (dir=='S'){
            return 'E';
        }else if (dir =='E'){
            return 'N';
        }else if (dir == 'N'){
            return 'W';
        }else{
            return 'S';
        }
        */
        System.err.println(inverted);
        if (inverted==-1) {
            if (blocked == 0) {
                return 'S';
            } else if (blocked == 1) {
                return 'E';
            } else if (blocked == 2) {
                return 'N';
            } else {
                return 'W';
            }
        }else{
            if (blocked == 0) {
                return 'W';
            } else if (blocked == 1) {
                return 'N';
            } else if (blocked == 2) {
                return 'E';
            } else {
                return 'S';
            }
        }
    }
    String text = new String ("###############\n"+
                                "#  #@#I  T$#  #\n"+
                                "#  #    IB #  #\n"+
                                "#  #     W #  #\n"+
                                "#  #      ##  #\n"+
                                "#  #B XBN# #  #\n"+
                                "#  ##      #  #\n"+
                                "#  #       #  #\n"+
                                "#  #     W #  #\n"+
                                "#  #      ##  #\n"+
                                "#  #B XBN# #  #\n"+
                                "#  ##      #  #\n"+
                                "#  #       #  #\n"+
                                "#  #     W #  #\n"+
                                "#  #      ##  #\n"+
                                "#  #B XBN# #  #\n"+
                                "#  ##      #  #\n"+
                                "#  #       #  #\n"+
                                "#  #       #  #\n"+
                                "#  #      ##  #\n"+
                                "#  #  XBIT #  #\n"+
                                "#  #########  #\n"+
                                "#             #\n"+
                                "# ##### ##### #\n"+
                                "# #     #     #\n"+
                                "# #     #  ## #\n"+
                                "# #     #   # #\n"+
                                "# ##### ##### #\n"+
                                "#             #\n"+
                                "###############");

    public static char move (BenderInst b, int x, int y, Block[][] map, int rows, int columns){
        char dir = b.dir;
        int blocked = 0;

        for (int i = 0; i < 4; i++) {

            int newcords[] = cords(dir);
            int newx = x + newcords[0];
            int newy = y + newcords[1];

            if (map[newx][newy].type == '#'){
                map[newx][newy].visCnt += 1;
                if(map[newx][newy].visCnt >=8){
                    System.out.println("LOOP");
                    System.exit(0);
                }
            }
            //System.err.println(map[newx][newy].type);
            if ((map[newx][newy].type == ' ')||(map[newx][newy].type == '$')){
                b.dir = get_new_dir(b.pos_x, b.pos_y, newx, newy);
                b.pos_x = newx;
                b.pos_y = newy;
                return dir;
            }else if((map[newx][newy].type == 'I')){
                System.err.println("inverting");
                if(b.inverted==-1){
                    b.inverted=1;
                }else{
                    b.inverted=-1;
                }
                //b.dir = 'W';
                b.pos_x = newx;
                b.pos_y = newy;
                return dir;
            } else if (map[newx][newy].type == 'B') {
                b.dir = get_new_dir(b.pos_x, b.pos_y, newx, newy);
                b.pos_x = newx;
                b.pos_y = newy;
                if (b.status == 0) {
                    b.status = 1;
                } else {
                    b.status = 0;
                }
                return dir;
            } else if (map[newx][newy].type == 'X') {
                if (b.status == 1) {
                    b.dir = get_new_dir(b.pos_x, b.pos_y, newx, newy);
                    map[newx][newy].type = ' ';
                    b.pos_x = newx;
                    b.pos_y = newy;
                    return dir;
                }else{
                    System.err.println("blocked by X");
                    dir = blocked_dir(dir, blocked, b.inverted);
                    blocked +=1;
                }
            } else if (map[newx][newy].type == 'T') {
                b.dir = get_new_dir(b.pos_x, b.pos_y, newx, newy);
                //b.dir = 'S';
                b.pos_x = map[newx][newy].dest_x;
                b.pos_y = map[newx][newy].dest_y;;
                return dir;
            }else if (map[newx][newy].type == 'S') {
                //b.dir = get_new_dir(b.pos_x, b.pos_y, newx, newy);
                b.dir = 'S';
                b.pos_x = newx;
                b.pos_y = newy;
                return dir;
            } else if (map[newx][newy].type == 'E') {
                //b.dir = get_new_dir(b.pos_x, b.pos_y, newx, newy);
                b.dir = 'E';
                b.pos_x = newx;
                b.pos_y = newy;
                return dir;
            } else if (map[newx][newy].type == 'N') {
                //b.dir = get_new_dir(b.pos_x, b.pos_y, newx, newy);
                b.dir = 'N';
                b.pos_x = newx;
                b.pos_y = newy;
                return dir;
            } else if (map[newx][newy].type == 'W') {
                //b.dir = get_new_dir(b.pos_x, b.pos_y, newx, newy);
                b.dir = 'W';
                b.pos_x = newx;
                b.pos_y = newy;
                return dir;
            } else if (map[newx][newy].type == '#') {
                if (map[newx][newy].type == '#') {
                    System.err.println("blocked");
                    dir = blocked_dir(dir, blocked, b.inverted);
                    blocked+=1;
                }
            }
        }
        return b.dir;

    }
    public String[] directions = {"SOUTH", "EAST", "NORTH", "WEST"};
    // SOUTH EAST NORTH WEST BLOCK X BEER FREE GOAL
    public int[] blockTypes = new int[]{0, 1 ,2 ,3, 4, 5, 6, 7, 8};
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        BenderInst bender = new BenderInst(0, 0);
        int L = in.nextInt();
        int C = in.nextInt();
        Block[][] map = new Block[L][C];
        in.nextLine();
        int goal_x = 0;
        int goal_y = 0;
        int tc = 0;
        int des1x = -1;
        int des2x = -1;
        int des1y = -1;
        int des2y = -1;
        for (int i = 0; i < L; i++) {
            String row = in.nextLine();
            // System.err.println(row);
            for (int j = 0; j < C; j++){
                if(row.charAt(j)=='S'){
                    map[i][j]=new Block(i, j, 'S');
                }else if(row.charAt(j)=='E'){
                    map[i][j]=new Block(i, j, 'E');
                }else if(row.charAt(j)=='N'){
                    map[i][j]=new Block(i, j, 'N');
                }else if(row.charAt(j)=='W'){
                    map[i][j]=new Block(i, j, 'W');
                }else if(row.charAt(j)=='B'){
                    map[i][j]=new Block(i, j, 'B');
                }else if(row.charAt(j)=='X'){
                    map[i][j]=new Block(i, j, 'X');
                }else if(row.charAt(j)=='T'){
                    map[i][j]=new Block(i, j, 'T');
                    System.err.println(des2x+" "+des2y);
                    if (tc == 0){
                        tc+=1;
                        des2x = i;
                        des2y = j;
                        System.err.println(des2x+" "+des2y);
                    }else{
                        map[i][j].dest_x = des2x;
                        map[i][j].dest_y = des2y;
                        map[des2x][des2y].dest_x = i;
                        map[des2x][des2y].dest_y = j;
                        System.err.println(map[i][j].dest_x+" "+map[i][j].dest_y+" "+map[des2x][des2y].dest_x +" "+map[des2x][des2y].dest_y);
                    }
                    //System.exit(0);
                }else if(row.charAt(j)=='@'){
                    map[i][j]=new Block(i, j, ' ');
                    bender = new BenderInst(i, j);
                }else if(row.charAt(j)=='I') {
                    map[i][j] = new Block(i, j, 'I');
                }else if(row.charAt(j)=='$'){
                    map[i][j]=new Block(i, j, '$');
                    goal_x = i;
                    goal_y = j;
                }else if(row.charAt(j)=='#'){
                    //System.err.println(row.charAt(j));
                    map[i][j]=new Block(i, j, '#');
                }else{
                    map[i][j]=new Block(i, j, ' ');
                }
            }
        }
        ArrayList<String> course = new ArrayList<>();
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < C; j++) {
                if((i==bender.pos_x)&&(j==bender.pos_y)){
                    System.err.print('@');
                }else {
                    System.err.print(map[i][j].type);
                }
            }
            System.err.print("\n");
        }
        //System.exit(0);
        //course.add("SOUTH");
        int iter = 0;
        while (bender.pos_x!=goal_x || bender.pos_y!=goal_y) {
            System.err.println(iter);
            iter+=1;
            char b = move(bender, bender.pos_x, bender.pos_y, map, L, C);
            /*
            if (bender.dir == 'S'){
                course.add("SOUTH");
            }else if (bender.dir == 'E') {
                course.add("EAST");
            }else if (bender.dir == 'N') {
                course.add("NORTH");
            }else{
                course.add("WEST");
            }*/

            if (b == 'S'){
                course.add("SOUTH");
            }else if (b == 'E') {
                course.add("EAST");
            }else if (b == 'N') {
                course.add("NORTH");
            }else{
                course.add("WEST");
            }
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < C; j++) {
                    if((i==bender.pos_x)&&(j==bender.pos_y)){
                        System.err.print('@');
                    }else {
                        System.err.print(map[i][j].type);
                    }
                }
                System.err.print("\n");
            }
            System.err.println(bender.pos_x+" "+bender.pos_y+" "+goal_x+" "+goal_y+" "+bender.dir+" "+bender.inverted);
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
        }

        //Collections.reverse(course);
        for(int i = 0; i < course.size(); i++) {
            System.out.println(course.get(i));
        }
    }




}