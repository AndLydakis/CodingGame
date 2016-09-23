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
        int C = in.nextInt();
        int[] budgets = new int[N];
        int[] contributions = new int [N];
        for (int i = 0; i < N; i++) {
            budgets[i] = in.nextInt();
            contributions[i]=0;
            System.err.println(budgets[i]);
        }
        System.err.println("-----------");
        int sum = 0;
        while(sum<C){
            int prevsum = sum;
            for (int i = 0; i < N; i++) {
                if(sum >= C){
                    break;
                }
                if (contributions[i] < budgets[i]){
                    contributions[i]+=1;
                    sum+=1;
                }
            }
            if (prevsum==sum){
                System.out.println("IMPOSSIBLE");
                System.exit(0);
            }
        }
        Arrays.sort(contributions);
        for (int i = 0; i < N; i++) {
            System.out.println(contributions[i]);
        }
    }
}