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
        int n = in.nextInt(); // the number of temperatures to analyse
        in.nextLine();
        String temps = in.nextLine(); // the n temperatures expressed as integers ranging from -273 to 5526
        String[] tokens = temps.split(" ");
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        int min = 100000000;
        int temp ;
        for(int i = 0; i< n;i++){
            if(tokens[i]==null){break;}
            System.err.println(tokens[i]);
            int num = Integer.parseInt(tokens[i]);
            temp = Math.abs(num);
            if(temp==Math.abs(min)){
                if(num<0){}
                else{min = temp;}
            }else if(temp<Math.abs(min)){
                min = num;
            }
            System.err.println(min);
            System.err.println("******");
        }
        if(min==100000000){min=0;}
        System.out.println(min);
    }
}
