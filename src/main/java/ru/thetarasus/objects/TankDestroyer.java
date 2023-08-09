package ru.thetarasus.objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TankDestroyer extends Entity{

    public TankDestroyer(int x, int y) {
        super(x, y);
        this.piercingBonus = 1;
        this.maxPossibleAttack = 12;
        this.tankDestroyer = true;
        BufferedImage image;
        try {
            image = ImageIO.read(getClass().getClassLoader().getResource("textures/entities/at/AT.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.texture = image;
    }
}
