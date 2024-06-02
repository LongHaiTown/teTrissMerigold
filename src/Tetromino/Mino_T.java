package Tetromino;

import java.awt.*;

public class Mino_T extends Mino{
    public Mino_T(){
        super.create();
    }
    public void setXY(int x, int y){
        //o o o
        //  o
        b[0].x = x;
        b[0].y = y;
        b[1].x =b[0].x- b[0].SIZE;
        b[1].y =b[0].y;
        b[2].x = b[0].x + b[0].SIZE ;
        b[2].y = b[0].y;
        b[3].x = b[0].x;
        b[3].y = b[0].y + b[0].SIZE;
    }

    @Override
    public void getDirectionS(){

        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[1].x - Block.SIZE;
        tempB[1].y = b[1].y - Block.SIZE;
        tempB[2].x = b[2].x + Block.SIZE;
        tempB[2].y = b[2].y + Block.SIZE;
        tempB[3].x = b[3].x - Block.SIZE;
        tempB[3].y = b[3].y + Block.SIZE;
        updateXY('s');
    }
    public void getDirectionN() {
        //  o
        //o o o
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[1].x;
        tempB[1].y = b[1].y;
        tempB[2].x = b[2].x;
        tempB[2].y = b[2].y;
        tempB[3].x = b[3].x + Block.SIZE;
        tempB[3].y = b[3].y - Block.SIZE;
        updateXY('n');
    }
    public void getDirectionW() {
        //  o
        //o o
        //  o
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[1].x;
        tempB[1].y = b[1].y;
        tempB[2].x = b[2].x - Block.SIZE;
        tempB[2].y = b[2].y - Block.SIZE;
        tempB[3].x = b[3].x;
        tempB[3].y = b[3].y;
        updateXY('w');
    }
    public void getDirectionE() {
        //  o
        //  o o
        //  o
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[1].x + Block.SIZE;
        tempB[1].y = b[1].y + Block.SIZE;
        tempB[2].x = b[2].x ;
        tempB[2].y = b[2].y;
        tempB[3].x = b[3].x;
        tempB[3].y = b[3].y ;
        updateXY('e');
    }
}
