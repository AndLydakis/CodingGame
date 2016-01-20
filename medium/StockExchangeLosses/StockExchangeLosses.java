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
        int maxDif = 0;
        int cur_min = (int)Math.pow(2, 31);
        int cur_max = 0;
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            if(v>cur_max){
                cur_max = v;
            }else{
                if(-Math.abs((cur_max - v))<maxDif){
                    maxDif = -Math.abs((cur_max - v));
                    cur_min = v;
                }
            }
        }
        System.out.println(maxDif);
    }
}
