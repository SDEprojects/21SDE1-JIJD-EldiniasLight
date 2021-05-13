package com.fourforfour.eldanialight.controller;

import com.fourforfour.eldanialight.Game;

public class SwingController {
    private GameInterface gi;

    //CONSTRUCTOR
    public SwingController (GameInterface gi){
        this.gi = gi;
    }

    public String getDescription() {
        return gi.getDescriptionText();
    }

    // GUI TEST FUNCTIONS
    public String processInput(String input) {
        return gi.submitPlayerString(input);
    }

}
