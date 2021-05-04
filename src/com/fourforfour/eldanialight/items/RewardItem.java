package com.fourforfour.eldanialight.items;

import java.util.ArrayList;

class RewardItem extends Item{
    ArrayList<String> rewardItems = new ArrayList<>();


    public RewardItem(String name) {
        super(name);
    }

    public ArrayList getRewardItems() {
        return rewardItems;
    }


}//EOC