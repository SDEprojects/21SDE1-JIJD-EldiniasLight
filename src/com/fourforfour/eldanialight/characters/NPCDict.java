package com.fourforfour.eldanialight.characters;

import java.util.List;

public class NPCDict {
    public static QuestNPC warcheif = new QuestNPC("War Chief",10,10,10,50,10,5,
            "Greetings hero! Thank you for your help. Our forces are stretched thin defending Lucino Town" +
                    "\n Stop by the Shops to gear up for your adventure. Head to the Evil Forest to start on you way do " +
                    "stopping Tyoneious." , QuestsOfEldania.killTyroneious);

    private static ShopNPC blacksmith = new ShopNPC("Black Smith",5,5,5,1500,5,1,"Welcome to my Shop");

    private static ShopNPC shopkeep = new ShopNPC("Shop Keeper",5,5,5,2000,5,1,"Welcome to my Shop");

    private static ShopNPC innkeep = new ShopNPC("Inn Keeper",5,5,5,1000,5,1,"Welcome to the Inn");

    public static List<? super Character> emptyCharacterList = new NPCList().getNPCList();
    public static List<? super Character> questCharacterList = new NPCList(warcheif).getNPCList();

    ShopNPC getBlacksmith = new ShopNPC("Black Smith",5,5,5,1500,5,1,"Welcome to my Shop");



}//EOC