package com.fourforfour.eldanialight.characters;

import com.fourforfour.eldanialight.battle.Utility;

public class Enemy extends Character implements BattleActions{
    //This is inheriting from the Character class and using the Interface methods for it's actions
    public String rewardItem;
    private String dialog;

    //Constructor for enemy
    public Enemy() {
    }

    public Enemy(String name, int health, int defense, int strength, int speed, int intel, int bezos, int xp, String rewardItem){
        super(name, health, defense, strength, bezos, speed, intel);
        this.setXp(xp);
        this.rewardItem = rewardItem;
    }

    //used for the player to attack
    @Override
    public void attack(Character character) {
        Player player = (Player)character;
        double attackingPower = (this.getStrength() + this.getSpeed())* Utility.randomNumber();
        double defendingPower = player.defend() * Utility.randomNumber();

        if (attackingPower>defendingPower)
            player.setHealth((player.getHealth() - (attackingPower - defendingPower)));
    }

    //used to see if you can run away
    public boolean run(Character character) {
    return true;
    }

    //used for defending
    @Override
    public int defend() {
        return getDefense();
    }

    //used to use items
    @Override
    public void use() {
    }
}//EOC
