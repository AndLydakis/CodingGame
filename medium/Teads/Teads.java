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
        int n = in.nextInt(); // the number of adjacency relations
        System.err.println("N :"+n);
        int nAr[] = new int[2*n];
        for(int i = 0; i< n ;i++){nAr[i]=0;}
        Node nodes[] = new Node[2*n];
        HashMap<Integer, Node> hs = new HashMap<Integer, Node>();
        for (int i = 0; i < n; i++) {
            int xi = in.nextInt(); // the ID of a person which is adjacent to yi
            System.err.println("xi :"+xi);
            System.err.println();
            if(nAr[xi]==0){
                nAr[xi]=1;
                nodes[xi]= new Node(xi);
                hs.put(xi, nodes[xi]);
            }
            int yi = in.nextInt(); // the ID of a person which is adjacent to xi
            System.err.println("xi :"+xi +" yi :"+yi);
            if(nAr[yi]==0){
                nAr[yi]=1;
                nodes[yi] = new Node(yi);
                hs.put(yi, nodes[yi]);
            }
            nodes[xi].connections.add(nodes[yi]);
            nodes[yi].connections.add(nodes[xi]);
        }
        int turns = 0;
        HashMap<Integer, Node> leaves = new HashMap<Integer, Node>();
        while (hs.size()>1){
            for(Map.Entry<Integer, Node> entry : hs.entrySet()){
                if(entry.getValue().connections.size()==1){
                    leaves.put(entry.getKey(), entry.getValue());
                }
            }
            for(Map.Entry<Integer, Node> entry : leaves.entrySet()){
                entry.getValue().signalRemove();
                hs.remove(entry.getKey());
            }
            leaves.clear();
            turns++;
        }
        System.out.println(turns); // The minimal amount of steps required to completely propagate the advertisement
    }
    
    public static void reset(Node[] nodes, int size){
        for(int i = 0 ; i < size ; i++){
            nodes[i].knows = 0;
        }
    }
    
    public static boolean knows(Node[] nodes, int size){
        for(int i = 0 ; i < size ; i++){
            if(nodes[i].knows == 0){
                return false;
            }
        }
        return true;
    }
}


class Node{
    public ArrayList<Node> connections;
    public int knows;
    public int id ; 

    public Node(int id){
        this.id = id;
        this.knows = 0;
        this.connections=new ArrayList<Node>();
    }
    public void signalRemove(){
        for(Node n : this.connections){
            n.removeNode(id);
        }
    }
    public void removeNode(int id){
        for(int i = 0 ; i < this.connections.size(); i++){
            if(this.connections.get(i).id==id){
                this.connections.remove(i);
                return;
            }
        }
    }
}
