package com.fourforfour.eldanialight;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fourforfour.eldanialight.characters.Player;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

class DataParser{
    // STATIC FIELDS
    private static final String CLASS_NODE = "classes";
    private static final String NPC_NODE = "npcs";
    private static final String ENEMIES_NODE = "enemies";
    private static final String ATTRIBUTES_NODE = "attributes";
    private static final String STATS_NODE = "stats";
    private static final String TYPE_NODE = "type";
    private static final String WEAR_ITEM_NODE = "wear item";
    private static final String WEAPONS_NODE = "weapons";
    private static final String ARMOR_NODE = "armor";
    private static final String CONSUMABLES_NODE = "consumables";
    private static final String UTILITY_NODE = "utility";
    private static final String REWARDS_NODE = "rewards";
    private static final String SHOP_INVENTORY_NODE = "shop inventory";
    private static final String ARMORY_LIST_NODE = "armorylist";
    private static final String MAGIC_LIST_NODE = "magiclist";
    private static final String LOCATIONS_NODE = "locations";
    private static final String DESCRIPTION_NODE = "description";
    private static final String NEIGHBOR_NODE = "neighbors";
    private static final String COMMANDS_NODE = "commands";
    private static final String SHOP_NPC_NODE = "npc";
    private static final String IMAGE_PATH_NODE = "imagepath";
    private static final String ITEM_VALUE_NODE = "value";



    // FIELDS
    private static ObjectMapper mapper;
    private static JsonNode gameData;

    // CONSTRUCTOR
    DataParser() {
        try {
            // grabs the gameData file
            InputStream jsonFile = getClass().getResourceAsStream("/data.json");
            // create a mapper to read through the game data
            mapper = new ObjectMapper();
            // create the JSON starting point
            gameData = mapper.readTree(jsonFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // HELPER METHODS
    private List<String> getArrayAsList(JsonNode node, String childNode) {
        List<String> result = new ArrayList<>();
        node.path(childNode).forEach(val -> result.add(val.asText()));
        return result;
    }

    private List<String> getKeysAsList(JsonNode node, String childNode) {
        List<String> result = new ArrayList<>();
        node.path(childNode).fieldNames().forEachRemaining(result::add);
        return result;
    }

    // Currently only being used to grab all the NPCs from "Quest" and "Shop"
    private List<String> getNestedKeysAsList(JsonNode node, String childNode) {
        List<String> result = new ArrayList<>();
        node.path(childNode).forEach(value -> value.fieldNames().forEachRemaining(result::add));
        return result;
    }

    // METHODS
    /**
     * PLAYER CLASSES
     *
     *  getPlayerClasses()  -> returns a List of available classes
     *  isPlayerClass()     -> checks to see if a class is available in the gameData
     */
    public List<String> getPlayerClasses() {
        return getKeysAsList(gameData, CLASS_NODE);
    }

    public boolean isPlayerClass(String playerClass) {
        return getPlayerClasses().contains(playerClass);
    }

    /**
     * NPCS
     *
     *  getNPCs()   -> returns a List of available NPCs
     *  isNPC()     -> checks to see if an NPC is available in the gameData
     */
    public List<String> getNPCs() {
        return getNestedKeysAsList(gameData, NPC_NODE);
    }

    public boolean isNPC(String npc) {
        return getNPCs().contains(npc);
    }

    /**
     * ENEMIES
     *
     *  getEnemies()    -> returns a List of available NPCs
     *  isEnemy()       -> checks to see if an NPC is available in the gameData
     *  getEnemy()      -> grabs the JsonNode of the specified enemy
     */
    public List<String> getEnemies() {
        return getKeysAsList(gameData, ENEMIES_NODE);
    }

    public boolean isEnemy(String enemy) {
        return getEnemies().contains(enemy);
    }

    public JsonNode getEnemy(String enemy) {
        if (isEnemy(enemy))
            return gameData.path(ENEMIES_NODE).path(enemy);
        else
            throw new IllegalArgumentException("Please input a valid enemy");
    }

    /**
     * ITEMS
     *
     *  getItemStats()  -> returns a List of available Item Stats
     *  isItemStat()    -> checks to see if an Item Stat is available in the gameData
     *  getItemTypes()  -> returns a List of available Item Types
     *  isItemType()    -> checks to see if an Item Type is available in the gameData
     */
    public List<String> getItemStats() {
        return getArrayAsList(gameData.path(ATTRIBUTES_NODE), STATS_NODE);
    }

    public boolean isItemStat(String itemStat) {
        return getItemStats().contains(itemStat);
    }

    public List<String> getItemTypes() {
        return getArrayAsList(gameData.path(ATTRIBUTES_NODE), TYPE_NODE);
    }

    public boolean isItemType(String itemTypes) {
        return getItemTypes().contains(itemTypes);
    }

    /**
     * WEAPONS
     *
     *  getWeapons()    -> returns a List of available Weapons
     *  isWeapon()      -> checks to see if a Weapon is available in the gameData
     *  getWeapon()     -> grabs the JsonNode of the specified Weapon
     */
    public List<String> getWeapons() {
        return getKeysAsList(gameData.path(WEAR_ITEM_NODE), WEAPONS_NODE);
    }

    public boolean isWeapon(String weapon) {
        return getWeapons().contains(weapon);
    }

    public JsonNode getWeapon(String weapon) {
        if (isWeapon(weapon))
            return gameData.path(WEAR_ITEM_NODE).path(WEAPONS_NODE).path(weapon);
        else
            throw new IllegalArgumentException("Please input a valid weapon");
    }
    public int getWeaponValue(String weapon) {
        if (isWeapon(weapon))
            return gameData.path(WEAR_ITEM_NODE).path(WEAPONS_NODE).path(weapon).path(ITEM_VALUE_NODE).asInt();
        else
            throw new IllegalArgumentException("Please input a valid weapon");
    }

    /**
     * ARMOR
     *
     *  getArmor()  -> returns a List of available Armor
     *  isArmor()   -> checks to see if a Armor is available in the gameData
     *  getArmor()  -> grabs the JsonNode of the specified Armor
     */
    public List<String> getArmor() {
        return getKeysAsList(gameData.path(WEAR_ITEM_NODE), ARMOR_NODE);
    }

    public boolean isArmor(String armor) {
        return getArmor().contains(armor);
    }

    public JsonNode getArmor(String armor) {
        if (isArmor(armor))
            return gameData.path(WEAR_ITEM_NODE).path(ARMOR_NODE).path(armor);
        else
            throw new IllegalArgumentException("Please input a valid armor");
    }

    public int getArmorValue(String armor) {
        if (isArmor(armor))
            return gameData.path(WEAR_ITEM_NODE).path(ARMOR_NODE).path(armor).path(ITEM_VALUE_NODE).asInt();
        else
            throw new IllegalArgumentException("Please input a valid armor");
    }

    /**
     * CONSUMABLES
     *
     *  getConsumables()    -> returns a List of available Consumables
     *  isConsumable()      -> checks to see if a Armor is available in the gameData
     *  getConsumable()     -> grabs the JsonNode of the specified Armor
     */
    public List<String> getConsumables() {
        return getKeysAsList(gameData, CONSUMABLES_NODE);
    }

    public boolean isConsumable(String consumable) {
        return getConsumables().contains(consumable);
    }

    public JsonNode getConsumable(String consumable) {
        if (isConsumable(consumable))
            return gameData.path(CONSUMABLES_NODE).path(consumable);
        else
            throw new IllegalArgumentException("Please input a valid enemy");
    }
    public int getConsumableValue(String consumable) {
        if (isConsumable(consumable))
            return gameData.path(CONSUMABLES_NODE).path(consumable).path(ITEM_VALUE_NODE).asInt();
        else
            throw new IllegalArgumentException("Please input a valid enemy");
    }
    /**
     * UTILITY ITEMS
     *
     *  getUtilityItems()    -> returns a List of available Utility Items
     *  isUtilityItem()      -> checks to see if a Utility Item is available in the gameData
     */
    public List<String> getUtilityItems() {
        return getArrayAsList(gameData, UTILITY_NODE);
    }

    public boolean isUtilityItem(String utilityItem) {
        return getUtilityItems().contains(utilityItem);
    }

    /**
     * REWARD ITEMS
     *
     *  getRewardItems()    -> returns a List of available Reward Item
     *  isRewardItem()      -> checks to see if a Reward Item is available in the gameData
     */
    public List<String> getRewardItems() {
        return getArrayAsList(gameData, REWARDS_NODE);
    }

    public boolean isRewardItem(String rewardItem) {
        return getRewardItems().contains(rewardItem);
    }

    /**
     * LOCATIONS
     *
     *  getLocations()              -> returns a List of available Locations
     *  isLocation()                -> checks to see if a Location is available in the gameData
     *  getLocationType()           -> returns the Location's Type
     *  getLocationDescription()    -> returns the Location's Description
     *  getLocationNeighbors()      -> returns the Location's Neighbors
     *  getLocationCommands()       -> returns the Location's Commands
     *  getLocationNPC()            -> returns the Location's NPCs
     */
    public List<String> getLocations() {
        return getKeysAsList(gameData, LOCATIONS_NODE);
    }

    public boolean isLocation(String location) {
        return getLocations().contains(location);
    }

    public String getLocationType(String location) {
        if (isLocation(location))
            return gameData.path(LOCATIONS_NODE).path(location).path(TYPE_NODE).asText();
        else
            throw new IllegalArgumentException("Please input a valid location");
    }

    public String getLocationDescription(String location) {
        if (isLocation(location))
            return gameData.path(LOCATIONS_NODE).path(location).path(DESCRIPTION_NODE).asText();
        else
            throw new IllegalArgumentException("Please input a valid location");
    }

    public String getLocationImage(String location) {
        if (isLocation(location))
            return gameData.path(LOCATIONS_NODE).path(location).path(IMAGE_PATH_NODE).asText();
        else
            throw new IllegalArgumentException("Please input a valid location");
    }

    public List<String> getLocationNeighbors(String location) {
        if (isLocation(location))
            return getArrayAsList(gameData.path(LOCATIONS_NODE).path(location), NEIGHBOR_NODE);
        else
            throw new IllegalArgumentException("Please input a valid location");
    }

    public List<String> getLocationCommands(String location) {
        if (isLocation(location))
            return getArrayAsList(gameData.path(LOCATIONS_NODE).path(location), COMMANDS_NODE);
        else
            throw new IllegalArgumentException("Please input a valid location");
    }

    public JsonNode getLocationNPC(String location) {
        if (isLocation(location))
            return gameData.path(LOCATIONS_NODE).path(location).path(SHOP_NPC_NODE);
        else
            throw new IllegalArgumentException("Please input a valid location");
    }

    /**
     * ARMORY LIST
     *
     *  getArmoryList()    -> returns a List of the Armor Shop's available items
     */
    public List<String> getArmoryList() {
        return getArrayAsList(gameData.path(SHOP_INVENTORY_NODE), ARMORY_LIST_NODE);
    }

    /**
     * MAGIC LIST
     *
     *  getMagicList()    -> returns a List of the Magic Shop's available items
     */
    public List<String> getMagicList() {
        return getArrayAsList(gameData.path(SHOP_INVENTORY_NODE), MAGIC_LIST_NODE);
    }

    /**
     * INSTANCE CREATORS
     *
     *  createPlayerClass() -> returns an instance of a Player Class
     *  createEnemy()       -> returns an instance of an Enemy Class
     */
    public Player createPlayerClass(String classChoice) {
        // allows you to create an instance of a character straight from your JSON game data
        if (!isPlayerClass(classChoice))
            throw new IllegalArgumentException("Requested character class does not exist in your game data");

        Player result = null;
        try {
            JsonNode characterInformation = gameData.path(CLASS_NODE).path(classChoice);
            // allows you to pass in a JsonNode and it returns a Java Object of your choosing (as long as it has the proper fields)
            result = mapper.treeToValue(characterInformation, Player.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}// EOC