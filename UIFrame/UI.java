import com.fourforfour.eldanialight.SimpleAudioPlayer;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
    public static void main(String[] args) {
        //This method creates the frame to begin the game

        JFrame frame; //Creates a frame
        JTextArea textArea;
        ImageIcon image;
        ImageIcon background;
        JLabel label;


        background = new ImageIcon("C:\\Users\\Jasmi\\IdeaProjects\\Eldinias-Light\\Eldinias-Light\\UIFrame\\Opening screen.jpg");
        image = new ImageIcon("C:\\Users\\Jasmi\\IdeaProjects\\Eldinias-Light\\Eldinias-Light\\UIFrame\\DOGE.jpg");
        frame = new JFrame();
        frame.setIconImage(image.getImage());


        frame.setVisible(true); //makes the frame visable
        frame.setSize(1000, 600);//sets height/width of the frame
        frame.setTitle("Crypto Millionaries Presents...");//sets title of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exits out of application
        frame.setResizable(false);//user cannot resize frame
        frame.getContentPane().setBackground(Color.white);//sets frame background to the color of choice
        frame.setLayout(null);//set text is a certain position


        textArea = new JTextArea("........");//example text to see where the string shows u[
        textArea.setBounds(50, 410, 700, 150);//sets the dimentions of the text box
        textArea.setBackground(Color.black);//sets the box to the color of choice
        textArea.setForeground(Color.white);//sets the words in the text box to color of choice
        textArea.setLineWrap(true);//a multi-line area that displays plain text
        textArea.setWrapStyleWord(true);// for your text is wrapped by words and not by the characters
        textArea.setFont(new Font("Book Antiqua", Font.PLAIN, 26));//sets font of the text
        frame.add(textArea);//makes the text appear

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
//
//
//    }
    }
}