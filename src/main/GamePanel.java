package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //screen settings
    final int originalTileSize=16; //16 * 16 tile
    final int scale = 3;
    final int tileSize = originalTileSize * scale ; //48 * 48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow= 12;
    final int screenWidth = tileSize * maxScreenCol;  // 768 px
    final int screenHeight = tileSize * maxScreenRow; //576 px


    // FPS frame per second
    int FPS = 60;



    KeyHandler keyH = new KeyHandler();
    Thread gameThread ;


    // set player's default position
    int playerX= 100;
    int playerY=100;
    int playerSpeed =4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        while (gameThread != null){
            //System.out.println("the game loop is running !");
            // 1:update information such as character positions

            double drawInterval = (double) 1000000000 /FPS;  // we draw the screen every 0.166666 second
            double nextDrawTime= System.nanoTime()+drawInterval;

            update();
            // 2:draw the screen with the updated information
            repaint();

            try {
                long remainingTime = (long) (nextDrawTime - System.nanoTime());
                remainingTime = remainingTime / 1000000;

                if( remainingTime < 0){
                    remainingTime =0;
                }
                Thread.sleep(remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void update(){

        if (keyH.upPressed ==true){
            playerY -= playerSpeed;
        } else if (keyH.downPressed==true) {
            playerY+=playerSpeed;
        } else if (keyH.leftPressed==true) {
            playerX -= playerSpeed;
        } else if (keyH.rightPressed==true) {
            playerX+=playerSpeed;
        }
    } 

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX ,playerY,tileSize,tileSize);
        g2.dispose();
    }
}
