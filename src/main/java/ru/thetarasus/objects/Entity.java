package ru.thetarasus.objects;

import ru.thetarasus.GamePanel;

import java.awt.*;
import java.util.Random;

public class Entity {
    public int maxHp = 6, hp = maxHp, movementSpeed = 1, armor = 0, piercingBonus = 0,
    maxPossibleAttack = 6, x, y, tilePos;
    public boolean tankDestroyer = false, dead = false;
    public Image texture;

    public Entity(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void dealDamage(Entity entity, int damage){
        Random random = new Random();
        entity.applyDamage(this, random.nextInt(maxPossibleAttack));
    }
    public void applyDamage(Entity entity, int damage){
        boolean shouldApplyIfTank = this.armor > 0;
        if(shouldApplyIfTank) {applyTankDamage(entity, damage); return;}
        this.hp -= damage;
    }

    private void applyTankDamage(Entity entity, int damage) {
        this.hp -= entity.piercingBonus;
        this.hp -= damage / armor;
        dead = checkHp();
    }

    private boolean checkHp() {
         return hp <= 0;
    }

    public void render(Graphics g, int playerX, int playerY, int tileSize, GamePanel gamePanel){
        int locX = 0, locY = 0;

        switch (tilePos){
            case 1:
                locX += tileSize>>1;
                break;
            case 2:
                locY += tileSize>>1;
                break;
            case 3:
                locX += tileSize>>1; locY += tileSize>>1;
                break;
            case 0:
            default:
                break;
        }

        g.drawImage(texture, (playerX-(y*tileSize))+locX, (playerY-(x*tileSize))-locY, tileSize>>2, tileSize>>2, gamePanel);
    }

    public void tilePosAdd(){
        this.tilePos += tilePos < 3 ? 1 : 0;
    }


}
