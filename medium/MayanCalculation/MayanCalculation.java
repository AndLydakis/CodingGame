import java.util.*;
import java.io.*;
import java.math.*;

/**
 * .oo.o...oo..ooo.oooo....o...oo..ooo.oooo....o...oo..ooo.oooo....o...oo..ooo.oooo
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    static int log20(double x){int val;x+=1; for(val=0; x>20; x/=20, val++); return val;}
    static long exp20(int x){long  val=1; for(int i=0;i<x;i++)val*=20; return val;}

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int H = in.nextInt();
        int k;
        ArrayList<String> numerals = new ArrayList<String>();
        for (int i = 0; i < H; i++) {
            String numeral = in.next();
            System.err.println(numeral);
            for (int j = 0; j < numeral.length(); j++){
                String w = "";
                for (k = 0; k < L; k++, j++) {
                    w = w+numeral.charAt(j);
                }
                j--;
                //System.err.println(w+" "+j);
                numerals.add(new String(w));
            }
        }

        //for(int i = 0 ; i<20;i++){
        //     System.err.print(numerals.get(i));
        //}
        //System.err.println(numerals.get(0));
        //System.err.println(numerals.get(20));
        //System.err.println(numerals.get(40));
        //System.err.println(numerals.get(60));
        long S1 = in.nextInt();
        System.err.println(S1);
        ArrayList<String> s1 = new ArrayList<>();
        for (int i = 0; i < S1; i++) {
            String num1Line = in.next();
            s1.add(num1Line);
        }
        ArrayList<String> s2 = new ArrayList<>();
        long S2 = in.nextInt();
        System.err.println(S2);
        for (int i = 0; i < S2; i++) {
            String num2Line = in.next();
            s2.add(num2Line);
        }
        String operation = in.next();
        for (int i = 0; i < S1; i++) {
            System.err.println(s1.get(i));
        }
        long ss1 = 0;
        long ss2 = 0;
        int i;
        for(k = 0; k<(S1/H);k++){
            //System.err.println("$$$"+k+" "+S1/H);
            for(i = 0; i < 20 ; i++){
                int eq = 1;
                for(int j =0 ; j < H; j++){
                    if(!s1.get((k*H)+j).equals(numerals.get(i+20*j))){
                        eq = 0;
                        continue;
                    }
                }
                //System.err.println("-------------");
                if (eq == 1 ){
                    System.err.println(""+i+" * 20^"+((S1/H)-k-1)+" = "+(i*Math.pow(20, (S1/H)-k-1)));
                    ss1+=(i*Math.pow(20, (S1/H)-k-1));
                    //System.err.println("------"+i+"--------");
                }
            }
        }
        System.err.println(ss1);
        System.err.println("%%%%%%%%%%%%%%%%%%%%%%");
        for (i = 0; i < S2; i++) {
            System.err.println(s2.get(i));
        }
        for(k = 0; k<(S2/H);k++) {
            //System.err.println("$$$" + k + " " + S2 / H);
            for (i = 0; i < 20; i++) {
                int eq = 1;
                for (int j = 0; j < H; j++) {
                    //System.err.println(numerals.get(i+20*j));
                    if (!s2.get((k*H)+j).equals(numerals.get(i + 20 * j))) {
                        eq = 0;
                        break;
                    }
                }
                //System.err.println("-------------");
                if (eq == 1) {
                    System.err.println(""+i+" * 20^"+((S2/H)-k-1)+" = "+(i*Math.pow(20, (S2/H)-k-1)));
                    ss2+=(i*Math.pow(20, (S2/H)-k-1));
                    //System.err.println("------"+i+"--------");
                }
            }
        }
        System.err.println(ss2);
        System.err.println("%%%%%%%%%%%%%%%%%%%%%%");
        //System.exit(0);
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        long res = 0;
        if(operation.equals("+")){
            res=ss1+ss2;
        }else if(operation.equals("-")){
            res=ss1-ss2;
        }
        else if(operation.equals("*")){
            res=ss1*ss2;
        }
        else if (operation.equals("/")){
            res=ss1/ss2;
        }
        System.err.println("-------------");
        System.err.println(ss1+""+operation+""+ss2+"="+res);
        System.err.println("-------------");
        ArrayList<Integer> elems = new ArrayList<>();
        long element;
        long ex;
        int p = log20(res);
        while (p>=0){
            ex=exp20(p);
            System.err.println(ex);
            element = res/ex;
            System.err.println(element);
            for (int j = 0; j < H; j++) {
                System.out.println(numerals.get((int)element + 20 * j));
            }
            res=res-(element*ex);
            p--;
        }
    }
}