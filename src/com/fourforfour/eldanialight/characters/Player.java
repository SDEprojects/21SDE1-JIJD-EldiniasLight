package com.fourforfour.eldanialight.characters;

import java.util.Scanner;

import static com.fourforfour.eldanialight.ColorChange.*;

public class Player extends Character {
    // List is making a questlog and scanner is for user input
    // private List<Quest> questLog = new ArrayList<>();
    private static transient Scanner scanner = new Scanner(System.in);

    // fields for maxLevel and leveling up
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
                TEXT_RESET + TEXT_RED + " Tyronious the Black" + TEXT_CYAN);

        // creates PlayerName
        System.out.println("What shall we call you?:");
        // SHOW ANY CLASS OPTIONS
        player.setName(scanner.nextLine());

        System.out.println("Welcome " +player.getName()+ ". Please go speak with the Warcheif at the town hall" + TEXT_RESET);
        return player;
    }

    // user can choose a fighter style but user has cannot see the options
    public void createPlayerClass() {
        System.out.println("What style of fighter are you?");
        String userInput = scanner.next();
    }


    // Method to view your stats
    public String viewStats() {
        return "Name:" + this.getName() + " *** " + "Health:" + this.getHealth() +
        "\nDefense:" + this.getDefense() + " *** " + "Strength:" + this.getStrength() +
        "\nSpeed:" + this.getSpeed() +" *** " +"Intel:" + this.getIntel() +
         "\nBezos:" + this.getBezos() + " *** " + "XP:" + this.getXp() +
        "\nLevel:" + this.getLevel();

    }
}// EOC