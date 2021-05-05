package com.fourforfour.eldanialight;

import com.fourforfour.eldanialight.areas.Area;
import com.fourforfour.eldanialight.areas.AreaInfo;
import com.fourforfour.eldanialight.areas.AreaList;
import com.fourforfour.eldanialight.characters.NPCDict;
import com.fourforfour.eldanialight.characters.Player;
public class Game {
    //This is setting the current area of the game, the beginning which is lucino town
    public static Area currentArea = new Area("The Beginning", new AreaList("lucino town"), AreaKommands.worldCommand,AreaInfo.LUCINO_TOWN, NPCDict.emptyCharacterList);

    //This is setting the character
    public static Player character;

    //This is initializing a new world
    public static World world = new World();

    //Commands command ... each command has a list of commands that it can take in
    //as the story progresses, this command will change
    Game(Player character){
        this.character = character;
    }

    //Play method waits for the user input and gets rid of whitespace, concatenates the strings ot join them
    public void play(String input) {
        input = input.trim();
        String[] inputs = input.split(" ");
        if(inputs.length > 1){
            input = String.join("_", inputs);
        }
        currentArea.commands(input);
    }

}
