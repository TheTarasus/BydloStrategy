package ru.thetarasus;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMovementHandler implements MouseMotionListener {

    public boolean upMotion, downMotion, leftMotion, rightMotion;
    public int x, y, sX, sY;

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point point = e.getPoint();
        x = point.x; y = point.y;
        sX = e.getXOnScreen();
        sY = e.getYOnScreen();
        int x1 = GamePanel.screenWidth, y1 = GamePanel.screenHeight;
        downMotion = y<(y1>>3);
        upMotion = y>(y1-(y1>>3));
        leftMotion = x>(x1-(x1>>3));
        rightMotion = x<(x1>>3);
    }
}
