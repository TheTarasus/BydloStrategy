package ru.thetarasus.objects;

public class Tank extends Entity {
    public Tank(int x, int y){
        super(x, y);
        this.hp = 6;
        this.armor = 3;
        this.maxHp = hp;
        this.movementSpeed = 2;
    }

}
