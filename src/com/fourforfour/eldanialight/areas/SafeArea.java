package com.fourforfour.eldanialight.areas;

import com.fourforfour.eldanialight.Command;
import com.fourforfour.eldanialight.characters.Character;

import java.util.List;

public class SafeArea  extends Area{
    public SafeArea(String name, AreaList areas, List<Command> command,AreaInfo areaInfo, List<? super Character> characterList) {
        super(name, areas, command, areaInfo, characterList);
    }
}
