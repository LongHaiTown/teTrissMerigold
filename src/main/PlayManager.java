package main;

import Tetromino.*;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.math.*;
import java.util.Random;

public class PlayManager {
    final int WIDTH = 360;
    final int HEIGHT= 600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    Mino currentMino ;
    final int MINO_START_X;
    final int MINO_START_Y;

    Mino nextMino;
    final int NEXTMINO_START_X;
    final int NEXTMINO_START_Y;
    public static ArrayList<Block> staticblock = new ArrayList<>();
    public static int dropInterval = 60;
    public PlayManager(){
        left_x=  (GamePanel.WIDTH/2) - (WIDTH/2);
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y= top_y+ HEIGHT;

        MINO_START_X = left_x+ WIDTH/2 - Block.SIZE;
        MINO_START_Y = top_y + Block.SIZE;

        NEXTMINO_START_X = right_x+185;
        NEXTMINO_START_Y = top_y+90;

        currentMino = pickMino();
        currentMino.setXY(MINO_START_X,MINO_START_Y);

        nextMino = pickMino();
        nextMino.setXY(NEXTMINO_START_X,NEXTMINO_START_Y);

    }
    private Mino pickMino(){
        Mino mino = null;
        Random random = new Random();
        int num = random.nextInt(7);
        switch (num){
            case 1:
                mino = new Mino_T();
                break;
            case 2:
                mino = new Mino_L2();
                break;
            case 3:
                mino = new Mino_Bar();
                break;
            case 4:
                mino = new Mino_Z2();
                break;
            case 5:
                mino = new Mino_Z1();
                break;
            case 6:
                mino = new Mino_L1();
                break;
            case 0:
                mino = new Mino_Square();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + num);
        }
        return mino;
    }
    public void update(){
        if(!currentMino.activate){
            staticblock.add(currentMino.b[0]);
            staticblock.add(currentMino.b[1]);
            staticblock.add(currentMino.b[2]);
            staticblock.add(currentMino.b[3]);

            currentMino = nextMino;
            currentMino.setXY(MINO_START_X,MINO_START_Y);
            nextMino = pickMino();
            nextMino.setXY(NEXTMINO_START_X,NEXTMINO_START_Y);

        }else currentMino.update();
//        currentMino.update();
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left_x-4,top_y-4,WIDTH+4,HEIGHT+4);

        int x = right_x +100;
        int y = top_y+4;
        g2.drawRect(x,y,200,200);

        g2.setFont(new Font("Arial",Font.ITALIC,30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT",x+60,y+30);
        g2.drawLine(x,y+40,x+200,y+40);

        if (currentMino != null)
            currentMino.draw(g2);

        for (int i = 0; i< staticblock.size(); i++){
            staticblock.get(i).draw(g2);
        }
        nextMino.draw(g2);
        if (currentMino != null){
            currentMino.draw(g2);
        }
    }
}
