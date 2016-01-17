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
       
        int N = in.nextInt();
        int[] diff = new int[N];
        for (int i = 0; i < N; i++) {
            int pi = in.nextInt();
            diff[i]=pi;
        }
        Arrays.sort(diff);
        int minDiff = diff[1]-diff[0];
        for (int i = 2 ; i != diff.length ; i++) {
            minDiff = Math.min(minDiff, diff[i]-diff[i-1]);
        }
        System.out.println(minDiff);
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
    }
}
