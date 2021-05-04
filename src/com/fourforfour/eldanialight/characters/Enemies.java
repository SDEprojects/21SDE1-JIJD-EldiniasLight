package com.fourforfour.eldanialight.characters;

import java.util.ArrayList;

public interface Enemies {

    Enemy goblin = new Enemy("Goblin",50,30,25,40,0,10,30,"drop something");
    Enemy wolf = new Enemy("Wolf",30,60,30,40,20,10,30,"wolf claw");
    Enemy troll = new Enemy("Troll", 60,45,60,15,0,35,40, "drop something cool");
    Enemy undead = new Enemy("undead",50,35,35,35,0,10,20,"drop something cools");
    Boss Giant = new Boss("Andre the Giant",100,100,150,35,0,100,150);
    Boss Dragon = new Boss("Haku",125,100,80,150,60,100,150);
    Boss Tyronious = new Boss("Tyronious the Black",300,150,150,150,150,10000,1000);




}
