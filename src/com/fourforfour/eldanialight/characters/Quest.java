package com.fourforfour.eldanialight.characters;

import com.fourforfour.eldanialight.items.Item;

abstract class Quest {
    private String name;
    private Item reward;
    private int xpReward;
    private boolean isComplete;


    public Quest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean completion();

    public int giveXP(){
        return xpReward;
    }

    public Item giveItem(){
        return reward;
    }

    public boolean checkCompletion(){
        return isComplete;
    }
}

