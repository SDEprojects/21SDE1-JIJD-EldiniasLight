package com.fourforfour.eldanialight.characters;

import com.fourforfour.eldanialight.Game;

public class QuestsOfEldania {
    public static Quest killTyroneious = new Quest("Kill Tyroneious the warlock") {
        @Override
        public boolean completion() {
            boolean res = false;
            if(Game.character.questItems.contains("gem")){
                res = true;
            }
            return res;
        }
    };
}
