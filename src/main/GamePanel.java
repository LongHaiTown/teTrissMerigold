package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    public static final int WIDTH =1280;
    public static final int HEIGHT =720;
    public static final int FPS = 60;
    Thread gameThread;
    PlayManager pm ;
    KeyHandler KeyHandler;

    public GamePanel(){
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        this.addKeyListener( new KeyHandler());
        this.setFocusable(true);
        pm = new PlayManager();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("run");
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread!= null) {
            currentTime = System.nanoTime();
            delta += (currentTime- lastTime)/ drawInterval;

            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }


        }
    }
    public void update(){
        if(main.KeyHandler.pausePressed == false) {
            pm.update();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        pm.draw(g2);
    }
}
