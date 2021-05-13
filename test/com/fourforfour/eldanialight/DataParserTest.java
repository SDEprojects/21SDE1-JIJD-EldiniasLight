package com.fourforfour.eldanialight;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fourforfour.eldanialight.characters.Character;
import com.fourforfour.eldanialight.characters.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DataParserTest {
    DataParser dp;

    @Before
    public void setUp() {
        dp = new DataParser();
    }

    @Test
    public void createPlayerClass_shouldReturnTrue_whenClassAllowed() {
        assertTrue(dp.createPlayerClass("mage") instanceof Player);
        assertTrue(dp.createPlayerClass("mage") instanceof Character);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPlayerClass_shouldThrowException_whenClassNotAllowed() {
        dp.createPlayerClass("TEST");
    }

    @Test
    public void createPlayerClass_shouldReturnTrue_whenClassStatsCorrect() {
        Player player = dp.createPlayerClass("mage");
        assertEquals(50.0, player.getHealth(), 0.001);
        assertEquals(10, player.getStrength());
        assertEquals(20, player.getDefense());
        assertEquals(50, player.getBezos());
        assertEquals(30, player.getIntel());
        assertEquals(20, player.getSpeed());
    }

    @Test
    public void getClasses_shouldReturnTrue() {
        List<String> expected = Arrays.asList("knight", "mage", "archer");
        assertEquals("The classes aren't matching up", expected, dp.getPlayerClasses());
    }

    @Test
    public void getNPCs_shouldReturnTrue() {
        List<String> expected = Arrays.asList(
                "war chief", "blacksmith", "shop keeper", "inn keeper", "hank", "hans"
        );
        assertEquals("The NPCs aren't matching up", expected, dp.getNPCs());
    }

    @Test
    public void getEnemies_shouldReturnTrue() {
        List<String> expected = Arrays.asList(
                "goblin", "wolf", "troll", "undead", "andre the giant", "haku", "tyronious the black"
        );
        assertEquals("The enemies aren't matching up", expected, dp.getEnemies());
    }

    @Test
    public void getItemStats_shouldReturnTrue() {
        List<String> expected = Arrays.asList("health", "defense", "intellect", "speed", "strength");
        assertEquals("The item stats aren't matching up", expected, dp.getItemStats());
    }

    @Test
    public void getItemTypes_shouldReturnTrue() {
        List<String> expected = Arrays.asList("head", "chest", "main hand", "off hand");
        assertEquals("The item types aren't matching up", expected, dp.getItemTypes());
    }

    @Test
    public void getWeapons_shouldReturnTrue() {
        List<String> expected = Arrays.asList("sword", "battleaxe", "bow", "staff");
        assertEquals("The item types aren't matching up", expected, dp.getWeapons());
    }

    @Test
    public void getArmor_shouldReturnTrue() {
        List<String> expected = Arrays.asList("shield", "chestplate", "lighthelm");
        assertEquals("The item types aren't matching up", expected, dp.getArmor());
    }

    @Test
    public void getConsumables_shouldReturnTrue() {
        List<String> expected = Arrays.asList("healthpotion", "manapotion", "speedpotion", "strengthpotion");
        assertEquals("The item types aren't matching up", expected, dp.getConsumables());
    }

    @Test
    public void getUtilityItems_shouldReturnTrue() {
        List<String> expected = Arrays.asList("escaperope", "treasurechestkey");
        assertEquals("The item types aren't matching up", expected, dp.getUtilityItems());
    }

    @Test
    public void getRewardItems_shouldReturnTrue() {
        List<String> expected = Arrays.asList("wolf claw");
        assertEquals("The item types aren't matching up", expected, dp.getRewardItems());
    }

    @Test
    public void getArmoryList_shouldReturnTrue() {
        List<String> expected = Arrays.asList("sword", "shield", "battleaxe", "bow", "staff");
        assertEquals("The item types aren't matching up", expected, dp.getArmoryList());
    }

    @Test
    public void getMagicList_shouldReturnTrue() {
        List<String> expected = Arrays.asList("healthpotion", "manapotion", "speedpotion", "strengthpotion");
        assertEquals("The item types aren't matching up", expected, dp.getMagicList());
    }

    @Test
    public void getLocations_shouldReturnTrue() {
        List<String> expected = Arrays.asList(
                "home", "lucino town", "lucino shops", "armory", "warchief", "lucino town hall",
                "magic", "front gate", "open world", "evil forest", "inner evil forest",
                "evil forest lair", "badlands", "elki town","fang hill", "fire mountain",
                "base", "cave", "castle eldina", "training grounds", "dungeon", "throne room"
        );
        assertEquals("The item types aren't matching up", expected, dp.getLocations());
    }

    @Test
    public void isPlayerClass_shouldReturnTrue_whenClassAllowed() {
        assertTrue(dp.isPlayerClass("mage"));
        assertTrue(dp.isPlayerClass("knight"));
        assertTrue(dp.isPlayerClass("archer"));
    }

    @Test
    public void isPlayerClass_shouldReturnTrue_whenClassNotAllowed() {
        assertFalse(dp.isPlayerClass("TEST-CLASS"));
        assertFalse(dp.isPlayerClass("Mage"));
    }

    @Test
    public void isNPC_shouldReturnTrue() {
        assertTrue(dp.isNPC("hans"));
    }

    @Test
    public void isEnemy_shouldReturnTrue() {
        assertTrue(dp.isEnemy("wolf"));
    }

    @Test
    public void isItemStat_shouldReturnTrue() {
        assertTrue(dp.isItemStat("health"));
    }

    @Test
    public void isItemType_shouldReturnTrue() {
        assertTrue(dp.isItemType("main hand"));
    }

    @Test
    public void isWeapon_shouldReturnTrue() {
        assertTrue(dp.isWeapon("sword"));
    }

    @Test
    public void isArmor_shouldReturnTrue() {
        assertTrue(dp.isArmor("lighthelm"));
    }

    @Test
    public void isConsumable_shouldReturnTrue() {
        assertTrue(dp.isConsumable("healthpotion"));
    }

    @Test
    public void isUtilityItem_shouldReturnTrue() {
        assertTrue(dp.isUtilityItem("escaperope"));
    }

    @Test
    public void isRewardItem_shouldReturnTrue() {
        assertTrue(dp.isRewardItem("wolf claw"));
    }

    @Test
    public void isLocation() {
        assertTrue(dp.isLocation("lucino town"));
    }

    @Test
    public void getLocationType() {
        String expected = "safe";
        assertEquals("The location type doesn't match", expected, dp.getLocationType("lucino town"));
    }

    @Test
    public void getLocationDescription() {
        String expected = "the largest city in eldina and one of the last strong holds preventing tyroneious form gaining complete control";
        assertEquals("The location description doesn't match", expected, dp.getLocationDescription("lucino town"));
    }

    @Test
    public void getLocationNeighbors() {
        List<String> expected = Arrays.asList("lucino shops", "lucino town hall");
        assertEquals("The location neighbors don't match", expected, dp.getLocationNeighbors("lucino town"));
    }

    @Test
    public void getLocationCommands() {
        List<String> expected = Arrays.asList("view", "view items", "venture", "view stats");
        assertEquals("The location commands don't match", expected, dp.getLocationCommands("lucino town"));
    }

    @Test
    public void getEnemy() {
        String json = "{ \"name\" : \"wolf\", \"health\" : 30, \"defense\" : 60, \"strength\" : 30, \"speed\" : 40, \"intel\" : 20, \"bezos\" : 10, \"xp\" : 30, \"reward\" : \"wolf claw\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode expected = objectMapper.readTree(json);
            assertEquals(expected, dp.getEnemy("wolf"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getWeapon() {
        String json = "{ \"name\" : \"sword\", \"attack\" : 5, \"value\" : 20, \"hand\" : \"main hand\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode expected = objectMapper.readTree(json);
            assertEquals(expected, dp.getWeapon("sword"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getArmor() {
        String json = "{ \"name\" : \"chestplate\", \"health\" : 10, \"defense\" : 8, \"value\" : 20, \"hand\" : \"chest\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode expected = objectMapper.readTree(json);
            assertEquals(expected, dp.getArmor("chestplate"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getConsumable() {
        String json = "{ \"name\" : \"healthpotion\", \"enhancer\" : 10, \"attribute\" : \"health\", \"value\" : 5}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode expected = objectMapper.readTree(json);
            assertEquals(expected, dp.getConsumable("healthpotion"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getLocationNPC() {
        String json = "{ \"name\" : \"hank\", \"type\" : \"shop\", \"items\" : \"armorylist\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode expected = objectMapper.readTree(json);
            assertEquals(expected, dp.getLocationNPC("armory"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void createEnemy_shouldReturnTrue_whenEnemyAllowed() {
//        Enemy test = dp.createEnemy("Haku");
//        assertTrue(test instanceof Enemy);
//        assertTrue(test instanceof Character);
//    }
}