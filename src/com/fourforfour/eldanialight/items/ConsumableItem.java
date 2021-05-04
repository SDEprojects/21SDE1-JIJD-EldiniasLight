package com.fourforfour.eldanialight.items;

import com.fourforfour.eldanialight.characters.Character;

public class ConsumableItem  extends Item{

    private int enhancer;
    private ItemType type;

    public ConsumableItem(String name, int enhancer, ItemType type, int itemWorth) {
        super(name);
        this.enhancer = enhancer;
        this.type = type;
    }

    public void useItem(Character player){
        switch (this.type){
            case HEALTH:
                player.setHealth(player.getHealth() + enhancer);
                break;
            case DEFENSE:
                player.setDefense(player.getDefense() + enhancer);
                break;
            case INTELLECT:
                player.setIntel(player.getIntel() + enhancer);
                break;
            case STRENGTH:
                player.setStrength(player.getStrength() + enhancer);
            default:
                System.out.println("Cant help you...");
        }
    }

    //method that checks type of potion and calls function accordingly


}
