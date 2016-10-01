import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.nio.file.Path;
import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.Matcher;

class Player {

    /**
     * Auto-generated code below aims at helping you parse
     * the standard input according to the problem statement.
     **/
    static class Block implements Comparable<Block>{
        int x;
        int y;
        char type = '?';
        int h;
        int g;
        int f;
        int visited = 0;
        int color = 0;
        String dir = "";
        Block parent;

        Block(int x, int y, char type){
            this.x = x;
            this.y = y;
            this.type = type;
            this.g = 9999999;
            this.f = 0;
            this.visited = 0;
            this.parent=new Block();
        }

        Block(){
            this.type = '?';
        }

        Block(Block b){
            this.x = b.x;
            this.y = b.y;
            this.type = b.type;
            this.h = b.h;
            this.g = b.g;
            this.f = b.f;
        }
        @Override
        public int compareTo( final Block o) {
            if((this.x==o.x)&&(this.y==o.y)){
                //if(this.f<o.f)return -1;
                //if(this.f>o.f)return 1;
                return 0;
            }
            if(this.f<o.f)return -1;
            if(this.f>o.f)return 1;
            return 0;

        }
    }


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt(); // number of rows.
        int C = in.nextInt(); // number of columns.
        int A = in.nextInt(); // number of rounds between the time the alarm countdown is activated and the time the alarm goes off.
        int goalx ;
        int goaly ;
        int initx ;
        int inity ;
        Block[][] map = new Block[R][C];
        for(int r = 0 ; r < R ; r++){
            for(int c = 0 ; c < C; c++){
                map[r][c] = new Block();
            }
        }

        PriorityQueue<String> pathsC = new PriorityQueue<>();
        ArrayList<String> pathsBack = new ArrayList<>();
        // game loop
        int KR = in.nextInt(); // row where Kirk is located.
        int KC = in.nextInt(); // column where Kirk is located.
        goalx = -1;
        initx = KR;
        goaly = -1;
        inity = KC;
        int un = 0;
        int dis = 0;
//        map[KR][KC].visited=1;
        map[KR][KC].type='T';
        map[KR][KC].x=KR;
        map[KR][KC].y=KC;
        un = R*C;
        int Astar = 0;
        ArrayList<Block> queue = new ArrayList<>();
        ArrayList<String> back = new ArrayList<>();
        while (true) {
            if(Astar==0){
                map[KR][KC].visited+=1;
//                System.err.println(KR+" "+KC+" "+map[KR][KC].visited);
                for (int i = 0; i < R; i++) {
                    String ROW = in.next(); // C of the characters in '#.TC?' (i.e. one line of the ASCII maze).
                    for (int j = 0;j<C;j++){
                        if((map[i][j].type=='?')) {
                            map[i][j] = new Block(i, j, ROW.charAt(j));
                            dis += 1;
                            un -= 1;
                        }
                    }
                }
                for (int i = 0; i < R; i++) {
                    for (int j = 0;j<C;j++){
                        if((i==KR)&&(j==KC)){
                            System.err.printf("%3c",'@');
                        }else{
//                            System.err.printf("%3d",map[i][j].visited);
                            System.err.printf("%3c",map[i][j].type);
                        }
                    }
                    System.err.println("");
                }
                    ArrayList<Block> neighsdfs = new ArrayList<>();
                    neighsdfs.add(map[KR + 1][KC]);
                    neighsdfs.add(map[KR][KC + 1]);
                    neighsdfs.add(map[KR - 1][KC]);
                    neighsdfs.add(map[KR][KC - 1]);
                    String direction;
                    int c = 0;
                    int mv = 1600000;
                    for (Block n : neighsdfs) {
                        System.err.println(n.x+" "+n.y+" "+n.visited+" "+n.type+" | "+map[KR][KC].visited+" "+map[KR][KC].x+" "+map[KR][KC].y);
                        if ((n.type != '#') && (n.visited ==0 ) && (n.type != 'C')) {
                            direction = getDir(map[KR][KC], n);
                            System.out.println(direction);
//                            System.err.println("$"+direction);
                            back.add(inverse(direction));
                            c += 1;
                            break;
                        }
                    }
                    if (c == 0) {
                        back.remove(back.get(back.size()-1));
                        System.out.println(back.get(back.size()-1));
//                        back.remove(0);
//                        System.out.println(back.get(0));
                    }
                KR = in.nextInt(); // row where Kirk is located.
                KC = in.nextInt(); // column where Kirk is located.
                /*
                if((goalx!=initx)&&(goaly!=inity)) {
                    if ((map[KR + 1][KC].type != '#') && (map[KR + 1][KC].type != '_') && (map[KR + 1][KC].visited == 0)  && (map[KR + 1][KC].type != 'C')) {
                        pathsC.add("DOWN");
                        pathsBack.add("UP");
                        String s = pathsC.poll();
                        System.out.println(s);
//                        pathsBack.add(inverse(s));
                    } else if ((map[KR][KC + 1].type != '#') && (map[KR][KC + 1].type != '_') && (map[KR][KC + 1].visited == 0)&& (map[KR][KC + 1].type != 'C')) {
                        pathsC.add("RIGHT");
                        pathsBack.add("LEFT");
                        String s = pathsC.poll();
                        System.out.println(s);
//                        pathsBack.add(inverse(s));
                    } else if ((map[KR - 1][KC].type != '#') && (map[KR - 1][KC].type != '_') && (map[KR - 1][KC].visited == 0)&& (map[KR-1][KC].type != '_')) {
                        pathsC.add("UP");
                        pathsBack.add("DOWN");
                        String s = pathsC.poll();
                        System.out.println(s);
//                        pathsBack.add(inverse(s));
                    } else if ((map[KR][KC - 1].type != '#') && (map[KR][KC - 1].type != '_') && (map[KR][KC - 1].visited == 0)) {
                        pathsC.add("LEFT");
                        pathsBack.add("RIGHT");
                        String s = pathsC.poll();
                        System.out.println(s);
//                        pathsBack.add(inverse(s));
                    } else {
                        String s = pathsBack.get(pathsBack.size() - 1);
                        pathsBack.remove(pathsBack.size() - 1);
                        System.out.println(s);
                        KR = in.nextInt(); // row where Kirk is located.
                        KC = in.nextInt();
                        continue;
                    }
                }
                */
            }else{
                for (int w = pathsBack.size() - 1; w >= 0; w--) {
                    //for (int w = 0; w<pathsBack.size(); w++){
                    System.out.println(pathsBack.get(w));
                    KR = in.nextInt(); // row where Kirk is located.
                    KC = in.nextInt(); // column where Kirk is located.
                    for (int i = 0; i < R; i++) {
                        String ROW = in.next(); // C of the characters in '#.TC?' (i.e. one line of the ASCII maze).
                        for (int j = 0;j<C;j++){
                            if((i==KR)&&(j==KC)){
                                System.err.printf("%3c",'@');
                            }else{
                                System.err.printf("%3c",map[i][j].type);
                            }
                        }
                        System.err.println("");
                    }
                }
                System.err.println("Start Astar");
                HashSet<Block> closedSet = new HashSet<>();
                PriorityQueue<Block> openSet = new PriorityQueue<>();
                map[KR][KC].f = Math.abs(KR-goalx) + Math.abs(goaly-KC);
                map[KR][KC].g = 0;
                map[initx][inity].g = 9999;
                int cx = initx;
                int cy = inity;
                openSet.add(map[KR][KC]);
                Block current;
//                goalx = initx;
//                goaly = inity;
                while (!openSet.isEmpty()) {
                    current = openSet.poll();
                    //System.err.println("Current : "+current.x+" "+current.y+" "+goalx+" "+goaly+" "+openSet.size());
                    if ((current.x == goalx)&&(current.y == goaly)) {
                        System.err.println("Found Goal");
                        ArrayList<String>PathBack =new ArrayList<>();
                        while(current.x!=cx||current.y!=cy){
//                            System.err.println(current.x+" "+current.y+" "+current.parent.x+" "+current.parent.y+" "+cx+" "+cy);
                            if(current.x==current.parent.x+1){
                                System.err.println("DOWN");
                                PathBack.add("DOWN");
                            }else if(current.x==current.parent.x-1){
                                System.err.println("UP");
                                PathBack.add("UP");
                            }else if(current.y==current.parent.y+1 ){
                                System.err.println("RIGHT");
                                PathBack.add("RIGHT");
                            }else if(current.y==current.parent.y-1 ){
                                System.err.println("LEFT");
                                PathBack.add("LEFT");
                            }
                            current=current.parent;
                        }
                        for (int w = PathBack.size()-1; w >=0; w--) {
                            System.err.println(PathBack.get(w));
                            System.out.println(PathBack.get(w));
                            KR = in.nextInt(); // row where Kirk is located.
                            KC = in.nextInt(); // column where Kirk is located.
                            for (int i = 0; i < R; i++) {
                                String ROW = in.next(); // C of the characters in '#.TC?' (i.e. one line of the ASCII maze).
                                for (int j = 0;j<C;j++){
                                    if((i==KR)&&(j==KC)){
                                        System.err.printf("%3c",'@');
                                    }else{
                                        System.err.printf("%3c",map[i][j].type);
                                    }
                                }
                                System.err.println("");
                            }
                        }
                        for (int w = 0; w <PathBack.size(); w++) {
                            System.err.println(inverse(PathBack.get(w)));
                            System.out.println(inverse(PathBack.get(w)));
                            KR = in.nextInt(); // row where Kirk is located.
                            KC = in.nextInt(); // column where Kirk is located.
                            for (int i = 0; i < R; i++) {
                                String ROW = in.next(); // C of the characters in '#.TC?' (i.e. one line of the ASCII maze).
                                for (int j = 0;j<C;j++){
                                    if((i==KR)&&(j==KC)){
                                        System.err.printf("%3c",'@');
                                    }else{
                                        System.err.printf("%3c",map[i][j].type);
                                    }
                                }
                                System.err.println("");
                            }
                        }
                        System.exit(0);
                    }
                    ArrayList<Block> neighs =new ArrayList<>();
                    neighs.add(map[current.x+1][current.y]);
                    neighs.add(map[current.x][current.y+1]);
                    neighs.add(map[current.x-1][current.y]);
                    neighs.add(map[current.x][current.y-1]);
                    closedSet.add(current);
//                    System.err.println(current.x+" "+current.y);
                    for (Block nn : neighs) {
                        int g = current.g + 1;
                        int h = Math.abs(nn.x-goalx)+Math.abs(nn.y-goaly);
                        //map[n.x][n.y].parent=map[current.x][current.y];
                        if ((nn.type != '#') && (nn.type != '?')) {
//                            System.err.println(n.x+" "+n.y);
                            if(closedSet.contains(nn)){
                                continue;
                            }
                            for(Block o : openSet){
                                if((o.x==nn.x)&&(o.y==nn.y)){
                                    if(o.g>g){
                                        openSet.remove(o);
                                        nn.g = g;
                                        nn.f = g+h;
//                                        System.err.println("replacing "+n.x+" "+n.y+" "+n.g+" "+n.f);
                                        openSet.add(nn);
                                        nn.parent = current;
                                        break;
                                    }
                                }
                            }
                            nn.g = g;
                            nn.f = g+h;
//                            System.err.println("adding "+n.x+" "+n.y+" "+n.g+" "+n.f);
                            openSet.add(nn);
                            nn.parent = current;
                        }
                    }
                }
                System.exit(0);
            }
        }
    }
    public static String inverse(String w){
        if(w=="UP"){
            return "DOWN";
        }else if(w=="DOWN"){
            return "UP";
        }else if(w=="RIGHT"){
            return "LEFT";
        }else if(w=="LEFT"){
            return "RIGHT";
        }
        return"";
    }
    public static int calcF(int kx, int ky, int x, int y){
        return Math.abs(kx-x) + Math.abs(ky-y);
    }

    public static String getDir(Block s, Block d){
        if(s.x + 1 == d.x){
            return("DOWN");
        }
        if(s.x-1 == d.x){
            return("UP");
        }
        if(s.y + 1== d.y){
            return("RIGHT");
        }
        if(s.y -1 == d.y){
            return("LEFT");
        }
        return "LEL";
    }
}