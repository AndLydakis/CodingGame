import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        ArrayList<Elevator> elev = new ArrayList<Elevator>();
        Scanner in = new Scanner(System.in);
        int nbFloors = in.nextInt(); // number of floors
        int width = in.nextInt(); // width of the area
        int nbRounds = in.nextInt(); // maximum number of rounds
        int exitFloor = in.nextInt(); // floor on which the exit is found
        int exitPos = in.nextInt(); // position of the exit on its floor
        int nbTotalClones = in.nextInt(); // number of generated clones
        int nbAdditionalElevators = in.nextInt(); // ignore (always zero)
        int nbElevators = in.nextInt(); // number of elevators
        for (int i = 0; i < nbElevators; i++) {
            int elevatorFloor = in.nextInt(); // floor on which this elevator is found
            int elevatorPos = in.nextInt(); // position of the elevator on its floor
            elev.add(new Elevator(elevatorFloor, elevatorPos));
        }

        // game loop
        while (true) {
            int cloneFloor = in.nextInt(); // floor of the leading clone
            int clonePos = in.nextInt(); // position of the leading clone on its floor
            String direction = in.next(); // direction of the leading clone: LEFT or RIGHT

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            
            if(atEdge(clonePos, direction, width)){
                System.out.println("BLOCK");
                System.err.println("atEdge");
            }else if(elevBlock(cloneFloor, clonePos, direction, elev)){
                System.out.println("BLOCK");
                System.err.println("elevBlock");
            }else if(lastFloor(cloneFloor, clonePos, direction, exitFloor, exitPos)){
                System.out.println("BLOCK");
                System.err.println("lastFloor");
            }
            else{System.out.println("WAIT");}
        }
    }
    
    public static boolean lastFloor(int cloneFloor, int clonePos, String dir,
                int lastFloor, int lastPos){
        if(cloneFloor == lastFloor){
            if((clonePos < lastPos)&&(dir.equals("LEFT"))){return true;}
            if((clonePos > lastPos)&&(dir.equals("RIGHT"))){return true;}
        }
        return false;
    }
    public static boolean atEdge(int pos, String dir, int w){
        System.err.println(pos+" "+dir+" "+w);
        if((pos == w-1)&&(dir.equals("RIGHT"))){return true;}
        if((pos == 0)&&(dir.equals("LEFT"))){return true;}
        return false;
    }
    
    public static boolean elevBlock(int cloneFloor, int clonePos, 
                    String dir, ArrayList<Elevator> elev){
        for(Elevator e : elev){
            if(cloneFloor == e.floor){
                if((clonePos < e.pos)&&(dir.equals("LEFT"))){return true;}
                if((clonePos > e.pos)&&(dir.equals("RIGHT"))){return true;}
            }
        }
        return false;
    }
}

class Elevator{
    public int floor;
    public int pos;
    public Elevator(int f, int p){
        this.floor = f;
        this.pos = p;
    }
}
