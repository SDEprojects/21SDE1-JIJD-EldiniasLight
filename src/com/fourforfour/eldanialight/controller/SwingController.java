package com.fourforfour.eldanialight.controller;

public class SwingController {
    private GameInterface gi;

    //CONSTRUCTOR
    public SwingController (GameInterface gi){
        this.gi = gi;
    }

    public String getLocationImagePath(){
        return gi.getLocationImagePath();
    }

    public String getLocationDescription(String location){
        return gi.getLocationDescription(location);
    }
}
