package ru.thetarasus;

import ru.thetarasus.objects.Entity;
import ru.thetarasus.objects.TankDestroyer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable, ImageObserver {

    public static int originalTileSize = 16, scale = 3, tileSize = originalTileSize * scale,
            maxColumns = 16, maxRows = 12,
            screenWidth = tileSize*maxColumns, screenHeight = tileSize*maxRows,
            fps = 60,
    mapTilesHeight = 55, mapTilesWidth = 34,
    mapScreenWidth = tileSize*mapTilesWidth, mapScreenHeight = tileSize*mapTilesHeight, minTileScale = 2, maxTileScale = 10;

    KeyInputHandler keyHandler;
    MouseMovementHandler mouseHandler;
    MouseScrollListener mouseScrollHandler;

    Thread gameThread;
    List<Entity> entities = new ArrayList<Entity>();
    int playerX = screenWidth>>1, playerY = screenHeight>>1, speed = 8;
    JLabel debugPanel;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        keyHandler = new KeyInputHandler();
        mouseHandler = new MouseMovementHandler();
        mouseScrollHandler = new MouseScrollListener();
        this.addMouseMotionListener(mouseHandler);
        this.addMouseWheelListener(mouseScrollHandler);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        for(int i = 0; i < 16; i++){
            Random r = new Random();
            int rx = r.nextInt(15), ry = r.nextInt(15);
            boolean isCollapsed = entities.stream().anyMatch(entity -> entity.x == rx && entity.y == ry);
            TankDestroyer destroyer = new TankDestroyer(rx, ry);
            if(isCollapsed) destroyer.tilePosAdd();
            entities.add(destroyer);
        }
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000/fps,
                nextDrawTime = System.nanoTime()+drawInterval;
        while(gameThread != null){
            update();
            repaint();

            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                remainingTime = remainingTime<0 ? 0 : remainingTime;

                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void update() {

        int oldScale = scale;

            scale += mouseScrollHandler.zoomIn && scale <= maxTileScale ? 1 : 0;
            scale -= mouseScrollHandler.zoomOut && scale >= minTileScale ? 1 : 0;

            tileSize = originalTileSize * scale;
            mapScreenWidth = tileSize * mapTilesWidth;
            mapScreenHeight = tileSize * mapTilesHeight;
            mouseScrollHandler.zoomOut = false;
            mouseScrollHandler.zoomIn = false;

        playerY -= mouseHandler.upMotion && playerY > 0 ? speed : 0;
        playerY += mouseHandler.downMotion && playerY <= mapScreenHeight ? speed : 0;
        playerX += mouseHandler.rightMotion && playerX <= mapScreenWidth ? speed : 0;
        playerX -= mouseHandler.leftMotion && playerX > 0 ? speed : 0;

        playerX = playerX > mapScreenWidth ? mapScreenWidth : playerX;
        playerY = playerY > mapScreenHeight ? mapScreenHeight : playerY;
        playerX = playerX < screenWidth ? screenWidth : playerX;
        playerY = playerY < screenHeight ? screenHeight : playerY;

        Main.debugPanel.setText("x: " + playerX + ", y: " + playerY +";");
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.WHITE);
        for(int x = 0; x < mapTilesHeight; x++){
            g.drawLine(playerX, playerY-(x*tileSize), playerX-(mapTilesWidth*tileSize), playerY-(x*tileSize));
        }

        for(int y = 0; y < mapTilesWidth; y++){
            g.drawLine(playerX-(y*tileSize), playerY, playerX-(y*tileSize), playerY-(mapTilesHeight*tileSize));
        }



        List<Entity> entitiesToRender = entities;
        entitiesToRender.removeIf(entity -> {
            return entity.x < playerX && entity.x > playerX + mapTilesWidth * tileSize && entity.y < playerY && entity.y > playerY + (mapTilesHeight * tileSize);
        });

        entitiesToRender.forEach(entity -> entity.render(g, playerX, playerY, tileSize, this));

        g2D.dispose();
    }


}
