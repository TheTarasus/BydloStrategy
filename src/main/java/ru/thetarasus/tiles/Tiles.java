package ru.thetarasus.tiles;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public enum Tiles {

    PLAINS(1f, 1f, 1f, false, new String[]{"textures/tiles/plains"}),
    HILLS(0.75f, 0.75f, 1.25f, false, new String[]{"textures/tiles/hills"}),
    MOUNTAINS(0.5f, 0.5f, 1.5f, false, new String[]{"textures/tiles/mountains"}),
    JUNGLE(0.65f, 0.65f, 1.35f, false, new String[]{"textures/tiles/jungle"}),
    SWAMP(0.85f, 0.85f, 1.15f, false, new String[]{"textures/tiles/swamp"});

    public AbstractTile tile;
    Tiles(float speedModifier, float attackModifier, float defenceModifier, boolean connectable, String[] pathes) {
        int length = pathes.length;
        Image[] textures = new Image[]{};
        for(int i = 0; i < pathes.length;){
            try {
                textures[i] = ImageIO.read(getClass().getClassLoader().getResource(pathes[i]));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        this.tile = new AbstractTile(speedModifier, attackModifier, defenceModifier, connectable, textures);
    }

}
