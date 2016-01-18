import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Don't let the machines win. You are humanity's last hope...
 **/
class Player {

    public static void main(String args[]) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // the number of cells on the X axis
        int height = in.nextInt(); // the number of cells on the Y axis
        in.nextLine();
        for (int i = 0; i < height; i++) {
            String line = in.nextLine(); // width characters, each either 0 or .
            for(int j = 0 ;j <width; j++){
                if(line.charAt(j)!='.'){
                    System.err.println(j +" "+i);
                    nodes.add(new Node(j, i));
                }
            }
        }
        for(Node n1 : nodes){
            for(Node n2 : nodes){
                if((n1.x != n2.x)&&(n1.y == n2.y)){
                    if((n1.x < n2.x)&&((n1.rx == -1)||(n1.rx > n2.x))){
                        n1.setRX(n2.x, n2.y);
                    }
                }
                if((n1.x == n2.x)&&(n1.y != n2.y)){
                    if((n1.y < n2.y)&&((n1.by == -1)||(n1.by > n2.y))){
                       n1.setBX(n2.x, n2.y);
                    }
                }
            }
        }
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        for(Node n : nodes){
            System.out.println(n.x+" "+n.y+" "+n.rx+" "+n.ry+" "+n.bx+" "+n.by);
        }
        //System.out.println("0 0 1 0 0 1"); // Three coordinates: a node, its right neighbor, its bottom neighbor
    }
}    
    class Node{
    public int x;
    public int y;
    public int rx;
    public int ry;
    public int bx;
    public int by;
    
    public Node(int x, int y){
        this.x = x;
        this.y = y;
        this.rx = -1;
        this.ry = -1;
        this.bx = -1;
        this.by = -1;
        System.err.println(this.x+" "+this.y+" "+this.rx+" "+this.ry+" "+this.bx+" "+this.by);
    }
    
    public void setRX(int x, int y){
         System.err.println("RX :"+x+" RY :"+y);
        this.rx = x;
        this.ry = y;
    }
    
    public void setBX(int x, int y){
        System.err.println("BX :"+x+" BY :"+y);
        this.bx = x;
        this.by = y;
    }
}
