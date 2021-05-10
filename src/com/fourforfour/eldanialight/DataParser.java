package com.fourforfour.eldanialight;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fourforfour.eldanialight.characters.Player;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

class DataParser {
    // STATIC FIELDS
    private static final String CHARACTERS_NODE = "Characters";
    private static final String CLASS_NODE = "Classes";
    private static final String NPC_NODE = "NPCs";
    private static final String ENEMIES_NODE = "Enemies";
    private static final String ITEMS_NODE = "Items";
    private static final String ATTRIBUTES_NODE = "Attributes";
    private static final String STATS_NODE = "stats";
    private static final String TYPE_NODE = "type";
    private static final String WEAR_ITEM_NODE = "Wear Item";
    private static final String WEAPONS_NODE = "weapons";
    private static final String ARMOR_NODE = "armor";
    private static final String CONSUMABLES_NODE = "Consumables";
    private static final String UTILITY_NODE = "Utility";
    private static final String REWARDS_NODE = "Rewards";
    private static final String SHOP_INVENTORY_NODE = "Shop Inventory";
    private static final String ARMORY_LIST_NODE = "armoryList";
    private static final String MAGIC_LIST_NODE = "magicList";
    private static final String LOCATIONS_NODE = "Locations";

    // FIELDS
    private ObjectMapper mapper;
    private JsonNode gameData;

    // CONSTRUCTOR
    DataParser() {
        try {
            // grabs the game dataParser file
            InputStream gameData = getClass().getResourceAsStream("/gameData.json");
            // create a mapper to read through the game data
            mapper = new ObjectMapper();
            // create the JSON starting point
            this.gameData = mapper.readTree(gameData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // HELPER METHODS
    private List<String> getArrayAsList(JsonNode node, String childNode) {
        List<String> result = new ArrayList<>();
        node.path(childNode).forEach(stat -> result.add(stat.asText()));
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
    public List<String> getPlayerClasses() {
        return getKeysAsList(gameData.path(CHARACTERS_NODE), CLASS_NODE);
    }
    public boolean isPlayerClass(String playerClass) {
        return getPlayerClasses().contains(playerClass);
    }

    public List<String> getNPCs() {
        return getNestedKeysAsList(gameData.path(CHARACTERS_NODE), NPC_NODE);
    }
    public boolean isNPC(String npc) {
        return getNPCs().contains(npc);
    }

    public List<String> getEnemies() {
        return getKeysAsList(gameData.path(CHARACTERS_NODE), ENEMIES_NODE);
    }
    public boolean isEnemy(String enemy) {
        return getEnemies().contains(enemy);
    }

    public List<String> getItemStats() {
        return getArrayAsList(gameData.path(ITEMS_NODE).path(ATTRIBUTES_NODE), STATS_NODE);
    }
    public boolean isItemStat(String itemStat) {
        return getItemStats().contains(itemStat);
    }

    public List<String> getItemTypes() {
        return getArrayAsList(gameData.path(ITEMS_NODE).path(ATTRIBUTES_NODE), TYPE_NODE);
    }
    public boolean isItemType(String itemTypes) {
        return getItemTypes().contains(itemTypes);
    }

    public List<String> getWeapons() {
        return getKeysAsList(gameData.path(ITEMS_NODE).path(WEAR_ITEM_NODE), WEAPONS_NODE);
    }
    public boolean isWeapon(String weapon) {
        return getWeapons().contains(weapon);
    }

    public List<String> getArmor() {
        return getKeysAsList(gameData.path(ITEMS_NODE).path(WEAR_ITEM_NODE), ARMOR_NODE);
    }
    public boolean isArmor(String armor) {
        return getArmor().contains(armor);
    }

    public List<String> getConsumables() {
        return getKeysAsList(gameData.path(ITEMS_NODE), CONSUMABLES_NODE);
    }
    public boolean isConsumable(String consumable) {
        return getConsumables().contains(consumable);
    }

    public List<String> getUtilityItems() {
        return getArrayAsList(gameData.path(ITEMS_NODE), UTILITY_NODE);
    }
    public boolean isUtilityItem(String utilityItem) {
        return getUtilityItems().contains(utilityItem);
    }

    public List<String> getRewardItems() {
        return getArrayAsList(gameData.path(ITEMS_NODE), REWARDS_NODE);
    }
    public boolean isRewardItem(String rewardItem) {
        return getRewardItems().contains(rewardItem);
    }

    public List<String> getLocations() {
        return getKeysAsList(gameData, LOCATIONS_NODE);
    }
    public boolean isLocation(String location) {
        return getLocations().contains(location);
    }

    public List<String> getArmoryList() {
        return getArrayAsList(gameData.path(ITEMS_NODE).path(SHOP_INVENTORY_NODE), ARMORY_LIST_NODE);
    }

    public List<String> getMagicList() {
        return getArrayAsList(gameData.path(ITEMS_NODE).path(SHOP_INVENTORY_NODE), MAGIC_LIST_NODE);
    }

    // allows you to create an instance of a character straight from your JSON game data
    public Player createPlayerClass(String classChoice) {
        if (!isPlayerClass(classChoice)) {
            throw new IllegalArgumentException("Requested character class does not exist in your game data");
        }

        Player result = null;
        try {
            JsonNode characterInformation = gameData.path(CHARACTERS_NODE).path(CLASS_NODE).path(classChoice);
            // allows you to pass in a JsonNode and it returns a Java Object of your choosing (as long as it has the proper fields)
            result = mapper.treeToValue(characterInformation, Player.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}