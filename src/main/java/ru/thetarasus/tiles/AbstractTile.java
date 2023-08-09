package ru.thetarasus.tiles;

import java.awt.*;

public class AbstractTile {
    public float speedModifier = 1f, attackModifier = 1f, defenceModifier = 1f;

    public boolean connectable = false, landPassable = true, seaPassable = false;
    public Image[] textures;

    public AbstractTile(float speedModifier, float attackModifier, float defenceModifier, boolean connectable, Image[] textures){
        this.speedModifier = speedModifier;
        this.attackModifier = attackModifier;
        this.defenceModifier = defenceModifier;
        this.connectable = connectable;
        this.textures = textures;
    }


}
