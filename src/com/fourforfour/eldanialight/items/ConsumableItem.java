package com.fourforfour.eldanialight.items;

import com.fourforfour.eldanialight.characters.Character;

public class ConsumableItem  extends Item{
    // fields for the enhancer and item type
    private int enhancer;
    private ItemType type;

    // constructor to consume items
    public ConsumableItem(String name, int enhancer, ItemType type, int itemWorth) {
        super(name);
        this.enhancer = enhancer;
        this.type = type;
    }

    // Switch case to choose what stat user wants to enhance
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
}// EOC