package com.fourforfour.eldanialight.characters;

public class Boss extends Character implements BattleActions {
    // this class uses the character fields
    public Boss(String name, int health, int defense, int strength, int speed, int intel, int bezos, int xp) {
        this.setName(name);
        this.setHealth(health);
        this.setDefense(defense);
        this.setStrength(strength);
        this.setSpeed(speed);
        this.setIntel(intel);
        this.setBezos(bezos);
        this.setXp(xp);
    }

    // assuming they tried to implement from BattleActions
    @Override
    public void attack(Character character) {
    }

    @Override
    public boolean run(Character character) {
        return false;
    }

    @Override
    public int defend() {
        return 0;
    }

    @Override
    public void use() {
    }
}//EOC