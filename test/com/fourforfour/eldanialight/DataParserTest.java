package com.fourforfour.eldanialight;

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
        assertTrue(dp.createPlayerClass("Mage") instanceof Player);
        assertTrue(dp.createPlayerClass("Mage") instanceof Character);
    }

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
    public void isClass_shouldReturnTrue_whenClassAllowed() {
        assertTrue(dp.isPlayerClass("Mage"));
        assertTrue(dp.isPlayerClass("Knight"));
        assertTrue(dp.isPlayerClass("Archer"));
    }

    @Test
    public void isClass_shouldReturnTrue_whenClassNotAllowed() {
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
}