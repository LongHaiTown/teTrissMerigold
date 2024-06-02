package Tetromino;

import java.awt.*;

public class Mino_L1 extends Mino{
    public Mino_L1(){
        super.create();
    }
    public void setXY(int x, int y){
        //o o o
        //  o
        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x ;
        b[1].y = b[0].y - Block.SIZE;
        b[2].x = b[0].x ;
        b[2].y = b[0].y + Block.SIZE;
        b[3].x = b[0].x + Block.SIZE;
        b[3].y = b[0].y + Block.SIZE;
    }

    @Override
    public void getDirectionS(){

        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[1].x + Block.SIZE;
        tempB[1].y = b[1].y - Block.SIZE;
        tempB[2].x = b[2].x - Block.SIZE;
        tempB[2].y = b[2].y + Block.SIZE;
        tempB[3].x = b[3].x ;
        tempB[3].y = b[3].y + Block.SIZE*2;
        updateXY('s');
    }
    public void getDirectionN() {
        //  o
        //o o o
        tempB[0].x = b[0].x ;
        tempB[0].y = b[0].y ;
        tempB[1].x = b[1].x - Block.SIZE ;
        tempB[1].y = b[1].y + Block.SIZE ;
        tempB[2].x = b[2].x + Block.SIZE;
        tempB[2].y = b[2].y - Block.SIZE;
        tempB[3].x = b[3].x ;
        tempB[3].y = b[3].y - b[1].SIZE*2;
        updateXY('n');
    }
    public void getDirectionW() {
        //  o
        //o o
        //  o
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[1].x + Block.SIZE;
        tempB[1].y = b[1].y + Block.SIZE;
        tempB[2].x = b[2].x - Block.SIZE;
        tempB[2].y = b[2].y - Block.SIZE;
        tempB[3].x = b[3].x - Block.SIZE*2;
        tempB[3].y = b[3].y ;
        updateXY('w');
    }
    public void getDirectionE() {
        //  o
        //  o o
        //  o
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[1].x - Block.SIZE;
        tempB[1].y = b[1].y - Block.SIZE;
        tempB[2].x = b[2].x + Block.SIZE;
        tempB[2].y = b[2].y + Block.SIZE;
        tempB[3].x = b[3].x + Block.SIZE*2;
        tempB[3].y = b[3].y ;
        updateXY('e');
    }
}
