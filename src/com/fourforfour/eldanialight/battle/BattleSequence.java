package com.fourforfour.eldanialight.battle;

import com.fourforfour.eldanialight.Game;
import com.fourforfour.eldanialight.areas.EnemyGenerator;
import com.fourforfour.eldanialight.characters.Enemy;


public class BattleSequence implements java.io.Serializable{
    // FIELDS
    Enemy enemy;
    String battleAreaType;
    private boolean stillFighting = true;
    // use Game.currentPlayer to access the player in the game
    // make attack method and check to see if the character that got attacked is still alive

    // CONSTRUCTOR
    // Called in class BattleArea
    public BattleSequence(String battleAreaType) {
        this.battleAreaType = battleAreaType;
        this.enemy = EnemyGenerator.generate(battleAreaType);
    }



    // set the character health to max and perform action according to the player's input: attack, use item, run(), fight()
    // player wins a battle if enemy health < 1 and loses when player's health is < 1
    public String attack() {
        String result;
        Game.player.attack(this.enemy);
        if (this.enemy.getHealth() < 1) {
            return win();
        }
        this.enemy.attack(Game.player);
        System.out.println(Game.player.getHealth());
        if(Game.player.getHealth() < 1) {
            return lose();
        }
        return printCurrentStats();
    }

    // check if the the player was able escape from the enemy. If not, continue fight().
    public String run() {
        if (Game.player.run(this.enemy)) {
            return "you have successfully ran away from " + this.enemy.getName();
        } else {
            this.enemy.attack(Game.player);
            return "You were not fast enough to run away. you must stay and fight, good luck" +
                    "\nWhile attempting to run away, you were hit by the enemy" +
                    "\nYour current health is " + Game.player.getHealth();
        }
    }

    // print stats
    public String printCurrentStats() {
        return " You are currently fighting " + this.enemy.getName() +
                "\nYour Current total health is :" + Game.player.getHealth() +
                "\n" + "Your current total attacking power is :" + (Game.player.getStrength() + enemy.getSpeed()) +
                "\n" + this.enemy.getName() + "'s current total health is " + this.enemy.getHealth() +
                "\n" + this.enemy.getName() + "'s current total attacking power is :" + (this.enemy.getStrength() + this.enemy.getSpeed()) +
                "\n" + "What action do  you want to take: Attack Run?" +
                "\n" + "If you attack the enemy will attack back.";
    }

    //When the player kills the enemy this method adds to the players stats, and takes them back to the previous location
    public String win() {
        if(this.enemy.getName().equals("gandolf the black")){
            Game.gameOver = true;
        }else{
            Game.player.setXp((Game.player.getXp()) + this.enemy.getXp());
            Game.player.setBezos((Game.player.getBezos()) + this.enemy.getBezos());
            Game.changeCity(Game.previousCity);
            stillFighting = false;
            return "You have defeated " + this.enemy.getName() +
                    "\nYou have earned " + Game.player.getXp() + "XP and " + Game.player.getBezos() + " Bezos" +
                    "\n" + Game.player.checkLevelUp();
        }
        return null;
    }

    // This is called when the player dies and sets the currentArea to Lucino Town
    public String lose() {
        Game.player.setHealth(Game.player.getMaxHealth());
        Game.changeCity("lucino town");
        stillFighting = false;
        return "You have been killed by " + this.enemy.getName() +
        "\n You have respawned at lucino town";
    }
}