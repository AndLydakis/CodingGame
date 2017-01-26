//package WinamaxSponsoredContest.Java;

import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by lydakis-local on 1/25/17.
 */
class Solution {

    enum SquareType{
        FREE, BALL, ARROW, HOLE, HOLE_FULL, HAZARD
    }

    enum Direction{
        UP, DOWN, LEFT, RIGHT
    }

    static class Ball{
        int id ;
        int x ;
        int y;
        int shotCount;
        boolean inHole;

        public Ball(int x, int y, int sc){
            this.x = x;
            this.y = y;
            this.shotCount = sc;
            inHole = false;
        }


        public int getX(){
            return this.x;
        }
        public void setX(int X){
            this.x = X;
        }

        public int getY(){
            return this.y;
        }
        public void setY(int Y){
            this.y = Y;
        }

        public int getSc(){
            return this.shotCount;
        }
        public void setSc(int sc){
            this.shotCount = sc;
        }
    }

    static class GridSquare{
        int x;
        int y;
        char symbol;
        Solution.SquareType type;
        ArrayList<String> moveList;

        public GridSquare(){

        }

        public GridSquare(int x, int y, Solution.SquareType type, int shotCount){
            this.x = x;
            this.y = y;
            this.type = type;
            switch(type){
                case HOLE:
                    this.symbol = 'H';
                    break;
                case FREE:
                    this.symbol = '.';
                    break;
                case HAZARD:
                    this.symbol = ' ';
                    break;
                case BALL:
                    this.symbol = Character.forDigit(shotCount, 10);
                    break;
            }
            this.moveList = new ArrayList<>();
        }

        public int getX(){
            return this.x;
        }
        public int getY(){
            return this.y;
        }
        public void setX(int X){
            this.x = X;
        }
        public void setY(int Y){
            this.y = Y;
        }
        public Solution.SquareType getType(){
            return this.type;
        }
        public void setY(Solution.SquareType t){
            this.type = t;
        }
    }

    static class Solver{
        int height;
        int width;
        GridSquare[][] map;
        ArrayList<Ball> balls;
        ArrayList<GridSquare> holes;

        public Solver(int h, int w){
            this.height = h;
            this.width = w;
            this.map = new GridSquare[h][w];
            this.balls = new ArrayList<>();
            this.holes = new ArrayList<>();
        }

        public boolean allInPlace(){
            for(Ball b:this.balls){
                if (!b.inHole){
                    return false;
                }
            }
            return true;
        }

        public boolean haveMovesLeft(){
            int count = 0;
            for(Ball b:this.balls){
                if (b.shotCount > 0){
                    count ++;
                }
            }
            if (count == 0){
                return false;
            }
            return true;
        }

        public void move(Direction d, Ball b){
            int new_x, new_y;
            switch (d){
                case UP:
                    if (b.y < this.height){
                        this.map[b.y][b.x].type = SquareType.ARROW;
                        b.shotCount -= 1;
                        b.x = b.x;
                        b.y += 1 ;
                    }
                    break;
                case DOWN:
                    if (b.y > 0){
                        this.map[b.y][b.x].type = SquareType.ARROW;
                        b.shotCount -= 1;
                        b.x = b.x;
                        b.y -= 1 ;
                    }
                    break;
                case LEFT:
                    if (b.x > 0){
                        this.map[b.y][b.x].type = SquareType.ARROW;
                        b.shotCount -= 1;
                        b.x -= 1 ;
                        b.y = b.y;
                    }
                    break;
                case RIGHT:
                    if (b.x < this.width){
                        this.map[b.y][b.x].type = SquareType.ARROW;
                        b.shotCount -= 1;
                        b.x += 1 ;
                        b.y = b.y;
                    }
                    break;
            }
            if(this.map[b.y][b.x].type==SquareType.HOLE){
                this.map[b.y][b.x].type = SquareType.HOLE_FULL;
                b.inHole = true;
            }
        }

        public void DFS(){
            while(!allInPlace()&&haveMovesLeft()){
                for(Ball b:this.balls){
                    if (!b.inHole && b.shotCount>0){
                        //move left
                        move(Direction.LEFT, b);
                        if(allInPlace()){

                        }
                        move(Direction.RIGHT, b);
                    }
                    if (!b.inHole && b.shotCount>0){
                        move(Direction.RIGHT, b);
                        if(allInPlace()){

                        }
                        move(Direction.LEFT, b);
                    }
                    if (!b.inHole && b.shotCount>0){
                        move(Direction.UP, b);
                        if(allInPlace()){

                        }
                        move(Direction.DOWN, b);
                    }
                    if (!b.inHole && b.shotCount>0){
                        move(Direction.DOWN, b);
                        if(allInPlace()){

                        }
                        move(Direction.UP, b);
                    }
                }
            }
        }

    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int mapWidth = Integer.parseInt(in.next());
        int mapHeight = Integer.parseInt(in.next());
        System.err.println(mapWidth);
        System.err.println(mapHeight);
        Solver solver = new Solver(mapHeight, mapWidth);
        System.err.println(solver.width);
        System.err.println(solver.height);
        for(int h = 0; h < solver.height; h++){
            String nl = in.next();
            System.err.print(nl);
            for(int w = 0; w < solver.width; w++){
                Character c = nl.charAt(w);
//                System.err.print(c);
                if(Character.isDigit(c)){
//                    System.err.println("Creating ball with " + Integer.parseInt(""+c) +  " shot(s)");
//                    int cc = Character.getNumericValue(c);
                    int sc = Integer.parseInt(""+c);
                    solver.map[h][w] = new GridSquare(h, w, SquareType.BALL, sc);
                    Ball ball = new Ball(w, h, sc);
                    solver.balls.add(ball);
                }else{
                    switch (c){
                        case '.':
                            solver.map[h][w] = new GridSquare(w, h, SquareType.FREE, -1);
                            break;
                        case ' ':
                            solver.map[h][w] = new GridSquare(w, h, SquareType.HAZARD,-1);
                            break;
                        case 'H':
                            solver.map[h][w] = new GridSquare(w, h, SquareType.HOLE,-1);
                            solver.holes.add(solver.map[h][w]);
                    }
                }
            }
            System.err.println();
            printMap(solver);
            solver.DFS();
        }
        in.close();
    }

    public static void printMap(Solver solver){
        for (int x = 0; x < solver.height; x++){
            for (int y = 0; x < solver.width; x++){
                System.err.print(solver.map[y][x].symbol);
            }
            System.err.println();
        }
    }
    //public WMS.SquareType getType(char c){
    //    switch (c):
    //
    //}
}
