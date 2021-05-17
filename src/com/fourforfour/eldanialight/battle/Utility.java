package com.fourforfour.eldanialight.battle;

import java.util.Random;

public class Utility {

    // called in class Enemy for randomization (double)
    public static double randomNumber() {
        Random rand = new Random();
        return rand.nextDouble();
    }
}//EOC