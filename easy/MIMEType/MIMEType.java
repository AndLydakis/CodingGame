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
        int N = in.nextInt(); // Number of elements which make up the association table.
        in.nextLine();
        int Q = in.nextInt(); // Number Q of file names to be analyzed.
        in.nextLine();
        String[] types = new String[N];
        String[] ext = new String[N];
        HashMap<String, String> table = new HashMap<String, String>();
        for (int i = 0; i < N; i++) {
            String EXT = in.next(); // file extension
            ext[i]=EXT;
            String MT = in.next(); // MIME type.
            types[i]=MT;
            in.nextLine();
            table.put(ext[i].toLowerCase(), types[i]);
        }
        for (int i = 0; i < Q; i++) {
            String FNAME = in.nextLine(); // One file name per line.
            String[] tokens = FNAME.toLowerCase().split("\\.",-1);
            // System.out.println(FNAME);
            // System.out.println(tokens.length);
            if(!FNAME.contains(".")){System.out.println("UNKNOWN");}
            // else if (tokens.length>2){System.out.println("2UNKNOWN");}
            else if(tokens[tokens.length-1].equals("")){System.out.println("UNKNOWN");}
            else{
                if(table.get(tokens[tokens.length-1].toLowerCase())!=null){
                    System.out.println(table.get(tokens[tokens.length-1].toLowerCase()));
                }else{
                     System.out.println("UNKNOWN");
                }
                
               
            }
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

         // For each of the Q filenames, display on a line the corresponding MIME type. If there is no corresponding type, then display UNKNOWN.
    }
}
