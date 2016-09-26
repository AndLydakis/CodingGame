import java.util.*;
import java.io.*;
import java.math.*;

/**
 * .oo.o...oo..ooo.oooo....o...oo..ooo.oooo....o...oo..ooo.oooo....o...oo..ooo.oooo
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

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
        int S1 = in.nextInt();
        ArrayList<String> s1 = new ArrayList<>();
        for (int i = 0; i < S1; i++) {
            String num1Line = in.next();
            s1.add(num1Line);
        }
        ArrayList<String> s2 = new ArrayList<>();
        int S2 = in.nextInt();
        for (int i = 0; i < S2; i++) {
            String num2Line = in.next();
            s2.add(num2Line);
        }
        String operation = in.next();
        for (int i = 0; i < H; i++) {
            System.err.println(s1.get(i));
        }
        for (int i = 0; i < H; i++) {
            System.err.println(s2.get(i));
        }
        System.err.println("-------------");
        int stidx = 0;
        int ss1 = 0;
        int ss2 = 0;
        for(int i = 0; i < 20 ; i++){
            int eq = 1;
            for(int j =0 ; j < H; j++){
                //System.err.println(numerals.get(i+20*j));
                if(!s1.get(j).equals(numerals.get(i+20*j))){
                    eq = 0;
                    break;
                }
            }
            //System.err.println("-------------");
            if (eq == 1 ){
                ss1 = i;
            }
        }
        //System.err.println(ss1);

        for(int i = 0; i < 20 ; i++){
            int eq = 1;
            for(int j =0 ; j < H; j++){
                //System.err.println(numerals.get(i+20*j));
                if(!s2.get(j).equals(numerals.get(i+20*j))){
                    eq = 0;
                    break;
                }
            }
            //System.err.println("-------------");
            if (eq == 1 ){
                ss2 = i;
            }
        }
        // System.err.println(ss2);

        //System.exit(0);
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        long res = 0;
        if(operation.equals("+")){
            res=ss1+ss2;
        }else if(operation.equals("-")){
            res=ss1-ss2;
            if (res<0){
                res=0;
            }
        }
        else if(operation.equals("*")){
            res=ss1*ss2;
        }
        else if (operation.equals("/")){
            res=ss1/ss2;
        }
        double power = 0.0;
        double div = 1;
        while(Math.pow(power, 20.0)<res){
            System.err.println(Math.pow(20.0, power));
            div = Math.pow(power, 20.0);
            power+=1;
        }
        //power-=1;
        System.err.println("-------------");
        System.err.println(ss1);
        System.err.println(operation);
        System.err.println(ss2);
        System.err.println(res);
        System.err.println(power);
        //System.out.println("result");
        double rem = (double)res;
        System.err.println("-------------");
        ArrayList<Integer> elems = new ArrayList<>();
        long element;

        while (res>0){
            element = res/20;
            elems.add((int)element);
            res = res/20;
        }
        elems.add((int)res);
        System.err.println("-------------");
        if(res!=0) {
            elems.add((int)res);
        }

        for(int i = 0 ;i < elems.size();i++){
            //if(elems.get(i)>0) {
                for (int j = 0; j < H; j++) {
                    System.out.println(numerals.get(elems.get(i) + 20 * j));
                }
            //}
        }
    }
}