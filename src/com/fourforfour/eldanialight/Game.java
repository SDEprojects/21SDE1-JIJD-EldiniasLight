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


    // A LOT THINGS BEING DONE IN THIS CLASS JUST TO QUICKLY TEST - WILL SEPARATE SOON
    // NEED cTO CREATE AN INPUT PARSER LATER
    // CREATE ANOTHER CLASS TO SEPARATE DISPLAY STUFF
    Game() {
        dataParser = new DataParser();
        currentCity = "home";
        scanner = new Scanner(System.in);
        setCurrentLocationDescription("The once peaceful land of Eldina has recently been taken over by the evil warlock Tyroneious the Black who has cast a darkness upon the land. You are the one chosen to defeat Tyroneious and his Army to restore Eldina back to its peaceful ways.");
        setLocationCommands(dataParser.getLocationCommands(currentCity));
        setImagePath("data/images/Opening screen.jpg");


    }

    @Override
    public String submitPlayerString(String input) {
        String result;
        String command = input.toLowerCase(Locale.ROOT);

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
                return result = "What is your name?";
            }
            else
                return result = "That is not a correct input.\n What type of Player are you?\n MAGE or KNIGHT or ARCHER";
        }

        if (!nameSaved) {
            player.setName(command);
            nameSaved = true;
            changeCity(currentCity);
            return result = "Welcome, "  + input + "! Please go speak with the Warchief at the town hall.";
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

        if(command.equals("view stats")){
            return player.viewStats();
        }

        if(command.equals("view items")){
            return player.viewInventory();
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
            System.out.println(e.getMessage());
        } catch (IOException e) {
            result = "Failed to load the game files:";
            e.printStackTrace();
        }

        return result;
    }

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

    public String processVentureCommand(String command) {
        setPreviousCity(currentCity);
        setCurrentCity(command);
        setLocationCommands(dataParser.getLocationCommands(currentCity));
        setCurrentLocationDescription(dataParser.getLocationDescription(currentCity));
        setImagePath(dataParser.getLocationImage(currentCity));
        System.out.println(dataParser.getLocationImage(currentCity));
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