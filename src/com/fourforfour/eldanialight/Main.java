package com.fourforfour.eldanialight;

import com.fourforfour.eldanialight.UIFrame.UI;
import com.fourforfour.eldanialight.controller.GameInterface;
import com.fourforfour.eldanialight.controller.SwingController;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//        Thread.sleep(3000);
//
//        //used to play the audio wav file when the game is running
//        Thread thread = new Thread(() -> {
//            SimpleAudioPlayer audioPlayer;
//            try {
//                String filePath = "data/music/ffixhome.wav";
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
}// EOC