package com.fourforfour.eldanialight.items;

import java.util.ArrayList;

public class UtilityItem  extends Item{
    ArrayList<String> utilityItems = new ArrayList<>();


    public UtilityItem(String name) {
        super(name);
    }

    public ArrayList getUtilityItems() {
        return utilityItems;
    }

}
