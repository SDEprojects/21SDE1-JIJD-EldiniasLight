package com.fourforfour.eldanialight;

import com.fourforfour.eldanialight.battle.BattleSequence;
import com.fourforfour.eldanialight.characters.BattleActions;
import com.fourforfour.eldanialight.characters.Player;
import com.fourforfour.eldanialight.controller.GameInterface;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Game implements Serializable, GameInterface {
    static DataParser dataParser;
    private transient Scanner scanner;
    public static Player player;
    public static String currentCity;
    public static boolean gameOver = false;
    public static String currentLocationDescription;
    public static List<String> locationCommands;
    public static String previousCity;
    public boolean nameSaved = false;
    public boolean playerTypeSaved = false;
    boolean gameLoaded = false;
    public static String imagePath;


    /**
     * This initializes the game with a dataparser, sets the home location, description, and image
     */
    Game() {
        dataParser = new DataParser();
        currentCity = "home";
        scanner = new Scanner(System.in);
        setCurrentLocationDescription("The once peaceful land of Eldina has recently been taken over by the evil warlock Gandolf the Black who has cast a darkness upon the land. You are the one chosen to defeat Gandolf and his Army to restore Eldina back to its peaceful ways.");
        setLocationCommands(dataParser.getLocationCommands(currentCity));
        setImagePath("data/images/Opening screen.jpg");


    }

    /**
     * This method is how we interact with the GUI and send data back
     * @param input is the users input grabbed from the UI
     * @return what is return is a string back to the UI, and the getters will replace information within the gui automatically
     */
    @Override
    public String submitPlayerString(String input) {
        String result;
        String command = input.toLowerCase(Locale.ROOT).trim();

        if(gameOver){
            return "gameOver";
        }

        if(command.equals("load")){
            return processloadCommand();
        }

        if(command.equals("save")){
            return processSaveCommand();
        }



        if (!playerTypeSaved) {
            if (dataParser.isPlayerClass(command)) {
                player = dataParser.createPlayerClass(command);
                playerTypeSaved = true;
                return "What is your name?";
            } else{
                return "That is not a correct input.\n What type of Player are you?\n MAGE or KNIGHT or ARCHER";
            }
        }

        if (!nameSaved) {
            if(command.isEmpty()){
                return "please enter your name";
            }
            player.setName(command);
            nameSaved = true;
            changeCity(currentCity);
            return "Welcome, "  + input + "! Your quest has began young warrior. Go to the town, explore all the shops, and upgrade your equipment and set out to go gain experience in hopes of defeating Tyronious the Black";
        }

        if(currentCity.equalsIgnoreCase("armory")){
            if(dataParser.isArmor(command)) {
                if (player.getBezos() >= dataParser.getArmorValue(command)) {
                    player.addItem(command, dataParser.getArmorValue(command));
                    player.setBezos(player.getBezos() - dataParser.getArmorValue(command));
                    return " You have added a " + command + " to your inventory" +
                            "\nYou currently only have " + player.getBezos() + " bezos";
                }else {
                    return "You dont have enough bezos." +
                            "\nYou currently only have " + player.getBezos() + " bezos";
                }
            }
            if(dataParser.isWeapon(command)){
            if (player.getBezos() >= dataParser.getWeaponValue(command)){
                    player.addItem(command, dataParser.getWeaponValue(command));
                    player.setBezos(player.getBezos() - dataParser.getWeaponValue(command));
                    return " You have added a " + command + " to your inventory"+
                            "\nYou currently only have " + player.getBezos() + " bezos";
                }else {
                    return "You dont have enough bezos." +
                            "\nYou currently only have " + player.getBezos() + " bezos";
                }
            }
        }

        if(currentCity.equalsIgnoreCase("magic")){
            if(dataParser.isConsumable(command)){
                if (player.getBezos() >= dataParser.getConsumableValue(command)){
                    player.addItem(command, dataParser.getConsumableValue(command));
                    player.setBezos(player.getBezos() - dataParser.getConsumableValue(command));
                    return " You have added a " + command + " to your inventory" +
                            "\nYou currently only have " + player.getBezos() + " bezos";
                }else {
                    return "You dont have enough bezos." +
                            "\nYou currently only have " + player.getBezos() + " bezos";
                }
            }
        }

        if(currentCity.equalsIgnoreCase("pawnshop")){
            if(dataParser.isConsumable(command) && player.items.containsKey(command)){
                    player.items.remove(command);
                    player.setBezos(player.getBezos() + dataParser.getConsumableValue(command));
                    return " You have SOLD a " + command + " to the pawnshop" + " for " + dataParser.getConsumableValue(command) +" bezos" +
                            "\nYou currently have " + player.getBezos() + " bezos";
                }
            if(dataParser.isArmor(command) && player.items.containsKey(command)){
                player.items.remove(command);
                player.setBezos(player.getBezos() + dataParser.getArmorValue(command));
                return " You have SOLD a " + command + " to the pawnshop" + " for " + dataParser.getArmorValue(command) +" bezos" +
                        "\nYou currently have " + player.getBezos() + " bezos";
            }
            if(dataParser.isWeapon(command) && player.items.containsKey(command)){
                player.items.remove(command);
                player.setBezos(player.getBezos() + dataParser.getWeaponValue(command));
                return " You have SOLD a " + command + " to the pawnshop" + " for " + dataParser.getWeaponValue(command) +" bezos" +
                        "\nYou currently have " + player.getBezos() + " bezos";
            }
        }


        if(dataParser.getLocationType(currentCity).equals("battle")){
            BattleSequence batlleSequence = new BattleSequence(currentCity);

            if(command.equals("attack")){
                return batlleSequence.attack();
            }

            if(command.equals("run")){
                return batlleSequence.run();
            }
        }

        if(command.equals("map")){
            setImagePath(dataParser.getLocationMap(currentCity));
            return "Here is Map of the location.";
        }

        if(command.equals("quit")){
            return "gameOver";
        }

        if(command.equals("view stats")){
            return player.viewStats();
        }

        if(command.equals("view items")){
            return player.viewInventory();
        }

        if(command.contains("unequip")){
            return removeEquippedItems(command);
        } else if(command.contains("equip")){
          return equipItems(command);
        }


        if (command.isEmpty()) {
            result = "I don't understand \"" + input + "\"";
        } else if (getNeighbors().contains(command)) {
            result = processVentureCommand(command);
        }else{
            result = "You have entered a wrong input";
        }
        return result;
    }

    private String removeEquippedItems(String command) {
        String[] inputArr = command.split(" ");
        String item = inputArr[1];
        if(dataParser.isWeapon(item) && player.equibbedItems().contains(item)){
            player.setHealth(player.getHealth() - dataParser.getWeaponHealthStats(item));
            player.setStrength(player.getStrength() - dataParser.getWeaponAttackStats(item));
            player.setDefense(player.getDefense() - dataParser.getWeaponDefenseStats(item));
            player.equippedItems.remove(item);

            return "You have unequipped your " + item;

        }else if(dataParser.isArmor(item) && player.equibbedItems().contains(item)) {
            player.setHealth(player.getHealth() - dataParser.getArmorHealthStat(item));
            player.setStrength(player.getStrength() - dataParser.getArmorAttackStat(item));
            player.setDefense(player.getDefense() - dataParser.getArmorDefenseStat(item));
            player.equippedItems.remove(item);

            return "You have unequipped your " + item;

        }else{
            return "That item is unequippable";
        }
    }

    /**
     *
     * @param command users input
     * @return a string whether item was equipped
     * handle players equipped items and unequip, while also adding item buffs to user stats
     */
    private String equipItems(String command) {
        String[] inputArr = command.split(" ");
        String item = inputArr[1];
        if(dataParser.isWeapon(item) && player.items.containsKey(item) && !player.equibbedItems().contains(item)){
            player.addEquippedItem(item);
            player.setHealth(player.getHealth() + dataParser.getWeaponHealthStats(item));
            player.setStrength(player.getStrength() + dataParser.getWeaponAttackStats(item));
            player.setDefense(player.getDefense() + dataParser.getWeaponDefenseStats(item));
            return "You have equipped your " + item;

        }else if(dataParser.isArmor(item) && player.items.containsKey(item) && !player.equibbedItems().contains(item)) {
            player.addEquippedItem(item);
            player.setHealth(player.getHealth() + dataParser.getArmorHealthStat(item));
            player.setStrength(player.getStrength() + dataParser.getArmorAttackStat(item));
            player.setDefense(player.getDefense() + dataParser.getArmorDefenseStat(item));
            return "You have equipped your " + item;

        }else{
            return "That item is unequippable";
        }
    }

    /**
     * This method uses Java Serializable to save the game state
     * using a hashmap, different parts of the Game are saved and then Serialized
     * @return a string whether or not game was successfully save
     */
    private String processSaveCommand() {
        String result;
        gameLoaded = false;
        HashMap<String, Object> gameObjects = new HashMap<>();
        gameObjects.put("player", player);
        gameObjects.put("currentCity", currentCity);
        gameObjects.put("previousCity", previousCity);
        gameObjects.put("nameSaved", nameSaved);
        gameObjects.put("imagePath", imagePath);
        gameObjects.put("playerTypeSaved", playerTypeSaved);

        try {
            FileOutputStream fos = new FileOutputStream("data/saveGameData.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gameObjects);
            oos.flush();
            oos.close();
            fos.close();

            result = "game saved";
        } catch (FileNotFoundException e) {
            result = "Failed to load the game files:";
        } catch (IOException e) {
            result = "Failed to load the game files:";
            e.printStackTrace();
        }

        return result;
    }

    /**
     * This method deserializes the game state that was saved
     * and reinitializes the game with that data
     * @return a string whether the data was successfully load or not
     */
    private String processloadCommand(){
        String result;

        if (!gameLoaded) {

            try {
                FileInputStream fis = new FileInputStream("data/saveGameData.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);

                HashMap<String, Object> data = (HashMap<String, Object>) ois.readObject();
                fis.close();

                currentCity = (String) data.get("currentCity");
                player = (Player) data.get("player");
                setCurrentLocationDescription(dataParser.getLocationDescription(currentCity));
                setLocationCommands(dataParser.getLocationCommands(currentCity));
                previousCity = (String) data.get("previousCity");
                imagePath = (String) data.get("imagePath");
                setImagePath(imagePath);
                playerTypeSaved = (boolean) data.get("playerTypeSaved");
                nameSaved = (boolean) data.get("nameSaved");
                gameLoaded = true;
                result = "game loaded from last checkpoint";
            } catch (Exception e) {
                result = "game could not be loaded";
            }
        } else {
            result = "You have already loaded the game. You cannot do it again.";
        }
        return result;
    }

    /**
     * Helped method that allows us to change the current city
     * while also updating the description and location image
     * @param location is the next city that the user will be moved to
     */
    public static void changeCity(String location){
        currentCity = location;
        setCurrentLocationDescription(dataParser.getLocationDescription(location));
        setImagePath(dataParser.getLocationImage(location));
        setLocationCommands(dataParser.getLocationCommands(location));
    }
    public List<String> getLocationItemsList() {
        if (currentCity.equals("armory"))
            return dataParser.getArmoryList();
        else
            return dataParser.getMagicList();
    }

    /**
     * This method handles how our user can traverse through the different locations
     * @param command is the users input
     * @return a string whether the user was able to go to the new location or not
     */
    public String processVentureCommand(String command) {
        setPreviousCity(currentCity);
        setCurrentCity(command);
        setLocationCommands(dataParser.getLocationCommands(currentCity));
        setCurrentLocationDescription(dataParser.getLocationDescription(currentCity));
        setImagePath(dataParser.getLocationImage(currentCity));
        return "You have entered " + command;
    }


    public List<String> getNeighbors() {
        return dataParser.getLocationNeighbors(currentCity);
    }

    public List<String> getLocationCommands() {
        return locationCommands;
    }

    public static void setLocationCommands(List<String> locationCommands) {
        Game.locationCommands = locationCommands;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getCurrentLocationDescription() {
        return currentLocationDescription;
    }

    public static void setCurrentLocationDescription(String currentLocationDescription) {
        Game.currentLocationDescription = currentLocationDescription;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public String getPreviousCity() {
        return previousCity;
    }

    public void setPreviousCity(String previousCity) {
        this.previousCity = previousCity;
    }

    public String getImagePath() {
        return imagePath;
    }

    public static void setImagePath(String imagePath) {
        Game.imagePath = imagePath;
    }

    @Override
    public String getDescriptionText() {

        if(!nameSaved){
            return getCurrentLocationDescription();
        }

        String result = "Location: " + getCurrentLocationDescription() +
              "\n \nAllowed Commands "+ getLocationCommands() +
              "\n" + player.equibbedItems() +
              "\n \nPossible Destinations ->" + dataParser.getLocationNeighbors(currentCity) +
              "\nType destination name to move";
        if(currentCity.equalsIgnoreCase("armory")){
            result += "\nYou can type available items to BUY " +
                    dataParser.getArmoryList();
        }
        if(currentCity.equalsIgnoreCase("magic")){
            result += "\nYou can type available items to BUY" +
                    dataParser.getMagicList();
        }
        if(currentCity.equalsIgnoreCase("pawnshop")){
            result += "\nYou can type available items to SELL" +
                    player.itemsToSell();
        }
        if(dataParser.getLocationType(currentCity).equals("battle")){
            result += "\nYou have encountered an enemy\nIf you would like to fight -> type attack";
        }
        return result;
    }

    @Override
    public String getLocationImagePath() {
        return getImagePath();
    }
}