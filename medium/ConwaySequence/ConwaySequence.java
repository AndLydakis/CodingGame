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
        int R = in.nextInt();
        int L = in.nextInt();

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
		Conway(Integer.toString(R), L);
    }
    
    public static void Conway(String s, int l){
		String tokens[] = s.split(" ");
		if(l == 1){
			for(int i = 0; i <tokens.length-1; i ++){
				System.out.print(tokens[i]+" ");
			}
			System.out.println(tokens[tokens.length-1]);
			return;
		}
		String sequence = "";
		String val = tokens[0];
		int counter = 1;
		for(int i = 1; i < tokens.length; i++){
			System.err.println(val+" "+tokens[i]);
			if(val.equals(tokens[i])){
				counter++;
			}else{
				sequence+=" "+counter+" "+val ;
				val = tokens[i] ;
				counter = 1;
			}
		}
		sequence+=" "+counter+" "+val ;
		sequence=sequence.trim();
		Conway(sequence, l-1);
		
    }
}
