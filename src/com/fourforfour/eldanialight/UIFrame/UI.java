package com.fourforfour.eldanialight.UIFrame;

import com.fourforfour.eldanialight.SimpleAudioPlayer;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class UI extends JFrame {

    //This method creates the frame to begin the game
    private JFrame frame, locationOneScreen; //Creates a frame
    private JTextArea textArea, locationInfo;
    private BufferedImage image;
    private JLabel titleLabel, commandInputLabel, locationImageContainer, commandLabel;
    private JTextField playerInputTF;
    private JPanel titleNamePanel, startButtonPanel, exitButtonPanel;
    private JButton startButton, exitButton;

    HandleSubmitButtonWhenClicked submitHandle = new HandleSubmitButtonWhenClicked();

    UI() {
        //Setting up the title of the game
        titleLabel = new JLabel("Eldinias Light"); //create a label and set text of label
        titleLabel.setBounds(240, 0, 200, 50);
        titleLabel.setFont(new Font("Book Antiqua", Font.BOLD, 20));//sets font of the text

        commandLabel = new JLabel("Command Label"); //create a label and set text of label
        commandLabel.setForeground(Color.RED);
        commandLabel.setBounds(240, 525, 100, 100);
        commandLabel.setFont(new Font("Book Antiqua", Font.BOLD, 10));//sets font of the text

        //Setting up the location image
        Image locationImage = null;
        try {
            locationImage = ImageIO.read(new File("Eldinias-Light/data/images/Opening screen.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        locationImage = locationImage.getScaledInstance(580, 300, Image.SCALE_SMOOTH);
        locationImageContainer = new JLabel();
        locationImageContainer.setIcon(new ImageIcon(locationImage));
        locationImageContainer.setBounds(10, 50, 580, 300);

        //setting up location info label
        locationInfo = new JTextArea(25, 40);
        locationInfo.setBounds(10, 360, 580, 120);
        locationInfo.setEditable(false);
        locationInfo.setLineWrap(true); //fit all the words in the given area
        locationInfo.setWrapStyleWord(true);
        locationInfo.setText("Need to grab the info from the JSON.");
        locationInfo.setFont(new Font("Book Antiqua", Font.PLAIN, 15));//sets font of the text
        locationInfo.setBackground(Color.orange);

        //Setting up the command input label
        commandInputLabel = new JLabel("Command Input:"); //create a label and set text of label
        commandInputLabel.setBounds(50, 567, 200, 50);
        commandInputLabel.setFont(new Font("Book Antiqua", Font.BOLD, 12));//sets font of the text
        commandInputLabel.setForeground(Color.ORANGE);

        //Setting up the player input box
        playerInputTF = new JTextField();
        playerInputTF.setBounds(46, 580, 500, 80);
        playerInputTF.setBackground(Color.BLACK);
        playerInputTF.setForeground(Color.ORANGE);
        playerInputTF.setFont(new Font("Book Antiqua", Font.PLAIN, 20));//sets font of the text
        playerInputTF.setHorizontalAlignment(JTextField.CENTER); //sets the start point to center
        playerInputTF.addActionListener(new HandleSubmitButtonWhenClicked());
        playerInputTF.addActionListener(new HandleSubmitButtonPressEnter());

        //Setting up the frame
        frame = new JFrame("Crypto Millionaire Presents");
        frame.setLocation(100, 100);
        frame.setLayout(null);
        frame.setSize(600, 700);//sets height/width of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exits out of application
        frame.setResizable(false);//user cannot resize frame
        frame.getContentPane().setBackground(Color.white);//sets frame background to the color of choice

        startButton = new JButton("Submit");
        startButton.setBounds(545, 600, 50, 20);
        startButton.addActionListener(new HandleSubmitButtonWhenClicked());
        startButton.addActionListener(new HandleSubmitButtonWhenClicked());
        startButton.setBackground(Color.white);
        startButton.setForeground(Color.black);


        frame.add(startButton);
        frame.add(titleLabel);
        frame.add(commandLabel);
        frame.add(commandInputLabel);
        frame.add(playerInputTF);
        frame.add(locationInfo);
        frame.add(locationImageContainer);
        frame.setVisible(true);//makes the frame visible


    }

    public void displayLocationOne() {
        //closes displayOpenScreen panel when called
        locationOneScreen = new JFrame("Crypto Millionaire Presents");
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);
        frame.setVisible(false);

        //Setting up the frame
        locationOneScreen.setVisible(true); //makes the frame visible
        locationOneScreen.setSize(1000, 600);//sets height/width of the frame
        locationOneScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//exits out of application
        locationOneScreen.setResizable(false);//user cannot resize frame
        locationOneScreen.getContentPane().setBackground(Color.white);//sets frame background to the color of choice
        locationOneScreen.setLayout(null);//set text is a certain position

        //setting up the test area inside the frame

        textArea = new JTextArea("This is working.");//example text to see where the string shows u[
        textArea.setBounds(50, 410, 700, 150);//sets the dimensions of the text box
        textArea.setBackground(Color.black);//sets the box to the color of choice
        textArea.setForeground(Color.white);//sets the words in the text box to color of choice
        textArea.setLineWrap(true);//a multi-line area that displays plain text
        textArea.setWrapStyleWord(true);// for your text is wrapped by words and not by the characters
        textArea.setFont(new Font("Book Antiqua", Font.PLAIN, 26));//sets font of the text
        locationOneScreen.add(textArea);//makes the text appear
    }

    private void processSubmitInput(){
        //String inputText = playerInputTF.getText().toLowerCase();
        //String result = controller....
        System.out.println("hey");

    }

    //Transitions between the open screen and first location screen
    public class HandleSubmitButtonWhenClicked implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("hey");
        }
    }

    public class HandleSubmitButtonPressEnter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(playerInputTF.getText());
        }
    }

    public static void main(String[] args) {
        UI ui = new UI();
        //used to play the audio wav file when the game is running
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
//    }
    }
}