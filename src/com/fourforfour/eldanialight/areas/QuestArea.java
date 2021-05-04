package com.fourforfour.eldanialight.areas;
import com.fourforfour.eldanialight.Command;
import com.fourforfour.eldanialight.Game;
import com.fourforfour.eldanialight.characters.NPCDict;
import com.fourforfour.eldanialight.characters.QuestNPC;
import com.fourforfour.eldanialight.items.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class QuestArea extends SafeArea{
    HashMap<String, Item> items = new HashMap<>();
    public QuestNPC driver;

    public QuestArea(String name, AreaList areas, List<Command> command, List<Item> itemList, QuestNPC driver,AreaInfo areaInfo) {
        super(name, areas, command,areaInfo, NPCDict.emptyCharacterList);
        this.driver = driver;


    }

    public void view(){
        items.forEach((k,v) -> System.out.println(k) );
    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public void buy(){
        driver.talk();

    }
}