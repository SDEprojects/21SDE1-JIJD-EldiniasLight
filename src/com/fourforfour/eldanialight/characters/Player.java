package com.fourforfour.eldanialight.characters;


import java.awt.*;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class Player extends Character {
    //Changes the text colors
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_CYAN = "\u001B[36m";

    //List is making a questlog and scanner is for user input
//    private List<Quest> questLog = new ArrayList<>();
    private static transient Scanner scanner = new Scanner(System.in);

    //fields for maxLevel and leveling up
    int maxLevel = 20;
    int levelUpXp = 100;

    //Constructor, no args
    public Player() {

    }
    // The User is given task of creating their in game player. They are given the option to choose what type of
    //player they will create

    public static Player createPlayer() {
        Player player = new Player();

        //create intro
        System.out.println(TEXT_CYAN + "Welcome Champion and thank you for coming to aid of Eldinia!!");
        System.out.println(TEXT_CYAN + "Tell us about this brave soul who has come to defeat" + TEXT_RESET + TEXT_RED
                + " Tyronious the Black" + TEXT_CYAN);

        // creates PlayerName
        System.out.println("What shall we call you?:");
        // SHOW ANY CLASS OPTIONS
        player.setName(scanner.nextLine());



        System.out.println("Welcome " + player.getName() +  ". " +
                "Please go speak with the Warcheif at the town hall" + TEXT_RESET);
        return player;

    }
    //user can choose a fighter style but user has cannot see the options
    public void createPlayerClass() {
        System.out.println("What style of fighter are you?");
        String userInput = scanner.next();


    }

    //Method to view your stats
    public void viewStats() {
        System.out.println("Name:" + this.getName());
        System.out.println("Health:" + this.getHealth());
        System.out.println("Defense:" + this.getDefense());
        System.out.println("Strength:" + this.getStrength());
        System.out.println("Speed:" + this.getSpeed());
        System.out.println("Intel:" + this.getIntel());
        System.out.println("Bezos:" + this.getBezos());
        System.out.println("XP:" + this.getXp());
        System.out.println("Level:" + this.getLevel());

    }

}//EOC
