import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        ArrayList<Defib> defibs = new ArrayList<Defib>();
        HashMap<Defib, Double> defibs2 = new HashMap<Defib, Double>();
        Scanner in = new Scanner(System.in);
        String LON = in.next();
        in.nextLine();
        String LAT = in.next();
        in.nextLine();
        int N = in.nextInt();
        in.nextLine();
        for (int i = 0; i < N; i++) {
            
            String DEFIB = in.nextLine();
            // System.out.println(DEFIB);
            DEFIB = DEFIB.replaceAll(",",".");
            // System.out.println(DEFIB);
            String[] tokens = DEFIB.split(";");
            // System.out.println(tokens);
            Defib newdef = new Defib();
            newdef.id = Integer.parseInt(tokens[0]);
            newdef.name = tokens[1];
            newdef.address = tokens[2];
            newdef.phone = tokens[3];
            // System.out.println( Double.parseDouble(tokens[4].replaceAll(",",".")));
            newdef.longi = Double.parseDouble(tokens[4].replaceAll(",","."));
            // System.out.println( Double.parseDouble(tokens[5].replaceAll(",",".")));
            newdef.lati = Double.parseDouble(tokens[5].replaceAll(",","."));
            newdef.setDistance(Double.parseDouble(LON.replaceAll(",",".")),
            Double.parseDouble(LAT.replaceAll(",",".")));
            defibs.add(newdef);
            
        }
        Collections.sort(defibs);
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(defibs.get(0).name);
    }
}

class user{
    public double longi;
    public double lati;
    
    public user(){
        
    }
}
class Defib implements Comparable<Defib>{
    public int id;
    public String name;
    public String address;
    public String phone;
    public double longi;
    public double lati;
    public double distance;
    public Defib(){
        
    }
    
    @Override
    public int compareTo(Defib otherDefib)
    {
        return Double.compare(distance, otherDefib.distance);
    }

    
    public void setDistance(double longB, double latB){
        double x = (longB - this.longi)*Math.cos((this.lati - latB)/2);
        double y = (latB - this.lati);
        this.distance = (Math.sqrt(Math.pow(x,2)+Math.pow(y,2)))*6371;
    }
}
