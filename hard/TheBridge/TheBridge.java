import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    static class Bike {
        int x;
        int y;
        int a;

        public Bike(int x, int y, int a) {
            this.x = x;
            this.y = y;
            this.a = a;
        }
        public Bike(Bike b) {
            this.x = b.x;
            this.y = b.y;
            this.a = b.a;
        }
    }
    static class State implements Comparable<State>{

        public static PriorityQueue<State> finals = new PriorityQueue<>();

        int level;
        ArrayList<String> path;
        Bike bikes[];
        int map[][];
        int M;
        int V;
        int S;
        int L;

        @Override
        public int compareTo(final State o){
            return Integer.compare(this.M, o.M);
        }

        public State(int map[][], Bike bikes[], int M, int V, int S){
            this.M = M;
            this.V = V;
            this.S = S;
            this.L = 0;
            this.map = new int[map.length][map[0].length];
            for(int i = 0 ; i< map.length;i++){
                for(int j = 0 ; j<map[0].length;j++){
                    this.map[i][j]=map[i][j];
                }
            }
            this.bikes = new Bike[M];
            for(int i = 0 ; i< bikes.length;i++){
                this.bikes[i]=new Bike(bikes[i]);
            }
            this.path = new ArrayList<>();
        }

        public State(State s){
            this.M = s.M;
            this.V = s.V;
            this.S = s.S;
            this.L = s.L;
            this.level = s.level;
            this.map = new int[s.map.length][s.map[0].length];
            for(int i = 0 ; i< s.map.length;i++){
                for(int j = 0 ; j<s.map[0].length;j++){
                    this.map[i][j]=s.map[i][j];
                }
            }
            this.bikes = new Bike[s.bikes.length];
            for(int i = 0 ; i < s.bikes.length;i++){
                this.bikes[i]=new Bike(s.bikes[i]);
            }
            this.path = new ArrayList<>();
            for(String st : s.path)this.path.add(new String(st));
        }

        public void print(){
            int p = 0;
            System.err.println(this.map.length+" "+this.map[0].length+" "+this.M+" "+this.S+" "+this.level);
            for(int i = 0; i < this.map.length;i++){
                for(int j = 0; j < this.map[0].length;j++){
                    for(int k = 0; k <this.bikes.length;k++){
                        if((bikes[k].a==1)&&(bikes[k].y==i)&&(bikes[k].x==j)){
                            System.err.print('@');
                            p=1;
                        }
                    }
                    if(p==0) {
                        System.err.print(this.map[i][j]);
                    }
                    p=0;
                }
                System.err.println("");
            }
            System.err.println(V+" motorbikes need to survive");
            for(int i = 0 ; i < this.bikes.length;i++){
                System.err.println("Bike #"+i+": "+bikes[i].y+" "+bikes[i].x+" "+bikes[i].a+" "+this.S);
            }
        }

        public boolean isFinal(){
            int vv = 0;
            for(Bike b : this.bikes){
                if((b.a==1)&&(b.x>=this.map[0].length-1)){
                    vv++;
                }
            }
            if(vv>= this.V){
                this.print();
                return true;
            }
            return false;
        }

        public void DFS(){
            String choices[]= {"SPEED","JUMP","SLOW","UP","DOWN"};
            this.print();
            for(String com : choices){
                this.dfs(com);
            }
        }

        public void dfs(String com) {
            if(this.isFinal()){
                State.finals.add(this);
                /*
                for(int i = 0 ; i < this.path.size();i++){
                    System.out.println(this.path.get(i));
                }
                */
                if (State.finals.size()>50){
                    State n = State.finals.poll();
                    for(int i = 0 ; i < n.path.size();i++){
                        System.out.println(n.path.get(i));
                    }
                }
                System.err.println(State.finals.size());
//                System.exit(0);

                return;
            }
            State s = new State(this);
            if(s.level==50){
                System.err.println("*"+s.level);
                return;
            }
            s.level++;
//            s.print();
            int ydes = 0;
            if((com=="SPEED")&&(s.S<50)){
                s.S+=1;
            }
            if((com!="SPEED")&&(s.S<1)){
                return;
            }
            if(com=="SLOW"){
                if(s.S>=2){
                    s.S-=1;
                }else{
                    return;
                }
            }
            if(com=="DOWN"){
                ydes = 1;
            }
            if(com=="UP"){
                ydes = -1;
            }
            if((com=="SLOW")||(com=="SPEED")){
                for (Bike b : s.bikes) {
                    if (b.a == 1) {
                        int des = b.x + s.S;
                        if (des >= s.map[0].length) {
                            des = s.map[0].length - 1;
                        }
                        for (int i = b.x; i <= des; i++) {
                            if (s.map[b.y][i] == 0) {
                                b.a = 0;
                                s.M--;
                                if(s.M<s.V){return;}
                                break;
                            }
                        }
                        if(b.a==1) {
                            b.x = des;
                        }
                    }
                }
                s.path.add(com);
                s.DFS();
            }
            if(com=="JUMP"){
                for (Bike b : s.bikes) {
                    if (b.a == 1) {
                        int des = b.x + s.S;
                        if (des >= s.map[0].length) {
                            des = s.map[0].length - 1;
                        }
                        if (s.map[b.y][des] == 0) {
                            b.a = 0;
                            s.M--;
                            if(s.M<s.V){return;}
                        } else {
                            b.x = des;
                        }
                    }
                }
                s.path.add(com);
                s.DFS();
            }
            if((com=="UP")||(com=="DOWN")) {
                for (Bike b : s.bikes) {
                    if((b.a==1)&&((b.y + ydes >= s.map.length) || (b.y + ydes < 0))) {
                        s.level--;
                        s.dfs("JUMP");
                        return;
                    }
                }
                for (Bike b : s.bikes) {
                    if (b.a == 1) {
                        int des = b.x + s.S;
                        if (des >= s.map[0].length) {
                            des = s.map[0].length - 1;
                        }
                        for(int i = b.x ; i <= des-1;i++){
                            if((map[b.y][i]==0)||((map[b.y+ydes][i]==0))){
                                b.a=0;
                                s.M--;
                                if(s.M<s.V){return;}
                                break;
                            }
                        }
                        if((b.a==1)&&(map[b.y+ydes][des]==0)){
                            b.a=0;
                            s.M--;
                            if(s.M<s.V){return;}
                        }else{
                            b.x=des;
                            b.y+=ydes;
                        }
                    }
                }
                s.path.add(com);
                s.DFS();
            }
            return;
        }


        public State step(String com){
            if(this.isFinal()) {
              return this;
            }
            State s = new State(this);
            if(s.level==30){
                System.err.println("*"+s.level);
                return null;
            }
            s.level++;
//            s.print();
            int ydes = 0;
            if((com=="SPEED")&&(s.S<50)){
                s.S+=1;
            }
            if((com!="SPEED")&&(s.S<1)){
                return null;
            }
            if(com=="SLOW"){
                if(s.S>=2){
                    s.S-=1;
                }else{
                    return null;
                }
            }
            if(com=="DOWN"){
                ydes = 1;
            }
            if(com=="UP"){
                ydes = -1;
            }
            if((com=="SLOW")||(com=="SPEED")){
                for (Bike b : s.bikes) {
                    if (b.a == 1) {
                        int des = b.x + s.S;
                        if (des >= s.map[0].length) {
                            des = s.map[0].length - 1;
                        }
                        for (int i = b.x; i <= des; i++) {
                            if (s.map[b.y][i] == 0) {
                                b.a = 0;
                                s.M--;
                                if(s.M<s.V){return null;}
                                break;
                            }
                        }
                        if(b.a==1) {
                            b.x = des;
                        }
                    }
                }
                s.path.add(com);
                return s;
            }
            if(com=="JUMP"){
                for (Bike b : s.bikes) {
                    if (b.a == 1) {
                        int des = b.x + s.S;
                        if (des >= s.map[0].length) {
                            des = s.map[0].length - 1;
                        }
                        if (s.map[b.y][des] == 0) {
                            b.a = 0;
                            s.M--;
                            if(s.M<s.V){return null;}
                        } else {
                            b.x = des;
                        }
                    }
                }
                s.path.add(com);
                return s;
            }
            if((com=="UP")||(com=="DOWN")) {
                for (Bike b : s.bikes) {
                    if((b.a==1)&&((b.y + ydes >= s.map.length) || (b.y + ydes < 0))) {
                        s.level--;
                        return s;
                    }
                }
                for (Bike b : s.bikes) {
                    if (b.a == 1) {
                        int des = b.x + s.S;
                        if (des >= s.map[0].length) {
                            des = s.map[0].length - 1;
                        }
                        for(int i = b.x ; i <= des-1;i++){
                            if((map[b.y][i]==0)||((map[b.y+ydes][i]==0))){
                                b.a=0;
                                s.M--;
                                if(s.M<s.V){return null;}
                                break;
                            }
                        }
                        if((b.a==1)&&(map[b.y+ydes][des]==0)){
                            b.a=0;
                            s.M--;
                            if(s.M<s.V){return null;}
                        }else{
                            b.x=des;
                            b.y+=ydes;
                        }
                    }
                }
                s.path.add(com);
                return s;
            }
            return null;
        }

    }
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int M = in.nextInt(); // the amount of motorbikes to control
        int V = in.nextInt(); // the minimum amount of motorbikes that must survive
        String L0 = in.next(); // L0 to L3 are lanes of the road. A dot character . represents a safe space, a zero 0 represents a hole in the road.
        String L1 = in.next();
        String L2 = in.next();
        String L3 = in.next();
        int L = L0.length();
        int map[][]= new int[4][L0.length()+30];
        for(int i =0 ; i < L0.length(); i++){
            if(L0.charAt(i)=='.'){
                map[0][i]=1;
            }else{
                map[0][i]=0;
            }
            if(L1.charAt(i)=='.'){
                map[1][i]=1;
            }else{
                map[1][i]=0;
            }
            if(L2.charAt(i)=='.'){
                map[2][i]=1;
            }else{
                map[2][i]=0;
            }
            if(L3.charAt(i)=='.'){
                map[3][i]=1;
            }else{
                map[3][i]=0;
            }
        }

        for(int i =L0.length()-1 ; i < L0.length()+30; i++) {
            map[0][i]=1;
            map[1][i]=1;
            map[2][i]=1;
            map[3][i]=1;
        }

        System.err.println(map.length);
        System.err.println(map[0].length);
        System.err.println(L0);
        System.err.println(L1);
        System.err.println(L2);
        System.err.println(L3);
        // game loop
        int S = in.nextInt(); // the motorbikes' speed
        Bike bikes[]= new Bike[M];
        for (int i = 0; i < M; i++) {
            int X = in.nextInt(); // x coordinate of the motorbike
            int Y = in.nextInt(); // y coordinate of the motorbike
            int A = in.nextInt(); // indicates whether the motorbike is activated "1" or detroyed "0"
            bikes[i] = new Bike(X, Y, A);
            System.err.println("Bike #"+i+": "+bikes[i].y+" "+bikes[i].x+" "+bikes[i].a+" "+S);

        }
        State s = new State(map, bikes, M, V, S);
        s.level=1;
        /*
        Stack<State> queue = new Stack<>();
        queue.add(s);
//        String choices[]= {"SPEED", "SLOW", "JUMP", "UP", "DOWN"};
        String choices[]= {"SPEED","JUMP","SLOW","UP","DOWN"};
        PriorityQueue<State> finals = new PriorityQueue<>();
        while (!queue.isEmpty()) {
            State current = new State(queue.pop());
            System.err.println(queue.size());
            if(current.isFinal()){
                System.err.println("YOLO");
                current.print();
                finals.add(current);
            }else {
                for (String c : choices) {
                    State newState = current.step(c);
                    if (newState != null) {
//                    System.err.println("===========   "+c+"    ================");
//                    current.step(c).print();
                        queue.push(new State(current.step(c)));
                        break;
                    }
                }
            }
        }
        System.err.println("-------------------------------");
        State Best = new State(finals.poll());
        for (int i =0 ; i < Best.path.size();i++){
            System.out.println(Best.path.get(i));
        }
        */
        s.DFS();
//        System.err.println(State.finals.size());
    }
}