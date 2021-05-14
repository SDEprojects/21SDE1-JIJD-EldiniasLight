package com.fourforfour.eldanialight.controller;

public interface GameInterface {
    //Used to get the image path for pictures.
    String getLocationImagePath();

    String submitPlayerString(String input);

    String getDescriptionText();
}