package com.fourforfour.eldanialight;

import com.fourforfour.eldanialight.UIFrame.UI;
import com.fourforfour.eldanialight.characters.Character;
import com.fourforfour.eldanialight.characters.Player;
import com.fourforfour.eldanialight.controller.GameInterface;
import com.fourforfour.eldanialight.controller.SwingController;

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
    public static final String TEXT_CYAN = "\u001B[36m";

    public static void main(String[] args) throws InterruptedException {

//        Thread.sleep(3000);
//
//        //used to play the audio wav file when the game is running
//        Thread thread = new Thread(() -> {
//            SimpleAudioPlayer audioPlayer;
//            try {
//                String filePath = "Eldinias-Light/src/com/fourforfour/eldanialight/UIFrame/01-prelude game opener.wav";
//                audioPlayer = new SimpleAudioPlayer(filePath);
//                audioPlayer.play();
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        });
//        thread.start();

        GameInterface gi = new Game();
        SwingController controller = new SwingController(gi);
        UI ui = new UI(controller);
        ui.setVisible(true);
    }
}
