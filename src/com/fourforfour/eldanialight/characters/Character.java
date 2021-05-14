package com.fourforfour.eldanialight.characters;

import com.fourforfour.eldanialight.Game;

import java.util.*;

public class Character implements java.io.Serializable {
    //Character class is used by Player,Enemy, Enemies,Boss
    //FIELDS
    private String name;
    private double health;
    private int strength;
    private int defense;
    int bezos;
    private int intel;
    private int speed;
    private int xp;
    private double maxHealth;
    private int level;

    //List for the player's items, quest items and equipment
    public HashMap<String, Integer> items = new HashMap<>();
    public List<String> questItems = new ArrayList<>();
    transient Scanner scanner = new Scanner(System.in);


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
        return health;
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

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void addItem(String itemName, int value) {
        items.put(itemName,value);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    //this method has you enter a item name, it will loop through the items and check it's size
    // and the output is you will either have items or you won't
    public String viewInventory() {
        String result = "";
        List<String> item = new ArrayList<>();

        if (items.size() == 0){
            return "There is nothing in the inventory";
        }

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            item.add(entry.getKey());
        }

        return "\nYour inventory -->"+ item;
    }
    public String itemsToSell() {
        List<String> item = new ArrayList<>();

        if (items.size() == 0){
            return "There is nothing in the inventory";
        }

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            item.add(entry.getKey());
        }

        return "\nItems you can sell from your inventory -->" +
                "\n" + item;
    }



    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }


}// EOC