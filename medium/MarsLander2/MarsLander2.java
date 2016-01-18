import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    
    public static final double GRAVITY = 3.711;
    public static final int MAX_VSPEED = 40;
    public static final int MAX_HSPEED = 40;
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        ArrayList<Point> surface = new ArrayList<Point>();
        int startX = -1 ; 
        int startY = -1; 
        int endX = -1 ; 
        int endY = -1;
        Lander l = new Lander(0, 0);
        int surfaceN = in.nextInt(); // the number of points used to draw the surface of Mars.
        for (int i = 0; i < surfaceN; i++) {
            int landX = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
            int landY = in.nextInt(); // Y coordinate of a surface point.
            if(startX != -1){
                if(landY == startY){
                    endX = landX;
                    endY = landY;
                }else{
                    if((endX - startX)<1000){
                        startX = -1;
                    }
                }
            }
            if(startX==-1){
                startX = landX;
                startY = landY;
                endX = landX;
                endY = endY;
            }
            //By linking all the points together in a sequential fashion, you form the surface of Mars.
        }
        Point start = new Point(startX, startY);
        surface.add(start);
        Point end = new Point(endX, endY);
        surface.add(end);
        
        // game loop
        while (true) {
            System.err.println(startX+" "+startY+" "+endX+" "+endY);
            l.pos.x = in.nextInt();
            l.pos.y = in.nextInt();
            l.hSpeed = in.nextInt(); // the horizontal speed (in m/s), can be negative.
            l.vSpeed = in.nextInt(); // the vertical speed (in m/s), can be negative.
            l.fuel = in.nextInt(); // the quantity of remaining fuel in liters.
            l.rotate = in.nextInt(); // the rotation angle in degrees (-90 to 90).
            l.power = in.nextInt(); // the thrust power (0 to 4).
            //System.err.println(surface.get(0).x+" "+surface.get(surface.size()-1).x +" "+l.pos.x);
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            if((l.pos.x >= surface.get(0).x) && (l.pos.x <= surface.get(1).x)){
                if((Math.abs(l.hSpeed)<=MAX_HSPEED)&&(Math.abs(l.vSpeed)<=MAX_VSPEED)){
                    l.rotate = 0;
                    l.power = 3;
                }else{
                    l.calculateTilt();
                    l.power = 4;
                }
            }else{
                if((l.pos.x < surface.get(0).x && (l.hSpeed <= 0))||
                    ((l.pos.x > surface.get(1).x)&&(l.hSpeed >= 0))||
                    (l.hSpeed>MAX_HSPEED)||
                    (l.vSpeed>MAX_VSPEED)){
                        System.err.println("1");
                        l.calculateTilt();
                        l.power = 4;
                    }
                else{
                    l.rotate = 0;
                    l.power = 2;
                }
            }
            System.out.println(l.rotate+" "+l.power);
            //System.out.println(1+" "+1);
            //System.out.println("-20 3"); // rotate power. rotate is the desired rotation angle. power is the desired thrust power.
        }
    }
}


class Point{
    public int x;
    public int y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Lander{
    public Point pos;
    public int hSpeed;
    public int vSpeed;
    public int fuel;
    public int rotate;
    public int power;
    
    public Lander(int x, int y){
        this.pos = new Point(x, y);
    }
    
    public void DecreaseRotate(int i){
        System.err.println("Decrease Rotate");
        if(i == 0){
            this.rotate -= 15;
        }else{
            this.rotate -= i;
        }
    }
    
    public void IncreaseRotate(int i){
        System.err.println("Increase Rotate");
        if(i == 0){
            this.rotate += 15;
        }else{
            this.rotate += i;
        }
    }
    
    public void calculateTilt(){
        double hVel = Math.pow(this.hSpeed, 2);
        double vVel = Math.pow(this.vSpeed, 2);
        double Vel = Math.sqrt(hVel + vVel);
        double rotation = Math.asin((double)hVel/Vel);
        this.rotate = (int) Math.toDegrees(rotation);
        System.err.println("Rotate :"+this.rotate);
    }
}
