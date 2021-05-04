package com.fourforfour.eldanialight;

import com.fourforfour.eldanialight.areas.Area;
import com.fourforfour.eldanialight.areas.AreaList;
import com.fourforfour.eldanialight.areas.WorldAreas;

import java.util.ArrayList;
import java.util.HashMap;

public class World implements WorldAreas {
    HashMap<String, ? super Area> world = new HashMap<>();

    public World() {

        world.put("lucino town", lucino_town);
        world.put("lucino shops", lucino_shops);
        world.put("lucino town hall", lucino_town_hall);
        world.put("warcheif", warchiefQuest);
        world.put("armory", armory);
        world.put("magic", magic);
        world.put("front gate", front_gate);

        world.put("open world", open_world);

        world.put("evil forest", evil_forest);
        world.put("inner evil forest" , inner_evil_forest);
        world.put("evil forest lair", evil_forest_lair);

        world.put("badlands", badlands);
        world.put("elki town",elki_town);
        world.put("fang hill", fang_hill);

        world.put("fire mountain", fire_mountain);
        world.put("base",base);
        world.put("cave",cave);

        world.put("castle eldina", castle_eldina);
        world.put("training grounds", training_grounds);
        world.put("dungeon",dungeon);
        world.put("throne room", throne_room);

    }

    public Area get(String input) {
        return (Area) world.get(input);
    }
}
