package com.fourforfour.eldanialight;

import org.junit.Before;
import org.junit.Test;

public class DataParserTest {
    DataParser characters;
    // DataParser items;

    @Before
    public void setUp() {
        characters = new DataParser("characters");
        // items = new DataParser("items");
    }

    @Test
    public void isClass() {
        System.out.println(characters.isClass("TEST"));
        System.out.println(characters.isClass("Mage"));
        System.out.println(characters.isClass("mage"));
    }

    @Test
    public void createCharacter() {
    }
}