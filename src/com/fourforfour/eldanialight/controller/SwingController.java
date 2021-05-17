package com.fourforfour.eldanialight.controller;

import com.fourforfour.eldanialight.Game;

public class SwingController {
    private GameInterface gi;

    //CONSTRUCTOR
    public SwingController (GameInterface gi){
        this.gi = gi;
    }

    //this helps us to automatically grab the games current cities description
    public String getDescription() {
        return gi.getDescriptionText();
    }

    // This method takes users input from the GUI and sends to the Game engine
    public String processInput(String input) {
        return gi.submitPlayerString(input);
    }

    public String getImage(){
        return gi.getLocationImagePath();
    }
}