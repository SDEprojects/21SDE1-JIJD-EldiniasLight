package com.fourforfour.eldanialight;

import com.fourforfour.eldanialight.characters.Player;
import com.fourforfour.eldanialight.controller.GameInterface;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Game implements Serializable, GameInterface {
    DataParser dataParser;
    private transient Scanner scanner;
    public Player player;
    String currentCity;
    boolean gameOver = false;
    public String currentLocationDescription;
    public List<String> locationCommands;
    public String previousCity;
    public boolean nameSaved = false;
    public boolean playerTypeSaved = false;
    boolean gameLoaded = false;
    String imagePath;

    // A LOT THINGS BEING DONE IN THIS CLASS JUST TO QUICKLY TEST - WILL SEPARATE SOON
    // NEED TO CREATE AN INPUT PARSER LATER
    // CREATE ANOTHER CLASS TO SEPARATE DISPLAY STUFF
    Game() {
        dataParser = new DataParser();
        currentCity = "home";
        setCurrentLocationDescription(dataParser.getLocationDescription(currentCity));
        scanner = new Scanner(System.in);
        setLocationCommands(dataParser.getLocationCommands(currentCity));
    }

    @Override
    public String submitPlayerString(String input) {
        String result = "";
        String command = input.toLowerCase(Locale.ROOT);

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
        String result = "";
        gameLoaded = false;
        HashMap<String, Object> gameObjects = new HashMap<>();
        gameObjects.put("player", player);
        gameObjects.put("currentCity", currentCity);
        gameObjects.put("previousCity", previousCity);
        gameObjects.put("imagePath", imagePath);

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

    private void processShopCommand() {
        System.out.println("Hello Traveler, what can I do for you?");
        System.out.println("You can BUY or SELL or LEAVE");
        String choice = scanner.nextLine();

        while (!choice.equals("leave")) {
            if (choice.equals("buy"))
                buyItems();
            if (choice.equals("sell"))
                sellItems();
            else
                System.out.println("You did not enter a correct command.");
        }
    }

    private void sellItems() {
    }

    private void buyItems() {
        System.out.println("Available item(s):" + getLocationItemsList());
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

    public void processLeaveCommand() {
        setCurrentCity(previousCity);
        setLocationCommands(dataParser.getLocationCommands(currentCity));
        setCurrentLocationDescription(dataParser.getLocationDescription(currentCity));
        System.out.println("You have entered: " + currentCity);
    }

    public List<String> getNeighbors() {
        return dataParser.getLocationNeighbors(currentCity);
    }

    public List<String> getLocationCommands() {
        return locationCommands;
    }

    public void setLocationCommands(List<String> locationCommands) {
        this.locationCommands = locationCommands;
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

    public void setCurrentLocationDescription(String currentLocationDescription) {
        this.currentLocationDescription = currentLocationDescription;
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

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String getDescriptionText() {
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
        return result;
    }

    @Override
    public String getLocationImagePath() {
        return getImagePath();
    }
}