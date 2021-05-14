package com.fourforfour.eldanialight.areas;

import com.fourforfour.eldanialight.characters.Enemy;

public class EnemyGenerator {

    // FIELDS
    private static String battleAreaType;
    private String dialog;

    // METHODS

    // Used in class BattleArea and BattleSequence
    public static Enemy generate(String area) {
        return newEnemy(area);
    }

    //creating enemy specific to a location - helper function to generate()
    private static Enemy newEnemy(String location) {
        Enemy result;
        switch (location) {
            case "badlands":
                result = new Enemy("mountain goat", 12, 21, 21, 1, 12, 12, 10, "horn"); break;
            case "evil forest lair":
                result = new Enemy("goblin", 50, 30, 25, 40, 0, 10, 30, "rusty knife"); break;
            case "cave":
                result = new Enemy("troll", 60, 45, 20, 15, 0, 35, 40, "ruby"); break;
            case "base":
                result = new Enemy("fire drake", 11, 12, 21, 2, 21, 12, 10, "something else"); break;
            case "fang hill":
                result = new Enemy("wolf", 30, 60, 20, 40, 20, 10, 30, "wolf claw"); break;
            case "inner evil forest":
                result = new Enemy("andre the giant", 100, 35, 20, 35, 0, 100, 150, "Rope"); break;
            case "dungeon":
                result = new Enemy("haku", 30, 100, 30, 150, 60, 100, 150, "scale"); break;
            case "throne room":
                result = new Enemy("tyronious the black", 100, 100, 40, 100, 1, 10000, 1000, "gem of light"); break;
            default:
                result = new Enemy("unknown enemy", 999, 999, 999, 999, 999, 999, 99999, "unknown"); break;
        }
        return result;
    }
}//