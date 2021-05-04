package com.fourforfour.eldanialight.areas;

import com.fourforfour.eldanialight.characters.Enemy;
import com.fourforfour.eldanialight.items.Item;
import com.fourforfour.eldanialight.items.ItemList;

import java.util.List;

public class EnemyGenerator {
    private static BattleAreaTypes battleAreaType;
    private String dialog;


    public static Enemy generate(BattleAreaTypes area){
        return  newEnemy(area);
    }


    private static Enemy newEnemy(BattleAreaTypes area){
        Enemy result;
        switch (area){
            case MOUNTAINS:
                result = new Enemy("Mountain Goat", 12, 21, 21, 1, 12 ,12, 10,"horn");
                break;
            case FOREST:
                result = new Enemy("Goblin",50,30,25,40,0,10,30,"rusty knife");;
                break;
            case DARK:
               // result = new Enemy("Dark Mist", 12, 21, 21, 12, 12 ,12, 10,"something");
                result = new Enemy("Troll", 60,45,60,15,0,35,40, "ruby");
                break;
            case FIRE:
                result = new Enemy("Fire drake", 11, 12, 21, 2, 21 ,12, 10,"something else");
                break;
            case DESERT:
                result = new Enemy("Wolf",30,60,30,40,20,10,30,"wolf claw");
                break;
            case GIANT:
                result = new Enemy("Andre the Giant",100,100,150,35,0,100,150,"Rope");
                break;
            case DRAGON:
                result = new Enemy("Haku",125,100,80,150,60,100,150,"scale");
                break;
            case WARLOCK:
                result = new Enemy("Tyronious the Black",300,150,150,150,150,10000,1000,"gem of light");
                break;
            default:
                result = new Enemy("Unknown Enemy", 999, 999, 999, 999, 999 ,999, 99999,"unknown");
        }
        return result;
    }
    //public static List<Enemy> darkList = new Enemy(goblin).getList(); want to create a list of enemies for each area
}
