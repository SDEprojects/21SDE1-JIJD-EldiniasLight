package com.fourforfour.eldanialight;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.io.InputStream;

class DataParser {
    private static String CHARACTER_NODE = "";

    private ObjectMapper mapper;

    // this will soon hold the entry point for the game dataParser file
    private JsonNode root;

    DataParser(String fileName) {
        try {
            // grabs the game dataParser file
            InputStream gameData = getClass().getResourceAsStream("/" + fileName + ".json");
            // create a mapper to read through the game data
            mapper = new ObjectMapper();
            // create the JSON starting point
            root = mapper.readTree(gameData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isCharacter(String characterName) {
        JsonNode result = root.path(CHARACTER_NODE).get(characterName);
        return result != null;
    }

    // allows you to create an instance of a character straight from your JSON game data
    public Character createCharacter(String characterName) {
        if (!isCharacter(characterName)) {
            throw new IllegalArgumentException("Requested character does not exist in your game data");
        }

        Character result = null;
        try {
            JsonNode characterInformation = root.path(CHARACTER_NODE).path(characterName);
            // allows you to pass in a JsonNode and it returns a Java Object of your choosing (as long as it has the proper fields)
            result = mapper.treeToValue(characterInformation, Character.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}