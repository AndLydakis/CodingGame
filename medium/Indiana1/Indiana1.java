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
        int W = in.nextInt(); // number of columns.
        int H = in.nextInt(); // number of rows.
        in.nextLine();
        int rooms[][] = new int[W][H];
        for (int i = 0; i < H; i++) {
            String LINE = in.nextLine(); // represents a line in the grid and contains W integers. Each integer represents one room of a given type.
            String tokens[] = LINE.split(" ");
            for(int j = 0 ; j<tokens.length; j++){
                rooms[j][i] = Integer.parseInt(tokens[j]);
            }
        }
        int EX = in.nextInt(); // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).
        int direction  = -1;
        // game loop
        while (true) {
            int XI = in.nextInt();
            int YI = in.nextInt();
            String POS = in.next();
            GoThroughRoom(XI, YI, POS, rooms[XI][YI]);
        }
    }
    
    
    public static void GoThroughRoom(int XI, int YI, String POS, int TYPE){
        System.err.println("GoThroughRoom "+XI+" "+YI+" "+POS+" "+TYPE);
        switch(TYPE){
            case 0:
                break;
            case 1:
                YI++;
                break;
            case 2:
                switch(POS){
                    case"LEFT":
                        XI++;
                        break;
                    case"RIGHT":
                        XI--;
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                YI++;
                break;
            case 4:
                switch(POS){
                    case"TOP":
                        XI--;
                        break;
                    case"RIGHT":
                        YI++;
                        break;
                    default:
                        break;
                }
                break;
            case 5:
                switch(POS){
                    case"TOP":
                        XI++;
                        break;
                    case"LEFT":
                        YI++;
                        break;
                    default:
                        break;
                }
                break;
            case 6:
                switch(POS){
                    case"LEFT":
                        XI++;
                        break;
                    case"RIGHT":
                        XI--;
                        break;
                    default:
                        break;
                }
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
                switch(POS){
                    case("TOP"):
                        XI--;
                        break;
                }
                break;
            case 11:
                switch(POS){
                    case("TOP"):
                        XI++;
                        break;
                }
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
        System.out.println(XI+" "+YI);
    }
}



class Room{
    int x;
    int y;
    int TYPE;
    public Room(int x, int y, int TYPE){
        this.x = x;
        this.y = y;
        this.TYPE = TYPE;
    }
    
    public void Rotate(){
        
    }
}
