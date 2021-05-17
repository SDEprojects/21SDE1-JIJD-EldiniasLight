package com.fourforfour.eldanialight;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fourforfour.eldanialight.characters.Player;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

class DataParser{
    /*
     * STATIC FIELDS
     *
     *  These fields specify which JsonNode to target
     */
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
    private static final String MAP_PATH_NODE = "map";
    private static final String ITEM_VALUE_NODE = "value";
    private static final String ATTACK_STAT_NODE = "attack";
    private static final String DEFENSE_STAT_NODE = "defense";
    private static final String HEALTH_STAT_NODE = "health";

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
    /**
     * getArrayAsList()
     *  returns a List<String> of array at the specified node
     */
    private List<String> getArrayAsList(JsonNode node, String childNode) {
        List<String> result = new ArrayList<>();
        node.path(childNode).forEach(val -> result.add(val.asText()));
        return result;
    }

    /**
     * getKeysAsList()
     *  returns a List<String> of all the keys inside of the specified node
     */
    private List<String> getKeysAsList(JsonNode node, String childNode) {
        List<String> result = new ArrayList<>();
        node.path(childNode).fieldNames().forEachRemaining(result::add);
        return result;
    }

    /**
     * getNestedKeysAsList()
     *  returns a List<String> of all the keys nested inside of the specified node
     *
     *  Currently only being used to grab all the NPCs from "Quest" and "Shop"
     */
    private List<String> getNestedKeysAsList(JsonNode node, String childNode) {
        List<String> result = new ArrayList<>();
        node.path(childNode).forEach(value -> value.fieldNames().forEachRemaining(result::add));
        return result;
    }

    // METHODS
    /**
     * getPlayerClasses()
     *  returns a List of available classes
     */
    public List<String> getPlayerClasses() {
        return getKeysAsList(gameData, CLASS_NODE);
    }

    /**
     * isPlayerClass()
     *  checks to see if a class is available in the gameData
     */
    public boolean isPlayerClass(String playerClass) {
        return getPlayerClasses().contains(playerClass);
    }

    /**
     * getNPCs()
     *  returns a List of available NPCs
     */
    public List<String> getNPCs() {
        return getNestedKeysAsList(gameData, NPC_NODE);
    }

    /**
     * isNPC()
     *  checks to see if an NPC is available in the gameData
     */
    public boolean isNPC(String npc) {
        return getNPCs().contains(npc);
    }

    /**
     * getEnemies()
     *  returns a List of available NPCs
     */
    public List<String> getEnemies() {
        return getKeysAsList(gameData, ENEMIES_NODE);
    }

    /**
     * isEnemy()
     *  checks to see if an NPC is available in the gameData
     */
    public boolean isEnemy(String enemy) {
        return getEnemies().contains(enemy);
    }

    /**
     * getEnemy()
     *  grabs the JsonNode of the specified enemy
     */
    public JsonNode getEnemy(String enemy) {
        if (isEnemy(enemy))
            return gameData.path(ENEMIES_NODE).path(enemy);
        else
            throw new IllegalArgumentException("Please input a valid enemy");
    }

    /**
     * getItemStats()
     *  returns a List of available Item Stats
     */
    public List<String> getItemStats() {
        return getArrayAsList(gameData.path(ATTRIBUTES_NODE), STATS_NODE);
    }

    /**
     * isItemStat()
     *  checks to see if an Item Stat is available in the gameData
     */
    public boolean isItemStat(String itemStat) {
        return getItemStats().contains(itemStat);
    }

    /**
     * getItemTypes()
     *  returns a List of available Item Types
     */
    public List<String> getItemTypes() {
        return getArrayAsList(gameData.path(ATTRIBUTES_NODE), TYPE_NODE);
    }

    /**
     * isItemType()
     *  checks to see if an Item Type is available in the gameData
     */
    public boolean isItemType(String itemTypes) {
        return getItemTypes().contains(itemTypes);
    }

    /**
     * getWeapons()
     *  returns a List of available Weapons
     */
    public List<String> getWeapons() {
        return getKeysAsList(gameData.path(WEAR_ITEM_NODE), WEAPONS_NODE);
    }

    /**
     * isWeapon()
     *  checks to see if a Weapon is available in the gameData
     */
    public boolean isWeapon(String weapon) {
        return getWeapons().contains(weapon);
    }

    /**
     * getWeapon()
     *  grabs the JsonNode of the specified Weapon
     */
    public JsonNode getWeapon(String weapon) {
        if (isWeapon(weapon))
            return gameData.path(WEAR_ITEM_NODE).path(WEAPONS_NODE).path(weapon);
        else
            throw new IllegalArgumentException("Please input a valid weapon");
    }

    /**
     * getWeaponValue()
     *  returns the value of the weapon
     */
    public int getWeaponValue(String weapon) {
        if (isWeapon(weapon))
            return gameData.path(WEAR_ITEM_NODE).path(WEAPONS_NODE).path(weapon).path(ITEM_VALUE_NODE).asInt();
        else
            throw new IllegalArgumentException("Please input a valid weapon");
    }

    public int getWeaponAttackStats(String weapon) {
        if (isWeapon(weapon))
            return gameData.path(WEAR_ITEM_NODE).path(WEAPONS_NODE).path(weapon).path(ATTACK_STAT_NODE).asInt();
        else
            throw new IllegalArgumentException("Please input a valid weapon");
    }

    public int getWeaponHealthStats(String weapon) {
        if (isWeapon(weapon))
            return gameData.path(WEAR_ITEM_NODE).path(WEAPONS_NODE).path(weapon).path(HEALTH_STAT_NODE).asInt();
        else
            throw new IllegalArgumentException("Please input a valid weapon");
    }
    public int getWeaponDefenseStats(String weapon) {
        if (isWeapon(weapon))
            return gameData.path(WEAR_ITEM_NODE).path(WEAPONS_NODE).path(weapon).path(DEFENSE_STAT_NODE).asInt();
        else
            throw new IllegalArgumentException("Please input a valid weapon");
    }

    /**
     * getArmor()
     *  returns a List of available Armor
     */
    public List<String> getArmor() {
        return getKeysAsList(gameData.path(WEAR_ITEM_NODE), ARMOR_NODE);
    }

    /**
     * isArmor()
     *  checks to see if a Armor is available in the gameData
     */
    public boolean isArmor(String armor) {
        return getArmor().contains(armor);
    }

    /**
     * getArmor()
     *  grabs the JsonNode of the specified Armor
     */
    public JsonNode getArmor(String armor) {
        if (isArmor(armor))
            return gameData.path(WEAR_ITEM_NODE).path(ARMOR_NODE).path(armor);
        else
            throw new IllegalArgumentException("Please input a valid armor");
    }

    /**
     * getArmorValue()
     *  returns the value of the armor
     */
    public int getArmorValue(String armor) {
        if (isArmor(armor))
            return gameData.path(WEAR_ITEM_NODE).path(ARMOR_NODE).path(armor).path(ITEM_VALUE_NODE).asInt();
        else
            throw new IllegalArgumentException("Please input a valid armor");
    }

    public int getArmorHealthStat(String armor) {
        if (isArmor(armor))
            return gameData.path(WEAR_ITEM_NODE).path(ARMOR_NODE).path(armor).path(HEALTH_STAT_NODE).asInt();
        else
            throw new IllegalArgumentException("Please input a valid armor");
    }
    public int getArmorAttackStat(String armor) {
        if (isArmor(armor))
            return gameData.path(WEAR_ITEM_NODE).path(ARMOR_NODE).path(armor).path(ATTACK_STAT_NODE).asInt();
        else
            throw new IllegalArgumentException("Please input a valid armor");
    }
    public int getArmorDefenseStat(String armor) {
        if (isArmor(armor))
            return gameData.path(WEAR_ITEM_NODE).path(ARMOR_NODE).path(armor).path(DEFENSE_STAT_NODE).asInt();
        else
            throw new IllegalArgumentException("Please input a valid armor");
    }

    /**
     * getConsumables()
     *  returns a List of available Consumables
     */
    public List<String> getConsumables() {
        return getKeysAsList(gameData, CONSUMABLES_NODE);
    }

    /**
     * isConsumable()
     *  checks to see if a Armor is available in the gameData
     */
    public boolean isConsumable(String consumable) {
        return getConsumables().contains(consumable);
    }

    /**
     * getConsumable()
     *  grabs the JsonNode of the specified Armor
     */
    public JsonNode getConsumable(String consumable) {
        if (isConsumable(consumable))
            return gameData.path(CONSUMABLES_NODE).path(consumable);
        else
            throw new IllegalArgumentException("Please input a valid enemy");
    }

    /**
     * getConsumableValue()
     *  returns the value of the consumable
     */
    public int getConsumableValue(String consumable) {
        if (isConsumable(consumable))
            return gameData.path(CONSUMABLES_NODE).path(consumable).path(ITEM_VALUE_NODE).asInt();
        else
            throw new IllegalArgumentException("Please input a valid enemy");
    }

    /**
     * getUtilityItems()
     *  returns a List of available Utility Items
     */
    public List<String> getUtilityItems() {
        return getArrayAsList(gameData, UTILITY_NODE);
    }

    /**
     * isUtilityItem()
     *  checks to see if a Utility Item is available in the gameData
     */
    public boolean isUtilityItem(String utilityItem) {
        return getUtilityItems().contains(utilityItem);
    }

    /**
     * getRewardItems()
     *  returns a List of available Reward Item
     */
    public List<String> getRewardItems() {
        return getArrayAsList(gameData, REWARDS_NODE);
    }

    /**
     * isRewardItem()
     *  checks to see if a Reward Item is available in the gameData
     */
    public boolean isRewardItem(String rewardItem) {
        return getRewardItems().contains(rewardItem);
    }

    /**
     * getLocations()
     *  returns a List of available Locations
     */
    public List<String> getLocations() {
        return getKeysAsList(gameData, LOCATIONS_NODE);
    }

    /**
     * isLocation()
     *  checks to see if a Location is available in the gameData
     */
    public boolean isLocation(String location) {
        return getLocations().contains(location);
    }

    /**
     * getLocationType()
     *  returns the Location's Type
     */
    public String getLocationType(String location) {
        if (isLocation(location))
            return gameData.path(LOCATIONS_NODE).path(location).path(TYPE_NODE).asText();
        else
            throw new IllegalArgumentException("Please input a valid location");
    }

    /**
     * getLocationDescription()
     *  returns the Location's Description
     */
    public String getLocationDescription(String location) {
        if (isLocation(location))
            return gameData.path(LOCATIONS_NODE).path(location).path(DESCRIPTION_NODE).asText();
        else
            throw new IllegalArgumentException("Please input a valid location");
    }

    /**
     * getLocationImage()
     *  returns a String of the location's image
     */
    public String getLocationImage(String location) {
        if (isLocation(location))
            return gameData.path(LOCATIONS_NODE).path(location).path(IMAGE_PATH_NODE).asText();
        else
            throw new IllegalArgumentException("Please input a valid location");
    }

    /**
     * getLocationMap()
     *  returns a String of the location's map
     */
    public String getLocationMap(String location) {
        if (isLocation(location))
            return gameData.path(LOCATIONS_NODE).path(location).path(MAP_PATH_NODE).asText();
        else
            throw new IllegalArgumentException("Please input a valid location");
    }

    /**
     * getLocationNeighbors()
     *  returns the Location's Neighbors
     */
    public List<String> getLocationNeighbors(String location) {
        if (isLocation(location))
            return getArrayAsList(gameData.path(LOCATIONS_NODE).path(location), NEIGHBOR_NODE);
        else
            throw new IllegalArgumentException("Please input a valid location");
    }

    /**
     * getLocationCommands()
     *  returns the Location's Commands
     */
    public List<String> getLocationCommands(String location) {
        if (isLocation(location))
            return getArrayAsList(gameData.path(LOCATIONS_NODE).path(location), COMMANDS_NODE);
        else
            throw new IllegalArgumentException("Please input a valid location");
    }

    /**
     * getLocationNPC()
     *  returns the Location's NPCs
     */
    public JsonNode getLocationNPC(String location) {
        if (isLocation(location))
            return gameData.path(LOCATIONS_NODE).path(location).path(SHOP_NPC_NODE);
        else
            throw new IllegalArgumentException("Please input a valid location");
    }

    /**
     * getArmoryList()
     *  returns a List of the Armor Shop's available items
     */
    public List<String> getArmoryList() {
        return getArrayAsList(gameData.path(SHOP_INVENTORY_NODE), ARMORY_LIST_NODE);
    }

    /**
     * getMagicList()
     *  returns a List of the Magic Shop's available items
     */
    public List<String> getMagicList() {
        return getArrayAsList(gameData.path(SHOP_INVENTORY_NODE), MAGIC_LIST_NODE);
    }

    /**
     * createPlayerClass()
     *  returns an instance of a Player Class
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