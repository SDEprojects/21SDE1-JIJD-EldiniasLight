package com.fourforfour.eldanialight.characters;

public class Boss extends Character  {

    public Boss(String name, double health, int defense, int strength, int speed,int intel, int bezos ,int xp){
        this.setName(name);
        this.setHealth(health);
        this.setDefense(defense);
        this.setStrength(strength);
        this.setSpeed(speed);
        this.setIntel(intel);
        this.setBezos(bezos);
        this.setXp(xp);
    }

//    @Override
//    public int attack() {
//
//        return 0;
//    }
//
//    @Override
//    public void run() {
//
//    }
//
//    @Override
//    public void defend() {
//
//    }
//
//    @Override
//    public void use() {
//
//    }
}//EOC