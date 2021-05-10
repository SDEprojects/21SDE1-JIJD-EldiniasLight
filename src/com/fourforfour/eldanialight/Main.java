package com.fourforfour.eldanialight;

import com.fourforfour.eldanialight.characters.Character;
import com.fourforfour.eldanialight.characters.Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    //Used to change the color of the text
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_RED = "\u001B[31m";

    public static void main(String[] args) throws InterruptedException {

        //Game intro
        System.out.println(TEXT_YELLOW + "The once peaceful land of Eldina has recently been taken over by the evil warlock"
                + TEXT_RESET + TEXT_RED + " Tyroneious" + " the Black" + TEXT_RESET + TEXT_YELLOW + " who has cast a darkness upon" +
                " the land.\n You are the one chosen to defeat" + TEXT_RESET + TEXT_RED + " Tyroneious" +
                TEXT_RESET + TEXT_YELLOW + " and his " + "Army to restore Eldina back to its peaceful ways. \n" + TEXT_RESET);

        //delays text as the game progresses
        Thread.sleep(3000);
        Scanner myScanner = new Scanner(System.in);
        AreaKommands areaComamnde = new AreaKommands();
        Game game = new Game(Player.createPlayer());
        String inputString = "";

        //used to play the audio wav file when the game is running
        Thread thread = new Thread(() -> {
            SimpleAudioPlayer audioPlayer;
            try {
                String filePath = "src/com/fourforfour/eldanialight/ffixhome.wav";
                audioPlayer = new SimpleAudioPlayer(filePath);
                audioPlayer.play();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        thread.start();

        //User enters a command listed from the Commands ENUM
        while (true) {
            System.out.println("Enter a command: ");
            Game.currentArea.printCommands();
            System.out.print("$ ");
            inputString = myScanner.nextLine().toUpperCase(Locale.ROOT);
            game.play(inputString);
            if (inputString.equalsIgnoreCase("exit()")) {
                System.exit(0);
            }
        }
    }


}
