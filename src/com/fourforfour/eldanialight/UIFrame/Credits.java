package com.fourforfour.eldanialight.UIFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class Credits extends JPanel implements ActionListener {
        //Set up for timer
        Timer creditTimer = new Timer(15,this);
        String text;
        int textY = 600;


        public Credits(){
            JFrame window = new JFrame("Test");
            window.setSize(800,600);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setLocationRelativeTo(null);
            window.add(this);
            window.setVisible(true);
            this.setBackground(Color.black);


            text = "Hope you enjoyed the game!\n\n"
                    + "Story by:\n"
                    + "The original team 5\n\n"
                    + "Tamarris Jenkins, Anthony Johnson, \n Shannon Glover and Marco Bragado\n\n"
                    + "The new team 5 \n"
                    + "Jeffery Moreta, Danny Manandhar, \n Ibrahim Gurhan and Jasmine Meade\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                    + "Thank You!";

            creditTimer.start();

        }

        //The Graphics class is used for rendering 2D shapes, text and images
        public void paintComponent(Graphics g){
            super.paintComponent(g);

            //casting the Graphics g to Graphics2D which is the updated version
            Graphics2D g2d = (Graphics2D)g;
            g2d.setFont(new Font("Times new Roman,", Font.PLAIN,20));
            g2d.setColor(Color.white);
            //class rendering hints attribute to specify whether you want objects to be rendered as quickly as possible
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            int y = textY;

            for(String line : text.split("\n")){
                int stringLength = (int)g2d.getFontMetrics().getStringBounds(line,g2d).getWidth();
                int x = getWidth()/2 - stringLength/2;
                g2d.drawString(line,x, y +=28);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e){

            textY--;
            if(textY < -400){
                creditTimer.stop();
            }
            repaint();
        }

        public static void main(String[] args) {
            Credits credit = new Credits();
        }
    }




