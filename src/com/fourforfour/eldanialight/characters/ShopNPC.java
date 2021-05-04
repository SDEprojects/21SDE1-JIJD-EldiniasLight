package com.fourforfour.eldanialight.characters;

import com.fourforfour.eldanialight.Game;
import com.fourforfour.eldanialight.areas.ShopArea;
import com.fourforfour.eldanialight.items.Item;
import com.fourforfour.eldanialight.items.ItemsOfEldinia;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class ShopNPC extends Character implements InteractActions {
    private String dialog;
    Scanner scanner = new Scanner(System.in);


    public ShopNPC(String name, int health, int strength, int defense, int bezos, int intel, int speed, String dialog) {
        super(name, health, strength, defense, bezos, intel, speed);
        this.dialog = dialog;
    }

    public void talk() {
        System.out.println(" Hello traveler what can I do for you?");
    }

    public void buy() {
        //implement
    }

    public void sell() {
        if (Game.character.items.size() == 0) {
            System.out.println("You have nothing to sell!");
        } else {
            Game.character.viewInventory();
            System.out.print("Item to sell -> ");
            String userSelection = scanner.nextLine();
            for (Item currentItem : Game.character.items) {
                if (currentItem.getName().equals(userSelection.toLowerCase(Locale.ROOT))) {
                    Game.character.items.remove(currentItem);
                    Game.character.setBezos(Game.character.getBezos() + currentItem.getItemWorth());
                    System.out.println("You now have " + Game.character.getBezos() + " Bezos\n");
                } else {
                    System.out.println("You don't have " + userSelection + " in your inventory\n");
                }
                break;
            }
        }
    }

    public void barter(HashMap<String, Item> items) {
        this.talk();
        System.out.println("BUY or SElL or LEAVE");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("BUY")) {
            System.out.println("Great, here are available Items: ");
            items.forEach((k, v) -> System.out.println(v.getName() + " " + v.getItemWorth() + " Bezos"));
            System.out.println("Please Select an Item form above");
            String selectedItem = scanner.nextLine();
            if (items.containsKey(selectedItem)) {
                if(Game.character.getBezos() >= items.get(selectedItem).getItemWorth()){
                    System.out.println("Excellent Choice traveler: +1 " + selectedItem);
                    Game.character.setBezos(Game.character.getBezos() - items.get(selectedItem).getItemWorth());
                    Game.character.addItem(items.get(selectedItem));
                }else{
                    System.out.println("You do not have enough to buy that item!");
                }

            }else{
                System.out.println("Sorry traveler, we don't have that here: " + selectedItem);
            }
            barter(items);
        } else if (input.equalsIgnoreCase("SELL")) {
//            System.out.println("We have no money for you!");
            sell();
        } else {
            System.out.println("Thank you for coming");
        }
    }
}//EOC
