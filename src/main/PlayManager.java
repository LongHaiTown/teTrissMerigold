package main;

import Tetromino.Block;
import Tetromino.Mino;
import Tetromino.Mino_T;

import java.awt.*;

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

    public static int dropInterval = 60;
    public PlayManager(){
        left_x=  (GamePanel.WIDTH/2) - (WIDTH/2);
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y= top_y+ HEIGHT;

        MINO_START_X = left_x+ WIDTH/2 - Block.SIZE;
        MINO_START_Y = top_y + Block.SIZE;

        currentMino = new Mino_T();
        currentMino.setXY(MINO_START_X,MINO_START_Y);
    }
    public void update(){
        currentMino.update();
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

        if (currentMino != null){
            currentMino.draw(g2);
        }
    }
}
