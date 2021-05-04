package com.fourforfour.eldanialight.areas;

import com.fourforfour.eldanialight.Game;

import java.util.ArrayList;
import java.util.List;

public class AreaList {
    List<String> areaList = new ArrayList<>();
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_WHITE = "\u001B[37m";

    public AreaList(String ...strings){
        for(String s: strings){
            areaList.add(s);
        }
    }

    public List<String> getAreaList() {
        return areaList;
    }

    public void view(){
        for(String area: areaList){
            System.out.print("->");
            if(Game.world.get(area) instanceof BattleArea){
                System.out.print(TEXT_RED+"BATTLE: "+TEXT_RESET);
            }
            if(Game.world.get(area) instanceof ShopArea){
                System.out.print(TEXT_YELLOW+"SHOP: "+TEXT_RESET);
            }
            if(Game.world.get(area) instanceof DangerArea){
                System.out.print(TEXT_BLUE+"OPEN WORLD: "+TEXT_RESET);
            }
            if(Game.world.get(area) instanceof QuestArea){
                System.out.print(TEXT_GREEN+"QUESTS: "+TEXT_RESET);
            }
            else{
                System.out.print("TOWN: ");
            }
            System.out.println(area);
        }
    }
}
