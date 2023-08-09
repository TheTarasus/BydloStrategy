package ru.thetarasus;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputHandler implements KeyListener {

    public boolean upPressed = false, downPressed = false, leftPressed = false, rightPressed = false;


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        upPressed =     code == KeyEvent.VK_W;
        downPressed =   code == KeyEvent.VK_S;
        leftPressed =   code == KeyEvent.VK_A;
        rightPressed =  code == KeyEvent.VK_D;

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        upPressed =      !upPressed     && code == KeyEvent.VK_W;
        downPressed =    !downPressed   && code == KeyEvent.VK_S;
        leftPressed =    !leftPressed   && code == KeyEvent.VK_A;
        rightPressed =   !rightPressed  && code == KeyEvent.VK_D;
    }
}
