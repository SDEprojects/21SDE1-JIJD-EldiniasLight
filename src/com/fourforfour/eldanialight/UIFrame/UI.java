package com.fourforfour.eldanialight.UIFrame;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {

    //This method creates the frame to begin the game
    JFrame frame; //Creates a frame
    JTextArea textArea;
    ImageIcon image;
    ImageIcon background;
    JLabel label = new JLabel();

    public void display(){
        //Setting up the frame
        frame = new JFrame("Crypto Millionaire Presents");
        frame.setVisible(true); //makes the frame visible
        frame.setSize(1000, 600);//sets height/width of the frame
//        frame.setTitle("Crypto Millionaires Presents...");//sets title of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exits out of application
        frame.setResizable(false);//user cannot resize frame
        frame.getContentPane().setBackground(Color.white);//sets frame background to the color of choice
        frame.setLayout(null);//set text is a certain position

        //setting up the test area inside the frame
        textArea = new JTextArea("This is working.");//example text to see where the string shows u[
        textArea.setBounds(50, 410, 700, 150);//sets the dimentions of the text box
        textArea.setBackground(Color.black);//sets the box to the color of choice
        textArea.setForeground(Color.red);//sets the words in the text box to color of choice
        textArea.setLineWrap(true);//a multi-line area that displays plain text
        textArea.setWrapStyleWord(true);// for your text is wrapped by words and not by the characters
        textArea.setFont(new Font("Book Antiqua", Font.PLAIN, 26));//sets font of the text
        frame.add(textArea);//makes the text appear

        background = new ImageIcon("C:\\Users\\Jasmi\\IdeaProjects\\Eldinias-Light\\Eldinias-Light\\UIFrame\\Opening screen.jpg");
        image = new ImageIcon("/src/UIFrame/DOGE.jpg");
        //image = new ImageIcon("C:\\Users\\Jasmi\\IdeaProjects\\Eldinias-Light\\Eldinias-Light\\UIFrame\\DOGE.jpg");
        label.setIcon(image);
        frame = new JFrame();
        frame.setIconImage(image.getImage());

//        //Plays music will be moved to JSON
//        Thread thread = new Thread(() -> {
//            SimpleAudioPlayer audioPlayer;
//            try {
//                String filePath = "C:\\Users\\Jasmi\\IdeaProjects\\Eldinias-Light\\Eldinias-Light\\UIFrame\\01-prelude game opener.wav";
//                audioPlayer = new SimpleAudioPlayer(filePath);
//                audioPlayer.play();
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        });
//        thread.start();
//    }
    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.display();
    }
}
