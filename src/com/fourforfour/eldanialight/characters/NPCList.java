//package com.fourforfour.eldanialight.characters;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class NPCList {
//    //wildcard declaration of super class character. So the list will be able to reference the object
//    List<? super Character> npcList = new ArrayList<>();
//
//    public NPCList() {
//
//    }
//
//    //looping through the npcList made above
//    public NPCList(Character... characters) {
//        for (Character c : characters) {
//            npcList.add(c);
//        }
//    }
//
//    public List<? super Character> getNPCList() {
//        return npcList;
//    }
//
//    //method to view the ncpList, it is never used
//    public void view() {
//        for (Object npc : npcList) {
//            if (npc instanceof Character) {
//                Character character = (Character) npc;
//                System.out.print("->");
//                System.out.println(character.getName());
//            }
//
//        }
//    }
//}//EOC