package com.fourforfour.eldanialight.UIFrame;

import com.fourforfour.eldanialight.controller.SwingController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame{

    //FIELDS
    private SwingController controller;
    private final int FRAME_X_SIZE = 600;
    private final int FRAME_Y_SIZE = 900;
    private JTextArea displayArea;
    private JTextField playerInputTF;
    private JLabel saveGameMsgLbl;
    private JLabel locationImageContainer;
    private JLabel titleLabel;
    private JTextArea playerMessageLbl;
    private JButton submitCommandBtn;
    Credits credits;


    public UI(SwingController controller) {
        this.controller = controller;
        this.add(credits);


        //SETTING UP FRAME
        setLocation(100,100);
        setSize(FRAME_X_SIZE,FRAME_Y_SIZE);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Setting up Title label
        titleLabel = new JLabel("Eldinias Light");
        titleLabel.setBounds(240, 0, 200, 50);
        titleLabel.setFont(new Font("Monospaced Bold", Font.BOLD, 20));
        add(titleLabel);

        //Displays image on JFrame
        Image locationImage = null;
        try {
            locationImage = ImageIO.read(new File("data/images/Opening screen.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Setting up location container
        locationImageContainer = new JLabel(new ImageIcon(locationImage), SwingConstants.CENTER);
        locationImageContainer.setBounds(15,50,570,260);
        add(locationImageContainer);

        //Setting up main display area that contains the messages
        displayArea = new JTextArea(25,40);
        displayArea.setBounds(15,315,570,250);
        displayArea.setEditable(false);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        displayArea.setText("The once peaceful land of Eldina has recently been taken over by the evil warlock Tyronious the Black who has cast a darkness upon the land. You are the one chosen to defeat Tyroneious and his Army to restore Eldina back to its peaceful ways.");
        displayArea.setBackground(Color.orange);
        displayArea.setFont(new Font("Book Antiqua", Font.PLAIN, 15));//sets font of the text
        add(displayArea);

        //Setting up the text area to show the commands available
        playerMessageLbl = new JTextArea(15,40);
        playerMessageLbl.setBounds(15,570,570,120);
        playerMessageLbl.setEditable(false);
        playerMessageLbl.append("Welcome Champion and thank you for coming to aid of Eldinia!!");
        playerMessageLbl.append("\nTell us about this brave soul who has come to defeat Tyronious the Black.\n");
        playerMessageLbl.append("\nWhat type of player are you?");
        playerMessageLbl.append("\n Options: Mage OR Knight OR Archer");
        playerMessageLbl.setWrapStyleWord(true);
        playerMessageLbl.setLineWrap(true);
        playerMessageLbl.setFont(new Font("Book Antiqua", Font.PLAIN, 15));//sets font of the text
        add(playerMessageLbl);

        saveGameMsgLbl = new JLabel("use save/load commands to save/load game", SwingConstants.CENTER);
        saveGameMsgLbl.setBounds(70,715,430,30);
        JLabel label = new JLabel("I'm bold");
        Font font = new Font("Courier", Font.BOLD,12);
        saveGameMsgLbl.setFont(font);
        Font f = label.getFont();
        saveGameMsgLbl.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        add(saveGameMsgLbl);

        //Setting up player's text input area
        playerInputTF = new JTextField();
        playerInputTF.setBounds(70,695,430,30);
        playerInputTF.addActionListener(new HandleSubmitButtonPressEnter());
        playerInputTF.setBackground(Color.BLACK);
        playerInputTF.setForeground(Color.ORANGE);
        playerInputTF.setFont(new Font("Book Antiqua", Font.PLAIN, 20));//sets font of the text
        playerInputTF.setHorizontalAlignment(JTextField.CENTER);
        playerInputTF.getCaret().setVisible(true);
        playerInputTF.setCaretColor(Color.ORANGE);
        add(playerInputTF);


        //Submit button
        submitCommandBtn = new JButton();
        submitCommandBtn.setBounds(250,740,75,25);
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
        locationImageContainer.setIcon(new ImageIcon(controller.getImage()));
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

}