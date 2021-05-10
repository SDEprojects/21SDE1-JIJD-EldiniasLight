package com.fourforfour.eldanialight.items;

import java.util.ArrayList;

class RewardItem extends Item {
    //This class inherits from Item class
    //ArrayList for reward items
    ArrayList<String> rewardItems = new ArrayList<>();

    //This is to get the reward name
    public RewardItem(String name) {
        super(name);
    }

    public ArrayList getRewardItems() {
        return rewardItems;
    }


}//EOC