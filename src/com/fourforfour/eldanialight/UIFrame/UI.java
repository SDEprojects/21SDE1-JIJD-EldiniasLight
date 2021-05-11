package com.fourforfour.eldanialight.UIFrame;

import com.fourforfour.eldanialight.SimpleAudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {

    //This method creates the frame to begin the game
    private JFrame startScreen, locationOneScreen; //Creates a frame
    private JLabel titleNameLabel;
    private Font titleFont = new Font("Times New Roman", Font.PLAIN,36);
    private JTextArea textArea;
    private ImageIcon image;
    private Container container;
    private JPanel titleNamePanel, startButtonPanel, optionsButtonPanel, exitButtonPanel;
    private JButton startButton, optionButton, exitButton;
    private TitleScreeHandler titleScreenHandler = new TitleScreeHandler();

    public void displayLocationOne() {
        //closes displayOpenScreen panel when called
        locationOneScreen = new JFrame();
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);
        startScreen.setVisible(false);

        //Setting up the frame
        locationOneScreen.setVisible(true); //makes the frame visible
        locationOneScreen.setSize(1000, 600);//sets height/width of the frame
        locationOneScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exits out of application
        locationOneScreen.setResizable(false);//user cannot resize frame
        locationOneScreen.getContentPane().setBackground(Color.white);//sets frame background to the color of choice
        locationOneScreen.setLayout(null);//set text is a certain position

        //setting up the test area inside the frame
        textArea = new JTextArea("Text that will come from JSON");//example text to see where the string shows u[
        textArea.setBounds(50, 410, 700, 150);//sets the dimensions of the text box
        textArea.setBackground(Color.black);//sets the box to the color of choice
        textArea.setForeground(Color.white);//sets the words in the text box to color of choice
        textArea.setLineWrap(true);//a multi-line area that displays plain text
        textArea.setWrapStyleWord(true);// for your text is wrapped by words and not by the characters
        textArea.setFont(new Font("Book Antiqua", Font.PLAIN, 26));//sets font of the text
        locationOneScreen.add(textArea);//makes the text appear
    }

    //Start Screen
    public void displayOpenScreen(){
        startScreen = new JFrame("Crypto Millionaire Presents");
        startScreen.setSize(1000,600);
        startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startScreen.getContentPane().setBackground(Color.black);
        startScreen.setLayout(null);
        startScreen.setVisible(true);
        container = startScreen.getContentPane();

        //Sets Icon to image below
        image = new ImageIcon();
        startScreen.setIconImage(image.getImage());

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(150,100,600,150);
        titleNamePanel.setBackground(Color.white);

        titleNameLabel = new JLabel("Eldina's Light");
        titleNameLabel.setForeground(Color.red);
        titleNameLabel.setFont(titleFont);

        titleNamePanel.add(titleNameLabel);
        container.add(titleNamePanel);

        startButton = new JButton("Start Game");
        startButton.setBackground(Color.white);
        startButton.setForeground(Color.black);
        startButton.addActionListener(titleScreenHandler);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(400,400,200,100);
        startButtonPanel.setBackground(Color.white);
        startButtonPanel.add(startButton);
        container.add(startButtonPanel);

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

        //used to play the audio wav file when the game is running
        Thread thread = new Thread(() -> {
            SimpleAudioPlayer audioPlayer;
            try {
                String filePath = "/Users/jasminemeade/IdeaProjects/Eldinas-Light/Eldinias-Light/src/com/fourforfour/eldanialight/UIFrame/01-prelude game opener.wav";
                audioPlayer = new SimpleAudioPlayer(filePath);
                audioPlayer.play();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        thread.start();


    }
}



