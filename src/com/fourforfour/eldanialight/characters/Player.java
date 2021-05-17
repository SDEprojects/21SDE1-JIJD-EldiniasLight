package com.fourforfour.eldanialight.characters;

import com.fourforfour.eldanialight.battle.Utility;

import java.util.Scanner;

import static com.fourforfour.eldanialight.ColorChange.*;

public class Player extends Character implements BattleActions {
    // List is making a quest log and scanner is for user input
//    private List<Quest> questLog = new ArrayList<>();
    private static transient Scanner scanner = new Scanner(System.in);

    String type;
    int maxLevel = 20;
    int levelUpXp = 100;


    // Constructor, no args
    public Player() {
    }

    // The User is given task of creating their in game player. They are given the option to choose what type of
    // player they will create
    public static Player createPlayer() {
        Player player = new Player();

        // create intro
        System.out.println(TEXT_CYAN + "Welcome Champion and thank you for coming to aid of Eldinia!!");
        System.out.println(TEXT_CYAN + "Tell us about this brave soul who has come to defeat" +
                TEXT_RESET + TEXT_RED + " Gandolf the Black" + TEXT_CYAN);

        // creates PlayerName
        System.out.println("What shall we call you?:");
        // SHOW ANY CLASS OPTIONS
        player.setName(scanner.nextLine());

        System.out.println("Welcome " + player.getName() + ". Please go speak with the Warcheif at the town hall" + TEXT_RESET);
        return player;
    }

    // user can choose a fighter style but user has cannot see the options
    public void createPlayerClass() {
        System.out.println("What style of fighter are you?");
        String userInput = scanner.next();
    }

    @Override
    public void attack(Character character) {
        Enemy enemy = (Enemy) character;
        double attackingPower = (this.getStrength() + this.getSpeed()) * Utility.randomNumber();
        double defendingPower = enemy.defend() * Utility.randomNumber();

        if (attackingPower > defendingPower)
            enemy.setHealth((int) (enemy.getHealth() - (attackingPower - defendingPower)));
    }

    @Override
    public boolean run(Character character) {
        Enemy enemy = (Enemy) character;
        return (this.getSpeed() * Utility.randomNumber()) > (enemy.getSpeed() * Utility.randomNumber());
    }

    @Override
    public int defend() {
        return getDefense();
    }

    @Override
    public void use() {
    }

    // Method to view your stats
    public String viewStats() {
        return "Name:" + this.getName() + " *** " + "Health:" + this.getHealth() +
                "\nDefense:" + this.getDefense() + " *** " + "Strength:" + this.getStrength() +
                "\nSpeed:" + this.getSpeed() + " *** " + "Intel:" + this.getIntel() +
                "\nBezos:" + this.getBezos() + " *** " + "XP:" + this.getXp() +
                "\nLevel:" + this.getLevel();
    }

    public String getType() {
        return this.type;
    }

    public String checkLevelUp() {
        String result="";
        /*
         * This  Method check to see if the player's level is high than the make level.
         * If the level is lower than maxLevel then the method checks the
         */
        if (getLevel() < maxLevel && getXp() >= levelUpXp) {

            this.setLevel(this.getLevel() + 1);
            this.setHealth(this.getHealth() + 15);
            this.setBezos(this.getBezos() + 15);

            switch (this.getType()) {
                case "mage":
                    this.setDefense(this.getDefense() + 10);
                    this.setStrength(this.getStrength() + 10);
                    this.setSpeed(this.getSpeed() + 15);
                    this.setIntel(this.getIntel() + 25);
                    break;
                case "archer":
                    this.setDefense(this.getDefense() + 15);
                    this.setStrength(this.getStrength() + 15);
                    this.setSpeed(this.getSpeed() + 20);
                    this.setIntel(this.getIntel() + 10);
                    break;
                case "knight":
                    this.setDefense(this.getDefense() + 20);
                    this.setStrength(this.getStrength() + 25);
                    this.setSpeed(this.getSpeed() + 15);
                    break;
            }

            this.setDefense(this.getDefense());
            this.setStrength(this.getStrength());
            this.setSpeed(this.getSpeed());
            this.setIntel(this.getIntel());

            levelUpXp = levelUpXp + 100;
            result = "You have leveled up!! You are now level:" + this.getLevel();

        }
        return result;

    }

//    public void addQuest(Quest quest){
//        questLog.add(quest);
//        System.out.println(quest.getName() + " has been added to your quest log");
//    }
}// EOC