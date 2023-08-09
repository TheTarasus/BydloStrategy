package ru.thetarasus;

import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseScrollListener implements MouseWheelListener {

    public boolean zoomIn, zoomOut;


    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        zoomIn = e.getWheelRotation() > 0;
        zoomOut = e.getWheelRotation() < 0;
    }
}
