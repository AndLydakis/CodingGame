import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static int value(String word){
        int v = 0;

        return v;
    }
    public static void main(String args[]) {
        Map<Character, Integer> values = new HashMap<>();
        values.put('e',1);
        values.put('a',1);
        values.put('i',1);
        values.put('o',1);
        values.put('n',1);
        values.put('r',1);
        values.put('t',1);
        values.put('l',1);
        values.put('s',1);
        values.put('u',1);
        values.put('d',2);
        values.put('g',2);
        values.put('b',3);
        values.put('c',3);
        values.put('m',3);
        values.put('p',3);
        values.put('f',4);
        values.put('h',4);
        values.put('v',4);
        values.put('w',4);
        values.put('y',4);
        values.put('k',5);
        values.put('j',8);
        values.put('x',8);
        values.put('q',10);
        values.put('z',10);
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Map<String, Integer> map = new HashMap<String, Integer>();
        Map<String, Integer> indexes = new HashMap<String, Integer>();
        in.nextLine();
        for (int i = 0; i < N; i++) {
            String W = in.nextLine();
            int v = 0;
            for(int j = 0; j < W.length(); j++){
                v+=values.get(W.charAt(j));
            }
            map.put(W, v);
            indexes.put(W, i);
            System.err.println(W+" "+map.get(W));

        }
        String LETTERS = in.nextLine();
        Map<Character, Integer> oc = new HashMap<Character, Integer>();
        int lc;
        for(int i = 0; i < LETTERS.length(); i++) {
            lc = 0;
            for(int j = 0; j < LETTERS.length(); j++){
                if(LETTERS.charAt(i)==LETTERS.charAt(j)){
                    lc++;
                }
            }
            oc.put(LETTERS.charAt(i), lc);
        }
        System.err.println("--------------------------");
        String topWord = "";
        int topval = -1;
        int add ;
        Iterator it = map.entrySet().iterator();{
            while (it.hasNext()){
                Map.Entry pair = (Map.Entry)it.next();
                String w = (String)pair.getKey();
                //System.err.println(w);
                add = 1;
                for(int j = 0; j < w.length(); j++){
                    if(LETTERS.indexOf(w.charAt(j))>=0) {
                        lc = 0;
                        for(int k = 0; k < w.length(); k++){
                            if(w.charAt(k)==w.charAt(j)){
                                lc++;
                            }
                        }
                        if(lc>oc.get(w.charAt(j))) {
                            add = -1;
                            break;
                        }
                    }else{
                        add = -1;
                        break;
                    }
                }
                if (add==1){
                    if(map.get(w)>=topval){
                        System.err.println(w+" "+map.get(w));
                        if(map.get(w)>topval) {
                            topWord = w;
                            topval = map.get(w);
                        }else{
                            if(indexes.get(w)<indexes.get(topWord)){
                                topWord = w;
                                topval = map.get(w);
                            }
                        }
                    }
                }
            }
        }
        System.err.println(LETTERS);
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        if(topWord=="") {
            System.out.println("invalid word");
        }else{
            System.out.println(topWord);
        }
    }
}