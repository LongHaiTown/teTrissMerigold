package Tetromino;
import main.KeyHandler;
import main.PlayManager;

import java.awt.*;
import java.security.Key;
import java.util.Random;

public class Mino {
    public Block b[] = new Block[4];
    public Block tempB[] = new Block[4];

    int autoDropCounter = 0;

    public char direction = 's'; // n s w e

    boolean leftCollision,rightCollision,bottomCollision;

    public boolean activate = true;

    public boolean deActivating;
    public int deActivatingCounter =0;
    public Color c;

    public void getRandomColor() {
        Random rand = new Random();
        int red = rand.nextInt(256); // Giá trị ngẫu nhiên từ 0 đến 255 cho màu đỏ
        int green = rand.nextInt(256); // Giá trị ngẫu nhiên từ 0 đến 255 cho màu xanh lá cây
        int blue = rand.nextInt(256); // Giá trị ngẫu nhiên từ 0 đến 255 cho màu xanh dương
        c = new Color(red, green, blue);
    }

    public void create(){
        getRandomColor();
        b[0] = new Block(c);
        b[1] = new Block(c);
        b[2] = new Block(c);
        b[3] = new Block(c);
        tempB[0] = new Block(c);
        tempB[1] = new Block(c);
        tempB[2] = new Block(c);
        tempB[3] = new Block(c);
    }
    public void setXY(int x, int y){}
    public void updateXY(char direction){
        checkRotationCollision();
        if(leftCollision == false && rightCollision == false && bottomCollision == false){
            this.direction = direction;
            b[0].x = tempB[0].x;
            b[0].y = tempB[0].y;
            b[1].x = tempB[1].x;
            b[1].y = tempB[1].y;
            b[2].x = tempB[2].x;
            b[2].y = tempB[2].y;
            b[3].x = tempB[3].x;
            b[3].y = tempB[3].y;
        }
    }
    public void getDirectionS(){}
    public void getDirectionN(){}
    public void getDirectionW(){}
    public void getDirectionE(){}

    public void checkMovementCollision(){
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;
        checkStaticCollision();
        for (int i = 0 ; i< b.length; i++){
            if(b[i].x == PlayManager.left_x){
                leftCollision = true;
            }
        }
        for (int i = 0 ; i< b.length; i++){
            if(b[i].x + Block.SIZE == PlayManager.right_x){
                rightCollision = true;
            }
        }
        for (int i = 0 ; i< b.length; i++){
            if(b[i].y + Block.SIZE == PlayManager.bottom_y){
                bottomCollision = true;
            }
        }
    }
    public void checkRotationCollision(){
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        checkStaticCollision();

        for (int i = 0 ; i< b.length; i++){
            if(tempB[i].x < PlayManager.left_x){
                leftCollision = true;
            }
        }
        for (int i = 0 ; i< b.length; i++){
            if(tempB[i].x + Block.SIZE > PlayManager.right_x){
                rightCollision = true;
            }
        }
        for (int i = 0 ; i< b.length; i++){
            if(tempB[i].y + Block.SIZE >= PlayManager.bottom_y){
                bottomCollision = true;
            }
        }
    }
    public void checkStaticCollision(){
        for( int i = 0; i< PlayManager.staticblock.size() ; i++){
            int targetX = PlayManager.staticblock.get(i).x;
            int targetY = PlayManager.staticblock.get(i).y;
            for (int j =0; j< b.length ; j++) {
                if (b[j].x  == targetX && b[j].y + Block.SIZE == targetY)
                    bottomCollision = true;
            }
            for (int j =0; j< b.length ; j++) {
                if (b[j].x - Block.SIZE == targetX && b[j].y == targetY)
                    leftCollision = true;
            }
            for (int j =0; j< b.length ; j++) {
                if (b[j].x + Block.SIZE == targetX && b[j].y == targetY)
                    rightCollision = true;
            }
        }
    }
    public void update(){
        if (deActivating) {
            deActivating();
        }
        if (KeyHandler.upPressed ){
            switch(direction){
                case 's':getDirectionW();break;
                case 'w':getDirectionN();break;
                case 'n':getDirectionE();break;
                case 'e':getDirectionS();break;
            }
            KeyHandler.upPressed = false;
        }
        checkMovementCollision();
        if (KeyHandler.downPressed && activate == true ) {
            if (bottomCollision == false){
                this.b[0].y += b[0].SIZE;
                this.b[1].y += b[1].SIZE;
                this.b[2].y += b[2].SIZE;
                this.b[3].y += b[3].SIZE;

                autoDropCounter = 0;
            }
            KeyHandler.downPressed = false;
        }
        if (KeyHandler.leftPressed ){
            if (leftCollision == false && activate == true) {
                this.b[0].x -= b[0].SIZE;
                this.b[1].x -= b[1].SIZE;
                this.b[2].x -= b[2].SIZE;
                this.b[3].x -= b[3].SIZE;
                KeyHandler.leftPressed = false;
            }
        }
        if (KeyHandler.rightPressed ){
            if (rightCollision == false && activate == true) {
                this.b[0].x += b[0].SIZE;
                this.b[1].x += b[1].SIZE;
                this.b[2].x += b[2].SIZE;
                this.b[3].x += b[3].SIZE;
                KeyHandler.rightPressed = false;
            }
        }
        if (bottomCollision){
            deActivating = true ;
        }else {
            autoDropCounter++;
            if (autoDropCounter == PlayManager.dropInterval) {
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;
                autoDropCounter = 0;
            }
        }
    }
    public void deActivating(){
        deActivatingCounter ++;
        if (deActivatingCounter == 30) {
            deActivatingCounter = 0;
            checkMovementCollision();
            if (bottomCollision)
                activate =false;
        }
    }
    public void draw(Graphics2D g2){
        int margin = 2;
        g2.setColor(b[0].c);
        g2.fillRect(b[0].x,b[0].y, Block.SIZE-(margin *2),Block.SIZE-(margin *2));
        g2.fillRect(b[1].x,b[1].y, Block.SIZE-(margin *2),Block.SIZE-(margin *2));
        g2.fillRect(b[2].x,b[2].y, Block.SIZE-(margin *2),Block.SIZE-(margin *2));
        g2.fillRect(b[3].x,b[3].y, Block.SIZE-(margin *2),Block.SIZE-(margin *2));
    }
}
