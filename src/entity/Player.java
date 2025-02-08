package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp ;
    KeyHandler keyH;

    public Player(GamePanel gp ,KeyHandler keyH){
        this.gp=gp;
        this.keyH=keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x=100;
        y=100;
        speed=4;
        direction="down";
    }

    public void getPlayerImage(){
        try {
            up1= ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2=ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1=ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if (keyH.upPressed ==true){
            y-= speed;
            direction="up";
        } else if (keyH.downPressed==true) {
            y+=speed;
            direction="down";
        } else if (keyH.leftPressed==true) {
            x -= speed;
            direction="left";
        } else if (keyH.rightPressed==true) {
            x+=speed;
            direction="right";
        }
        spriteCounter++;

        if(spriteCounter>20){
            if(spriteNum==1){
                spriteNum=2;
            } else if (spriteNum==2) {
                spriteNum=1;
            }
            spriteCounter=0;
        }
    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x ,y,gp.tileSize,gp.tileSize);

        BufferedImage image = null;

        switch (direction){
            case "up" :
                if (spriteNum==1){
                    image=up1;
                } else if (spriteNum==2) {
                    image=up2;
                };break;
            case "down" :
                if (spriteNum==1){
                    image=down1;
                } else if (spriteNum==2) {
                    image=down2;
                };break;
            case "left" :
                if (spriteNum==1){
                    image=left1;
                } else if (spriteNum==2) {
                    image=left2;
                };break;
            case "right" :
                if (spriteNum==1){
                    image=right1;
                } else if (spriteNum==2) {
                    image=right2;
                };break;
            default: System.out.println("error : direction unfound !!");
        }
        g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
    }
}
