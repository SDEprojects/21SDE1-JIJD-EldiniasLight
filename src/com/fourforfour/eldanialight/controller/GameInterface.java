package com.fourforfour.eldanialight.controller;

public interface GameInterface {
    //Used to get the image path for pictures.
    String getLocationImagePath();

    //this processes the users input and send to the Game engine
    String submitPlayerString(String input);

    //this helps us to automatically grab the games current cities description
    String getDescriptionText();
}