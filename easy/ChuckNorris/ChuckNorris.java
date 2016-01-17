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
        String MESSAGE = in.nextLine();
        String answer = "";
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
         byte[] bytes = MESSAGE.getBytes();  
          StringBuilder binary = new StringBuilder();  
          for (byte b : bytes)  
          {  
             int val = b;  
             for (int i = 0; i <8; i++)  
             {  
                binary.append((val & 128) == 0 ? 0 : 1);  
                val <<= 1;  
             }  
            // binary.append(' ');  8
          } 
          int zero_cnt = 0;
          int one_cnt = 0;
          int prev = -1;
        //   System.out.println(binary.toString().substring(1));  
          for(int i = 1; i < binary.length();i++){
              if(i%8==0){continue;}
              if(binary.charAt(i)=='0'){
                  if(prev == 1){
                    answer+=" 0 ";
                    for(int j = 0 ; j<one_cnt;j++){
                        answer+="0";   
                    } 
                    one_cnt = 0;
                    zero_cnt++;
                    prev = 0;
                  }else{
                    zero_cnt++;
                    prev = 0;
                  }
              }else{
                  if(prev == 0){
                    answer+=" 00 "; 
                    for(int j = 0; j< zero_cnt ; j ++){
                          answer+="0";
                    }
                    zero_cnt=0;
                    one_cnt++;
                    prev = 1;
                  }else{
                    one_cnt++;
                    prev = 1;
                  }
              }
          }
          if(zero_cnt!=0){
            answer+=" 00 "; 
                    for(int j = 0; j< zero_cnt ; j ++){
                          answer+="0";
                    }  
          }
          if(one_cnt!=0){
            answer+=" 0 ";
                    for(int j = 0 ; j<one_cnt;j++){
                        answer+="0";   
                    }   
          }
        //   System.out.println(binary.toString());  
        System.out.println(answer.trim());
    }
}
