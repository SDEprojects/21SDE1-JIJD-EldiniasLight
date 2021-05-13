package com.fourforfour.eldanialight;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

//    @Test
//    public void createPlayerClass_shouldReturnTrue_whenClassAllowed() {
//        assertTrue(dp.createPlayerClass("Mage") instanceof Player);
//        assertTrue(dp.createPlayerClass("Mage") instanceof Character);
//    }

    @Test(expected = IllegalArgumentException.class)
    public void createPlayerClass_shouldThrowException_whenClassNotAllowed() {
        dp.createPlayerClass("TEST");
    }

    @Test
    public void createPlayerClass_shouldReturnTrue_whenClassStatsCorrect() {
        Player player = dp.createPlayerClass("Mage");
        player.viewStats();
        player.setName("TEST");
        player.viewStats();
    }

    @Test
    public void getClasses_shouldReturnTrue() {
        List<String> expected = Arrays.asList("Knight", "Mage", "Archer");
        assertEquals("The classes aren't matching up", expected, dp.getPlayerClasses());
    }

    @Test
    public void getNPCs_shouldReturnTrue() {
        List<String> expected = Arrays.asList(
                "War Chief", "Blacksmith", "Shop Keeper", "Inn Keeper", "Hank", "Hans"
        );
        assertEquals("The NPCs aren't matching up", expected, dp.getNPCs());
    }

    @Test
    public void getEnemies_shouldReturnTrue() {
        List<String> expected = Arrays.asList(
                "Goblin", "Wolf", "Troll", "Undead", "Andre the Giant", "Haku", "Tyronious the Black"
        );
        assertEquals("The enemies aren't matching up", expected, dp.getEnemies());
    }

    @Test
    public void getItemStats_shouldReturnTrue() {
        List<String> expected = Arrays.asList("Health", "Defense", "Intellect", "Speed", "Strength");
        assertEquals("The item stats aren't matching up", expected, dp.getItemStats());
    }

    @Test
    public void getItemTypes_shouldReturnTrue() {
        List<String> expected = Arrays.asList("Head", "Chest", "Main Hand", "Off Hand");
        assertEquals("The item types aren't matching up", expected, dp.getItemTypes());
    }

    @Test
    public void getWeapons_shouldReturnTrue() {
        List<String> expected = Arrays.asList("sword", "battleAxe", "bow", "staff");
        assertEquals("The item types aren't matching up", expected, dp.getWeapons());
    }

    @Test
    public void getArmor_shouldReturnTrue() {
        List<String> expected = Arrays.asList("shield", "chestPlate", "lightHelm");
        assertEquals("The item types aren't matching up", expected, dp.getArmor());
    }

    @Test
    public void getConsumables_shouldReturnTrue() {
        List<String> expected = Arrays.asList("healthPotion", "manaPotion", "speedPotion", "strengthPotion");
        assertEquals("The item types aren't matching up", expected, dp.getConsumables());
    }

    @Test
    public void getUtilityItems_shouldReturnTrue() {
        List<String> expected = Arrays.asList("escapeRope", "treasureChestKey");
        assertEquals("The item types aren't matching up", expected, dp.getUtilityItems());
    }

    @Test
    public void getRewardItems_shouldReturnTrue() {
        List<String> expected = Arrays.asList("wolf claw");
        assertEquals("The item types aren't matching up", expected, dp.getRewardItems());
    }

    @Test
    public void getArmoryList_shouldReturnTrue() {
        List<String> expected = Arrays.asList("sword", "shield", "battleAxe", "bow", "staff");
        assertEquals("The item types aren't matching up", expected, dp.getArmoryList());
    }

    @Test
    public void getMagicList_shouldReturnTrue() {
        List<String> expected = Arrays.asList("healthPotion", "manaPotion", "speedPotion", "strengthPotion");
        assertEquals("The item types aren't matching up", expected, dp.getMagicList());
    }

    @Test
    public void getLocations_shouldReturnTrue() {
        List<String> expected = Arrays.asList(
                "Lucino Town", "Lucino Shops", "Armory", "Warchief", "Lucino Town Hall",
                "Magic", "Front Gate", "Open World", "Evil Forest", "Inner Evil Forest",
                "Evil Forest Lair", "Badlands", "Elki Town","Fang Hill", "Fire Mountain",
                "Base", "Cave", "Castle Eldina", "Training Grounds", "Dungeon", "Throne Room"
        );
        assertEquals("The item types aren't matching up", expected, dp.getLocations());
    }

    @Test
    public void isPlayerClass_shouldReturnTrue_whenClassAllowed() {
        assertTrue(dp.isPlayerClass("Mage"));
        assertTrue(dp.isPlayerClass("Knight"));
        assertTrue(dp.isPlayerClass("Archer"));
    }

    @Test
    public void isPlayerClass_shouldReturnTrue_whenClassNotAllowed() {
        assertFalse(dp.isPlayerClass("TEST-CLASS"));
        assertFalse(dp.isPlayerClass("mage"));
    }

    @Test
    public void isNPC_shouldReturnTrue() {
        assertTrue(dp.isNPC("Hans"));
    }

    @Test
    public void isEnemy_shouldReturnTrue() {
        assertTrue(dp.isEnemy("Wolf"));
    }

    @Test
    public void isItemStat_shouldReturnTrue() {
        assertTrue(dp.isItemStat("Health"));
    }

    @Test
    public void isItemType_shouldReturnTrue() {
        assertTrue(dp.isItemType("Main Hand"));
    }

    @Test
    public void isWeapon_shouldReturnTrue() {
        assertTrue(dp.isWeapon("sword"));
    }

    @Test
    public void isArmor_shouldReturnTrue() {
        assertTrue(dp.isArmor("lightHelm"));
    }

    @Test
    public void isConsumable_shouldReturnTrue() {
        assertTrue(dp.isConsumable("healthPotion"));
    }

    @Test
    public void isUtilityItem_shouldReturnTrue() {
        assertTrue(dp.isUtilityItem("escapeRope"));
    }

    @Test
    public void isRewardItem_shouldReturnTrue() {
        assertTrue(dp.isRewardItem("wolf claw"));
    }

    @Test
    public void isLocation() {
        assertTrue(dp.isLocation("Lucino Town"));
    }

    @Test
    public void getLocationType() {
        String expected = "safe";
        assertEquals("The location type doesn't match", expected, dp.getLocationType("Lucino Town"));
    }

    @Test
    public void getLocationDescription() {
        String expected = "The largest city in Eldina and one of the last strong holds preventing Tyroneious form gaining complete control";
        assertEquals("The location description doesn't match", expected, dp.getLocationDescription("Lucino Town"));
    }

    @Test
    public void getLocationNeighbors() {
        List<String> expected = Arrays.asList("Lucino Shops", "Lucino Town Hall");
        assertEquals("The location neighbors don't match", expected, dp.getLocationNeighbors("Lucino Town"));
    }

    @Test
    public void getLocationCommands() {
        List<String> expected = Arrays.asList("SHOP", "VIEW_ITEMS", "LEAVE", "VIEW_STATS");
        assertEquals("The location commands don't match", expected, dp.getLocationCommands("Lucino Town"));
    }

    @Test
    public void getEnemy() {
        String json = "{ \"name\" : \"Wolf\", \"health\" : 30, \"defense\" : 60, \"strength\" : 30, \"speed\" : 40, \"intel\" : 20, \"bezos\" : 10, \"xp\" : 30, \"reward\" : \"wolf claw\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode expected = objectMapper.readTree(json);
            assertEquals(expected, dp.getEnemy("Wolf"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getWeapon() {
        String json = "{ \"name\" : \"sword\", \"attack\" : 5, \"value\" : 20, \"hand\" : \"Main Hand\"}";
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
        String json = "{ \"name\" : \"chestPlate\", \"health\" : 10, \"defense\" : 8, \"value\" : 20, \"hand\" : \"Chest\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode expected = objectMapper.readTree(json);
            assertEquals(expected, dp.getArmor("chestPlate"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getConsumable() {
        String json = "{ \"name\" : \"healthPotion\", \"enhancer\" : 10, \"attribute\" : \"Health\", \"value\" : 5}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode expected = objectMapper.readTree(json);
            assertEquals(expected, dp.getConsumable("healthPotion"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getLocationNPC() {
        String json = "{ \"name\" : \"Hank\", \"type\" : \"Shop\", \"items\" : \"armoryList\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode expected = objectMapper.readTree(json);
            assertEquals(expected, dp.getLocationNPC("Armory"));
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