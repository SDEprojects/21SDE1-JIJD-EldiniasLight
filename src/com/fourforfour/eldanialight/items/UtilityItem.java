package com.fourforfour.eldanialight.items;

import java.util.ArrayList;

public class UtilityItem  extends Item{
    // This class inherits from Item class
    // ArrayList for utility items
    ArrayList<String> utilityItems = new ArrayList<>();

    // This is to get the reward name
    public UtilityItem(String name) {
        super(name);
    }

    public ArrayList getUtilityItems() {
        return utilityItems;
    }
}// EOC