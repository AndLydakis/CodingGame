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
        int min = 0;
        PhoneNumber root = new PhoneNumber(-1);
        int cost =0;
        for (int i = 0; i < N; i++) {
            String telephone = in.next();
            root.insert(telephone);
        }
        System.out.println(root.size); // The number of elements (referencing a number) stored in the structure.
    }
}

class PhoneNumber{
    static int size = 0;
    public int id;
    public PhoneNumber[] next;

    public PhoneNumber(int id){
        this.id = id;
        this.next = new PhoneNumber[10];
    }

    public void insert(String digits){
        if((digits.length())==0){return;}
        if(this.next[Character.getNumericValue(digits.charAt(0))]!=null){
            this.next[Character.getNumericValue(digits.charAt(0))].insert(digits.substring(1));
        }else{
            this.next[Character.getNumericValue(digits.charAt(0))] = new PhoneNumber(Character.getNumericValue(digits.charAt(0)));
            size++;
            this.next[Character.getNumericValue(digits.charAt(0))].insert(digits.substring(1));
        }

    }
}