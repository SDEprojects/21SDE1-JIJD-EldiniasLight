package com.fourforfour.eldanialight.areas;

import com.fourforfour.eldanialight.Command;
import com.fourforfour.eldanialight.characters.Character;
import com.fourforfour.eldanialight.characters.NPCDict;

import java.util.List;

public class DangerArea extends Area{
    public DangerArea(String name, AreaList areas, List<Command> command,AreaInfo areaInfo) {
        super(name, areas, command,areaInfo, NPCDict.emptyCharacterList);
    }

}
