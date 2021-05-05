package com.fourforfour.eldanialight.items;

import com.fourforfour.eldanialight.characters.Character;

public class WearItem extends Item{
    //This class inherits from the Item class
    //These fields are used for stat enhancements
    private int health;
    private int defense;
    private int attack;
    private WearItemType wearItemType;

    //constructor for above fields
    public WearItem(String name, int health, int defense, int attack, int itemWorth, WearItemType wearItemType) {
        super(name);
        this.health = health;
        this.defense = defense;
        this.attack = attack;
        setItemWorth(itemWorth);
        setWearItemType(wearItemType);

    }
    //equips the item on the character and enhances their stats
    public void equipItem(Character character){
        character.setHealth(character.getHealth() + health);
        character.setDefense(character.getDefense() + defense);
        character.setStrength(character.getStrength() + attack);
    }

    //unequips the item on the character and decreases their stats
    public void unequipItem(Character character){
        character.setHealth(character.getHealth() - health);
        character.setDefense(character.getDefense() - defense);
        character.setStrength(character.getStrength() - attack);
    }

    public WearItemType getWearItemType() {
        return wearItemType;
    }

    public void setWearItemType(WearItemType wearItemType) {
        this.wearItemType = wearItemType;
    }

}
