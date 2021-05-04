package com.fourforfour.eldanialight.areas;

import com.fourforfour.eldanialight.AreaKommands;
import com.fourforfour.eldanialight.characters.Enemy;
import com.fourforfour.eldanialight.characters.NPCDict;
import com.fourforfour.eldanialight.characters.QuestNPC;
import com.fourforfour.eldanialight.characters.ShopNPC;
import com.fourforfour.eldanialight.items.Chest;
import com.fourforfour.eldanialight.items.ItemsOfEldinia;
import com.fourforfour.eldanialight.items.UtilityItem;

/**
 * shopCommands
 * townCommands
 * openworldCommands
 * battleCommands
 * will have to keep track of previous location for battle vs dangerArea
 */
public interface WorldAreas {

    Area lucino_town = new SafeArea("lucino town",
            new AreaList("lucino shops", "carnival", "lucino town hall"),
            AreaKommands.worldCommand,AreaInfo.LUCINO_TOWN, NPCDict.emptyCharacterList);

    SafeArea lucino_shops = new SafeArea("lucino shops",
            new AreaList("armory", "magic", "inn", "front gate", "lucino town"),
            AreaKommands.worldCommand,AreaInfo.LUCINO_SHOPS, NPCDict.emptyCharacterList);
    ShopArea armory = new ShopArea("armory",
            new AreaList("lucino shops"),
            AreaKommands.shopCommand,
            ItemsOfEldinia.armoryList,
            new ShopNPC("Hank", 10, 10, 10, 10, 10, 34,
                    "Welcome to the Armory"),AreaInfo.LUCINO_SHOPS);

    QuestArea warchiefQuest = new QuestArea("Warchief", new AreaList("town hall"), AreaKommands.talkCommand, null, NPCDict.warcheif, AreaInfo.TRAINING_GROUNDS);

    SafeArea lucino_town_hall = new SafeArea("lucino town hall", new AreaList("lucino town, warchief"), AreaKommands.townHallCommand, AreaInfo.LUCINO_TOWN, NPCDict.questCharacterList);

    ShopArea magic = new ShopArea("magic",
            new AreaList("lucino shops"),
            AreaKommands.shopCommand,
            ItemsOfEldinia.magicList,
            new ShopNPC("Hans", 10, 10, 10 ,10, 10, 34, "Welcome to the Magic Shop"),AreaInfo.LUCINO_SHOPS);

    SafeArea front_gate = new SafeArea("lucino front gate",
            new AreaList("lucino shops", "open world"),
            AreaKommands.worldCommand, AreaInfo.FRONT_GATE, NPCDict.emptyCharacterList);


    DangerArea open_world = new DangerArea("open world",
            new AreaList("front gate", "badlands", "evil forest", "fire mountain", "castle eldina"),
            AreaKommands.worldCommand,AreaInfo.OPEN_WORLD);


    DangerArea evil_forest = new DangerArea("evil forest",
            new AreaList("open world", "inner evil forest", "evil forest lair"),
            AreaKommands.worldCommand,AreaInfo.EVIL_FOREST);

    BattleArea inner_evil_forest = new BattleArea("inner evil forest", new AreaList("evil forest", "evil forest lair"),
            AreaKommands.battleCommand,
            AreaInfo.INNER_EVIL_FOREST,"evil forest",BattleAreaTypes.FOREST);

    BattleArea evil_forest_lair = new BattleArea("inner evil forest", new AreaList("evil forest", "inner evil forest "),
            AreaKommands.battleCommand,
            AreaInfo.EVIL_FOREST_LAIR,"evil forest",BattleAreaTypes.GIANT);

    DangerArea badlands = new DangerArea("badlands",
            new AreaList("open world", "elki town", "fang hill"),
            AreaKommands.worldCommand,AreaInfo.BADLANDS);

    Area elki_town = new SafeArea("elki town",
            new AreaList("badlands", "fang hill"),
            AreaKommands.worldCommand,AreaInfo.ELKI_TOWN, NPCDict.emptyCharacterList);

    BattleArea fang_hill = new BattleArea("fang hill", new AreaList("badlands", "elki town"),
            AreaKommands.battleCommand,
            AreaInfo.FANG_HILL,"fang hill",BattleAreaTypes.DESERT);


    DangerArea fire_mountain = new DangerArea("fire mountain",
            new AreaList("open world", "base", "cave"), AreaKommands.worldCommand,AreaInfo.FIRE_MOUNTAIN);
    BattleArea base = new BattleArea("base", new AreaList("fire mountain", "cave"),
            AreaKommands.battleCommand,
            AreaInfo.BASE,"base",BattleAreaTypes.MOUNTAINS);

    BattleArea cave = new BattleArea("cave", new AreaList("fire mountain", "base"),
            AreaKommands.battleCommand,
            AreaInfo.CAVE,"cave",BattleAreaTypes.DRAGON);

    //castle eldina
    DangerArea castle_eldina = new DangerArea("castle eldina",
            new AreaList("open world", "training grounds", "dungeon", "throne room"),
            AreaKommands.worldCommand, AreaInfo.CASTLE_ELDINA);
    SafeArea training_grounds = new SafeArea("training grounds",
            new AreaList("armory", "magic", "castle eldina","dungeon","throne room"),
            AreaKommands.worldCommand,AreaInfo.TRAINING_GROUNDS, NPCDict.emptyCharacterList);

    BattleArea dungeon = new BattleArea("dungeon", new AreaList("training grounds", "castle eldina","throne room"),
            AreaKommands.battleCommand,

            AreaInfo.DUNGEON,"dungeon",BattleAreaTypes.DARK);

    BattleArea throne_room = new BattleArea("throne room", new AreaList("castle eldina", "dungeon","training grounds"),
            AreaKommands.battleCommand,
            AreaInfo.THRONE_ROOM,"throne room",BattleAreaTypes.WARLOCK);


}
