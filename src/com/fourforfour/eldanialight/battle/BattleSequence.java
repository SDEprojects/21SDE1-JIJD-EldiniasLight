package com.fourforfour.eldanialight.battle;

import com.fourforfour.eldanialight.Game;
import com.fourforfour.eldanialight.areas.BattleArea;
import com.fourforfour.eldanialight.areas.BattleAreaTypes;
import com.fourforfour.eldanialight.areas.EnemyGenerator;
import com.fourforfour.eldanialight.characters.*;
import com.fourforfour.eldanialight.Main;
import com.fourforfour.eldanialight.characters.Character;
import com.fourforfour.eldanialight.items.WearItem;

import java.util.*;

public class BattleSequence {


    Scanner myScanner = new Scanner(System.in);
    Enemy enemy;
    BattleAreaTypes battleAreaTypes;
    private boolean stillFighting = true;
//use Game.currentPlayer to access the player in the game
// make attack method and check to see if the character that got attacked is still alive
//
    public BattleSequence(BattleAreaTypes battleAreaTypes){
        this.battleAreaTypes = battleAreaTypes;
        this.enemy= EnemyGenerator.generate(battleAreaTypes);
    }

    public void battle(){
        if(enemy.getHealth() < 1) enemy = EnemyGenerator.generate(battleAreaTypes);
        battleChoice();
        System.out.println("");

    }

    public void battleChoice(){
    System.out.println(" You have entered a battle with " + this.enemy.getName() );//+ current enemy whenever we assign that value
    System.out.println("Do you want to fight or run?");
    String battleChoice = myScanner.nextLine().toLowerCase(Locale.ROOT);

    if (battleChoice.equals("run")){
            run();
    } else if (battleChoice.equals("fight")){
        fight();
    } else {
        System.out.println("you have entered an invalid action");
        battleChoice();
            }
    }
    public void fight(){
        String fightChoice;
        Game.character.setMaxHealth(Game.character.getHealth());
        printCurrentStats();
        fightChoice = myScanner.nextLine().toLowerCase(Locale.ROOT);

        switch (fightChoice){
                case "attack":
                    attack();
                    break;
                case "use item":
                    Game.character.selectItem();
                    break;
                case "run":
                   run();
                   break;
                default:
                    System.out.println("Error: Expected attack, use item, or run");
                    fight();
      }
      if(stillFighting) {
          fight();
      }
}




    public void attack(){

        Game.character.attack(this.enemy);
        if(this.enemy.getHealth()<1){
            win();
        }
        this.enemy.attack(Game.character);
        if(Game.character.getHealth()< 1){
            lose();
        }
    }

    public void run(){
        if(Game.character.run(this.enemy)){
            System.out.println("you have successfully ran away from " + this.enemy.getName());
        } else{
            System.out.println("You were not fast enough to run away. you must stay and fight, good luck ");
            fight();
        }
    }

    public void printCurrentStats(){
        System.out.println("Your Current total health is :" + Game.character.getHealth() );
        System.out.println("Your current total attacking power is :" + (Game.character.getStrength() + enemy.getSpeed()) );
        System.out.println(this.enemy.getName()+"'s current total health is " + this.enemy.getHealth() );
        System.out.println(this.enemy.getName()+ "'s current total attacking power is :" + (this.enemy.getStrength()+this.enemy.getSpeed()));
        System.out.println("What action do  you want to take: Attack, Use Item, or Run?");
        System.out.println("If you attack the enemy will attack back.");
    }

    public void win(){
        Game.character.setXp((Game.character.getXp())+this.enemy.getXp());
        Game.character.setBezos((Game.character.getBezos())+ this.enemy.getBezos());
        System.out.println("You have defeated " + this.enemy.getName());
        System.out.println("You have earned " +Game.character.getXp() + "XP and " + Game.character.getBezos() + " Bezos");
        System.out.println("You received a "+this.enemy.rewardItem);
        Game.character.questItems.add(this.enemy.rewardItem);


       BattleArea area = (BattleArea) Game.currentArea;
        System.out.println("You will be returning to " + area.getPreviousArea());
        Game.currentArea = Game.world.get(area.getPreviousArea());
        Game.character.addXp();
                stillFighting = false;
    }

    public void lose(){
        Game.character.setHealth(Game.character.getMaxHealth());
        System.out.println("You have been killed by " + this.enemy.getName());
        System.out.println("You will respawn at lucino town");
        Game.currentArea = Game.world.get("lucino town");
        stillFighting = false;

    }

}
