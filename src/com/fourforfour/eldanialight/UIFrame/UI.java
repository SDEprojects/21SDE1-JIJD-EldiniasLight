package com.fourforfour.eldanialight.UIFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {

    //This method creates the frame to begin the game
    private JFrame frame; //Creates a frame
    private JTextArea textArea;
    private BufferedImage image;
    private ImageIcon locationImage;
    private JLabel titleLabel;
    private JLabel commandInputLabel;
    private JLabel locationImageContainer;
    private JTextArea locationInfo;
    private JTextField playerInputTF;
    JFrame startScreen, locationOneScreen; //Creates a frame
    JLabel titleNameLabel;
    Image backgroundImage;
    Font titleFont = new Font("Times New Roman", Font.PLAIN,36);
    JTextArea textArea;
    ImageIcon image;
    JLabel label = new JLabel();
    Container container;
    JPanel titleNamePanel, startButtonPanel, optionsButtonPanel, exitButtonPanel;
    JButton startButton, optionButton, exitButton;
    TitleScreeHandler titleScreenHandler = new TitleScreeHandler();

    UI(){

        //Setting up the title of the game
        titleLabel = new JLabel("Eldinias Light"); //create a label and set text of label
        titleLabel.setBounds(240,0,200,50);
        titleLabel.setFont(new Font("Book Antiqua", Font.BOLD, 20));//sets font of the text

        //Setting up the location image
        Image locationImage = null;
        try {
            locationImage = ImageIO.read(new File("Eldinias-Light/data/images/Opening screen.jpg"));
        } catch(IOException e){
            e.printStackTrace();
        }
        locationImage = locationImage.getScaledInstance(580,300,Image.SCALE_SMOOTH);
        locationImageContainer = new JLabel();
        locationImageContainer.setIcon(new ImageIcon(locationImage));
        locationImageContainer.setBounds(10,50,580,300);

        //setting up location info label
        locationInfo = new JTextArea(25, 40);
        locationInfo.setBounds(10,360,580,120);
        locationInfo.setEditable(false);
        locationInfo.setLineWrap(true); //fit all the words in the given area
        locationInfo.setWrapStyleWord(true);
        locationInfo.setText("Need to grab the info from the JSON.");
        locationInfo.setFont(new Font("Book Antiqua", Font.PLAIN, 15));//sets font of the text
        locationInfo.setBackground(Color.orange);

        //Setting up the command input label
        commandInputLabel = new JLabel("Command Input:"); //create a label and set text of label
        commandInputLabel.setBounds(50,567,200,50);
        commandInputLabel.setFont(new Font("Book Antiqua", Font.BOLD, 12));//sets font of the text
        commandInputLabel.setForeground(Color.ORANGE);

        //Setting up the player input box
        playerInputTF = new JTextField();
        playerInputTF.setBounds(46,580,500,80);
        playerInputTF.setBackground(Color.BLACK);
        playerInputTF.setForeground(Color.ORANGE);
        playerInputTF.setFont(new Font("Book Antiqua", Font.PLAIN, 20));//sets font of the text
        playerInputTF.setHorizontalAlignment(JTextField.CENTER); //sets the start point to center
        //playerInputTF.addActionListener(new HandleEnterPressOnPlayerInputTF()); //Need this later

        //Setting up the frame
        frame = new JFrame("Crypto Millionaire Presents");
        frame.setLocation(100, 100);
        frame.setLayout(null);
        frame.setSize(600, 700);//sets height/width of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exits out of application
        frame.setResizable(false);//user cannot resize frame
        frame.getContentPane().setBackground(Color.white);//sets frame background to the color of choice

        frame.add(titleLabel);
        frame.add(commandInputLabel);
        frame.add(playerInputTF);
        frame.add(locationInfo);
        frame.add(locationImageContainer);
        frame.setVisible(true);//makes the frame visible

////        //Plays music will be moved to JSON
////        Thread thread = new Thread(() -> {
////            SimpleAudioPlayer audioPlayer;
////            try {
////                String filePath = "C:\\Users\\Jasmi\\IdeaProjects\\Eldinias-Light\\Eldinias-Light\\UIFrame\\01-prelude game opener.wav";
////                audioPlayer = new SimpleAudioPlayer(filePath);
////                audioPlayer.play();
////            } catch (Exception e) {
////                System.out.println(e.getMessage());
////            }
////        });
////        thread.start();
////    }
    }
  
    public void displayLocationOne() {
        //closes displayOpenScreen panel when called
        locationOneScreen = new JFrame("Crypto Millionaire Presents");
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);
        startScreen.setVisible(false);

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
        textArea.setForeground(Color.red);//sets the words in the text box to color of choice
        textArea.setLineWrap(true);//a multi-line area that displays plain text
        textArea.setWrapStyleWord(true);// for your text is wrapped by words and not by the characters
        textArea.setFont(new Font("Book Antiqua", Font.PLAIN, 26));//sets font of the text
        locationOneScreen.add(textArea);//makes the text appear

    }

    //Transitions between the open screen and first location screen
    public class TitleScreeHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            displayLocationOne();
        }
    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.displayOpenScreen();
    }
}