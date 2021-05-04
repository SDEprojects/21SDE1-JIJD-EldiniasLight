package com.fourforfour.eldanialight.characters;

import java.util.ArrayList;
import java.util.List;

public class NPCList {

        List<?super Character> npcList = new ArrayList<>();

        public NPCList(){

        }

        public NPCList(Character ...characters){
            for(Character c: characters){
                npcList.add(c);
            }
        }

        public List<?super Character> getNPCList() {
            return npcList;
        }

        public void view(){
            for(Object npc: npcList){
                if (npc instanceof Character) {
                    Character character = (Character)npc;
                    System.out.print("->");
                    System.out.println(character.getName());
                }

            }
        }



}//EOC