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
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int X0 = in.nextInt();
        int Y0 = in.nextInt();
        int HMIN = 0;
        int WMIN = 0;
        Coords c = new Coords(X0, WMIN, W-1, Y0, HMIN, H-1);
        // game loop
        while (true) {
            String BOMBDIR = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
            System.err.println(BOMBDIR);
            switch (BOMBDIR){
                case "D":
                    System.err.println(c.curX+" "+c.curY+" "+c.minX+" "+c.minY+" "+c.maxX+" "+c.maxY);
                    c.Down();
                    break;
                case "DR":
                    System.err.println(c.curX+" "+c.curY+" "+c.minX+" "+c.minY+" "+c.maxX+" "+c.maxY);
                    c.Down();
                    c.Right();
                    break;
                case "R":
                    System.err.println(c.curX+" "+c.curY+" "+c.minX+" "+c.minY+" "+c.maxX+" "+c.maxY);
                    c.Right();
                    break;
                case "UR":
                    System.err.println(c.curX+" "+c.curY+" "+c.minX+" "+c.minY+" "+c.maxX+" "+c.maxY);
                    c.Up();
                    c.Right();
                    break;
                case "U":
                    System.err.println(c.curX+" "+c.curY+" "+c.minX+" "+c.minY+" "+c.maxX+" "+c.maxY);
                    c.Up();
                    break;
                case "UL":
                    System.err.println(c.curX+" "+c.curY+" "+c.minX+" "+c.minY+" "+c.maxX+" "+c.maxY);
                    c.Up();
                    c.Left();
                    break;
                case "L":
                    System.err.println(c.curX+" "+c.curY+" "+c.minX+" "+c.minY+" "+c.maxX+" "+c.maxY);
                    c.Left();
                    break;
                case "DL":
                    c.Down();
                    c.Left();
                    System.err.println(c.curX+" "+c.curY+" "+c.minX+" "+c.minY+" "+c.maxX+" "+c.maxY);
                    break;
                default:
                    break;
            }
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.println((int)c.curX+" "+(int)c.curY); // the location of the next window Batman should jump to.
        }
    }
}

class Coords{
    public double curX;
    public double minX;
    public double maxX;
    public double curY;
    public double minY;
    public double maxY;
    
    public Coords(int curX, int minX, int maxX, int curY, int minY, int maxY){
        this.curX = curX;
        this.minX = minX;
        this.maxX = maxX;
        this.curY = curY;
        this.minY = minY;
        this.maxY = maxY;
    }
    
    public void Up(){// Y DECREASES
        //min width and max width remain same
        //min Y also remains same
        double prevY = this.curY;
        this.maxY = this.curY;
        this.curY = this.curY - Math.ceil((this.maxY - this.minY)/2);
        if(this.curY == prevY){this.curY--;}
    }
    public void Down(){// Y INCREASES
        //min width and max width remain same
        double prevY = this.curY;
        this.minY = this.curY;
        this.curY = this.curY + Math.ceil((this.maxY - this.minY)/2);
        if(this.curY == prevY){this.curY++;}
    }
    
    public void Right(){
        double prevX = this.curX;
        this.minX = this.curX;
        this.curX = this.curX + Math.ceil((this.maxX - this.minX)/2);
        if(this.curX==prevX){this.curX++;}
    }
    
    public void Left(){
        double prevX = this.curX;
        this.maxX = this.curX;
        this.curX = this.curX - Math.ceil((this.maxX - this.minX)/2);
        if(this.curX==prevX){this.curX--;}
    }
}
