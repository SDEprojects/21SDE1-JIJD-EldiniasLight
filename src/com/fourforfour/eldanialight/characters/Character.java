package com.fourforfour.eldanialight.characters;

import com.fourforfour.eldanialight.Game;
import com.fourforfour.eldanialight.items.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Character {

    //FIELDS
    private String name;
    private double health;
    private int strength;
    private int defense;
    private int bezos;
    private int intel;
    private int speed;
    private int xp;
    private double  maxHealth;
    private int level;

    private PlayerType playerType;
    public List<Item> items = new ArrayList<>();
    public List<String> questItems = new ArrayList<>();
    public List<WearItem> equipment = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);


    //CONSTRUCTOR

    //no args
    public Character() {

    }
    //This constructor was to all for original enemy creation

    //all args
    public Character(String name, double health, int strength, int defense, int bezos, int intel, int speed) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.defense = defense;
        this.bezos = bezos;
        this.intel = intel;
        this.speed = speed;
    }
    //Getter and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHealth() {
        return health ;
    }

    public void setHealth(double health) {
       this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
      this.defense = defense;
    }

    public int getBezos() {
        return bezos;
    }

    public void setBezos(int bezos) {
        this.bezos = bezos;
    }

    public int getIntel() {
        return intel;
    }

    public void setIntel(int intel) {
        this.intel = intel;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addWearableItem(WearItem item) {
        item.equipItem(this);
        equipment.add(item);
    }

    public void viewInventory() {
        System.out.println("\nEnter the item name or back: ");
        System.out.println("\n***INVENTORY***");

        for (Item item : items) {
            System.out.println(item.getName());
        }
        if (items.size() == 0) {
            System.out.println("There is nothing in the inventory");
        }
        System.out.println("****************");
        System.out.println("# of Items in Inventory: " + items.size() + "\n");
    }


    // select item
    public void selectItem() {
        viewInventory();
        System.out.println("Which Item would you like to select? ");
        String userInput = scanner.nextLine();
        if (checkItem(userInput)) {
            useItem(findItem(userInput));
        } else if (userInput.equalsIgnoreCase("back")) {
                Game.currentArea.printCommands();
        } else {
            System.out.println("Invalid Selection");
            selectItem();
        }

    }

    // check item  -- Will check to see if item is in player inventory
    public boolean checkItem(String itemName) {
        for (Item currentItem : items) {
            if (itemName.equalsIgnoreCase(currentItem.getName())) {
                return true;
            }
        }
        return false;
    }

    // check item type and will use appropriately
    public void useItem(Item item) {
        if (item instanceof ConsumableItem) {
            ((ConsumableItem) item).useItem(this);
            items.remove(item);
        } else if (item instanceof WearItem) {
            WearItem armor = (WearItem) item;
            equip(armor);
        }
    }

    public void equip(Item item) {
        System.out.println("Equip or Cancel");
        String userInput = scanner.nextLine();
        if (userInput.equalsIgnoreCase("Equip")) {
            if(isEquipped(findEquipment(item.getName())) && isTheSameWearType(item)){
                System.out.println("You already have the item equipped");
                return;
            }
            ((WearItem)item).equipItem(this);
            addWearableItem(((WearItem)item));
            items.remove(item);
        } else {
            System.out.println("Cannot equip item");
        }
    }

    public boolean isTheSameWearType(Item wearItem){
        for (WearItem armorType : equipment) {
            if(wearItem.getWearItemType().equals(armorType.getWearItemType())){
                return true;
            }
        }
        return false;
    }

    public boolean isEquipped(Item item) {
        for (Item currentEquipment : equipment) {
            if (currentEquipment.getName().equals(item.getName())) {
                return true;
            }
        }
        return false;
    }

    public Item findEquipment(String equipmentSelection){
        for (Item currentEquipment : equipment) {
            if(currentEquipment.getName().equals(equipmentSelection)){
                return currentEquipment;
            }
        }
        return null;
    }

    public Item findItem(String itemName) {
        for (Item currentItem : items) {
            if (itemName.equalsIgnoreCase(currentItem.getName())) {
                return currentItem;
            }
        }
        return null;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void pickUpItem(Item item) {
        String playerAction = scanner.nextLine();
        if (playerAction.equalsIgnoreCase("Pick up" + item.getName())) {
            items.add(item);
        }
    }
}//EOC
