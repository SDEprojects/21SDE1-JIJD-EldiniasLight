package com.fourforfour.eldanialight.UIFrame;

import com.fourforfour.eldanialight.controller.SwingController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {

    private SwingController controller;

    private final int FRAME_X_SIZE = 520;
    private final int FRAME_Y_SIZE = 900;

    private JTextArea displayArea;
    private JTextField playerInputTF;
    private JLabel saveGameMsgLbl;
    private JTextArea playerMessageLbl;
    private JButton submitCommandBtn;

    private JLabel locationImageContainer;




    public UI(SwingController controller) {
        this.controller = controller;

        setLocation(100,100);
        setSize(FRAME_X_SIZE,FRAME_Y_SIZE);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image locationImage = null;
        try {
            locationImage = ImageIO.read(new File("data/images/Opening screen.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        locationImageContainer = new JLabel(new ImageIcon(locationImage), SwingConstants.CENTER);
        locationImageContainer.setBounds(0,50,500,260);
        add(locationImageContainer);


        displayArea = new JTextArea(25,40);
        displayArea.setBounds(25,315,430,250);
        displayArea.setEditable(false);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        displayArea.setText("The once peaceful land of Eldina has recently been taken over by the evil warlock Tyroneious the Black who has cast a darkness upon the land. You are the one chosen to defeat Tyroneious and his Army to restore Eldina back to its peaceful ways.");
        displayArea.setBackground(Color.orange);
        displayArea.setFont(new Font("Book Antiqua", Font.PLAIN, 15));//sets font of the text
        add(displayArea);


        playerMessageLbl = new JTextArea(15,40);
        playerMessageLbl.setBounds(25,570,430,120);
        playerMessageLbl.setEditable(false);
        playerMessageLbl.append("Welcome Champion and thank you for coming to aid of Eldinia!!");
        playerMessageLbl.append("\nTell us about this brave soul who has come to defeat Tyronious the Black\n");
        playerMessageLbl.append("\nWhat type of player are you");
        playerMessageLbl.append("\nChoose from Mage or Knight or Archer");
        playerMessageLbl.setWrapStyleWord(true);
        playerMessageLbl.setLineWrap(true);
        playerMessageLbl.setFont(new Font("Book Antiqua", Font.PLAIN, 15));//sets font of the text
        add(playerMessageLbl);


//        saveGameMsgLbl = new JLabel("use save/load commands to save/load game", SwingConstants.LEFT);
//        saveGameMsgLbl.setBounds(25,780,430,25);
//        JLabel label = new JLabel("I'm bold");
//        Font font = new Font("Courier", Font.BOLD,12);
//        saveGameMsgLbl.setFont(font);
//        Font f = label.getFont();
//        saveGameMsgLbl.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
//        add(saveGameMsgLbl);

        playerInputTF = new JTextField();
        playerInputTF.setBounds(25,695,430,25);
        playerInputTF.addActionListener(new HandleSubmitButtonPressEnter());
        playerInputTF.setBackground(Color.BLACK);
        playerInputTF.setForeground(Color.ORANGE);
        playerInputTF.setFont(new Font("Book Antiqua", Font.PLAIN, 20));//sets font of the text
        playerInputTF.setHorizontalAlignment(JTextField.CENTER);
        add(playerInputTF);

        submitCommandBtn = new JButton();
        submitCommandBtn.setBounds(380,725,75,25);
        submitCommandBtn.setText("Submit");
        submitCommandBtn.addActionListener(new HandleSubmitButtonWhenClicked());
        add(submitCommandBtn);

    }

    private void processSubmitInput(){
        String inputText = playerInputTF.getText().toLowerCase();
        String result = controller.processInput(inputText);
        playerMessageLbl.setText(result);
        String displayData = controller.getDescription();
        displayArea.setText(displayData);

        playerInputTF.setText("");

    }

    public class HandleSubmitButtonWhenClicked implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            processSubmitInput();
        }
    }

    public class HandleSubmitButtonPressEnter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            processSubmitInput();
        }
    }

//    public static void main(String[] args) {
//        UI ui = new UI();
//        //used to play the audio wav file when the game is running
////        Thread thread = new Thread(() -> {
////            SimpleAudioPlayer audioPlayer;
////            try {
////                String filePath = "Eldinias-Light/src/com/fourforfour/eldanialight/UIFrame/01-prelude game opener.wav";
////                audioPlayer = new SimpleAudioPlayer(filePath);
////                audioPlayer.play();
////            } catch (Exception e) {
////                System.out.println(e.getMessage());
////            }
////        });
////        thread.start();
////    }
//    }
}