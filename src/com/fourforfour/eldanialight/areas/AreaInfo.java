package com.fourforfour.eldanialight.areas;


import com.fourforfour.eldanialight.characters.Enemy;

public enum AreaInfo {

    //areas of Lucino town
    LUCINO_TOWN(" The largest city in Eldina and one of the last strong holds preventing Tyroneious form" +
            "gaining complete control"),
    LUCINO_SHOPS("You are in a market place with a variety of shops. You can see the Armory, Inn, and Magic shop. "),
    LUCINO_TOWN_HALL(""),
    FRONT_GATE("The main enterance to Lucino Town. It looks strong and sturdy, but you can see the marks left by a " +
            "recent attack"),

    OPEN_WORLD("A vast open area. You see a sign that point to the evil forest, badlands, fire mountain" +
            "and Castle Eldina"),

    //evil forest and areas within
    EVIL_FOREST("A forest full of brown trees. There is a odd smell in the air. There are two trial, one leading" +
            "deeper into the forest, and the second to the Giant's lair"),
    INNER_EVIL_FOREST("The path takes you deeper into the forest and it starts to get darker." +
            "There is is another path that takes you to what looks like a lair of some type. You cab hear a sinister sound" +
            "you turn and prepare for battle"),
    EVIL_FOREST_LAIR("The forest seems to have open up and the tree seem to no longer block the little light" +
            "in the sky. You can see the two trails that lead here. You can see the remains of the Giant's victims scattered " +
            "on the ground. Suddenly you hear a loud thud followed by another. They seem to get closer"),

    //Badlands and areas within
    BADLANDS ("You find yourself if a desolate land. you can tell that it was once full of life, but that life has been" +
            "drained by the dark powers of Tyroneous. There is sign pointing to Fang hill and Elki Town  "),
    ELKI_TOWN("A small town that sits within the Badlands. The people seem to be on edge"),
    FANG_HILL("A hill covered area known to be home of wolves. you should be careful as they can attack from nowhere"),
    //fire mountain and areas
    FIRE_MOUNTAIN("You have come to the area of Fire Mountain the highest area of all the land. You can see the red" +
            "glow from the lava within and the large smoke cloud it makes. It is know to the be the home of deadly Dragon" +
            "You can see the pat that takes you to the base of the mountain"),
    BASE("You have made it to the the base of the mountain. Above you can see a cave that is home to the Dragon " +
            "There seems to be a something moving towards you that doesn't want you to reach the cave"),
    CAVE("You have climbed the mountain face and reached a Cave. You enter and the smell of sulfur hangs in the air." +
            "It seems to get warmer as you mover further in steeping over the charred skeletons. Suddenly there is a" +
            "bright light and you hear a roar."),

    //castle eldina and areas
    CASTLE_ELDINA("The great Castle Eldian, once the home to King Lumas. You can see what the dark influence of " +
            "Tyroneius had done "),
    TRAINING_GROUNDS(" This is the area one ran buy the War chief to train the soldiers of Eldiean. Now you can find " +
            "some black market merchants that can sell you some useful items. "),
    DUNGEON("You find yourself in the castle dungeon. those once held as prisoners have been changed into monsters"),
    THRONE_ROOM("You have entered the throne room. You can see Tyroneious sitting with a sinister grin upon his face. " +
            "You can feel his evil intent as you prepare yourself for battle!");

    String info;

     AreaInfo(String info){
    this.info = info;
    }
    public String getDescription(){
        return info;
    }



}//EOC