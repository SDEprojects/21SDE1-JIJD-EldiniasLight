package com.fourforfour.eldanialight.battle;

import com.fourforfour.eldanialight.Game;
import com.fourforfour.eldanialight.areas.EnemyGenerator;
import com.fourforfour.eldanialight.characters.Enemy;

import java.util.Locale;
import java.util.Scanner;

public class BattleSequence {
    // FIELDS
    Scanner myScanner = new Scanner(System.in);
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

    // METHODS
    public void battle() {
        if (enemy.getHealth() < 1) enemy = EnemyGenerator.generate(battleAreaType);
        battleChoice();
        System.out.println();
    }

    // options to battle: run() and fight(). Also used a helper functions in above functions
    public void battleChoice() {
        System.out.println(" You have entered a battle with " + this.enemy.getName());  //+ current enemy whenever we assign that value
        System.out.println("Do you want to fight or run?");
        String battleChoice = myScanner.nextLine().toLowerCase(Locale.ROOT);

        if (battleChoice.equals("run"))
            run();
        else if (battleChoice.equals("fight"))
            fight();
        else {
            System.out.println("you have entered an invalid action");
            battleChoice();
        }
    }

    // set the character health to max and perform action according to the player's input: attack, use item, run(), fight()
    public void fight() {
        String fightChoice;
        Game.player.setMaxHealth(Game.player.getHealth());
        printCurrentStats();
        fightChoice = myScanner.nextLine().toLowerCase(Locale.ROOT);

        switch (fightChoice) {
            case "attack":
                attack();
                break;
//            case "use item":
//                Game.player.selectItem();
//                break;
            case "run":
                run();
                break;
            default:
                System.out.println("Error: Expected attack, use item, or run");
                fight();
        }
        if (stillFighting) {
            fight();
        }
    }

    // player wins a battle if enemy health < 1 and loses when player's health is < 1
    public void attack() {
        Game.player.attack(this.enemy);
        if (this.enemy.getHealth() < 1) {
            win();
        }
        this.enemy.attack(Game.player);
        if (Game.player.getHealth() < 1) {
            lose();
        }
    }

    // check if the the player was able escape from the enemy. If not, continue fight().
    public void run() {
        if (Game.player.run(this.enemy)) {
            System.out.println("you have successfully ran away from " + this.enemy.getName());
//            BattleArea area = (BattleArea) Game.currentArea;
//            System.out.println("You will be returning to " + area.getPreviousArea());
//            Game.currentArea = Game.world.get(area.getPreviousArea());
            stillFighting = false;

        } else {
            System.out.println("You were not fast enough to run away. you must stay and fight, good luck ");
            fight();
        }
    }

    // print stats
    public void printCurrentStats() {
        System.out.println("Your Current total health is :" + Game.player.getHealth());
        System.out.println("Your current total attacking power is :" + (Game.player.getStrength() + enemy.getSpeed()));
        System.out.println(this.enemy.getName() + "'s current total health is " + this.enemy.getHealth());
        System.out.println(this.enemy.getName() + "'s current total attacking power is :" + (this.enemy.getStrength() + this.enemy.getSpeed()));
        System.out.println("What action do  you want to take: Attack, Use Item, or Run?");
        System.out.println("If you attack the enemy will attack back.");
    }

    // Battle win situation and add awardItem
    public void win() {
        Game.player.setXp((Game.player.getXp()) + this.enemy.getXp());
        Game.player.setBezos((Game.player.getBezos()) + this.enemy.getBezos());
        System.out.println("You have defeated " + this.enemy.getName());
        System.out.println("You have earned " + Game.player.getXp() + "XP and " + Game.player.getBezos() + " Bezos");
        System.out.println("You received a " + this.enemy.rewardItem);
        Game.player.questItems.add(this.enemy.rewardItem);

//        BattleArea area = (BattleArea) Game.currentArea;
//        System.out.println("You will be returning to " + area.getPreviousArea());
//        Game.currentArea = Game.world.get(area.getPreviousArea());
        Game.player.addXp();
        stillFighting = false;
    }

    // battle lose situation and set currentArea to Lucino Town
    public void lose() {
        Game.player.setHealth(Game.player.getMaxHealth());
        System.out.println("You have been killed by " + this.enemy.getName());
        System.out.println("You will respawn at lucino town");
//        Game.currentArea = Game.world.get("lucino town");
        stillFighting = false;
    }
}//EOC