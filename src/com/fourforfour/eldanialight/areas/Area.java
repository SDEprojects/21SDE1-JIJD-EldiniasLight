//package com.fourforfour.eldanialight.areas;
//
//import com.fourforfour.eldanialight.AreaKommands;
//import com.fourforfour.eldanialight.Command;
//import com.fourforfour.eldanialight.Game;
//import com.fourforfour.eldanialight.characters.Character;
//import com.fourforfour.eldanialight.characters.QuestNPC;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Locale;
//import java.util.Scanner;
//
//public class Area {
//
//    //FIELDS
//    private String name;
//    private HashMap<String, Character> characters = new HashMap<>();
//    private AreaList areas;
//    private List<Command> command;
//    private List<? super Character> npcList;
//    private Scanner scanner = new Scanner(System.in);
//    private AreaInfo areaInfo;
//
//
//    //CONSTRUCTORS
//    public Area(String name, AreaList areas, List<Command> command, AreaInfo areaInfo, List<? super Character> npcList){
//        this.name = name;
//        this.areas = areas;
//        this.command = command;
//        this.areaInfo = areaInfo;
//        this.npcList = npcList;
//    }
//
//    //METHODS
//
//    //print commands with "_" replaced by " ".
//    public void printCommands(){
//        for(Command cmd : command){
//            String c = cmd.toString();
//            c = c.replace("_", " ");
//            System.out.println(c);
//        }
//    }
//
//    //used in class Game
//    public void commands(String input){
//        try{
//            AreaKommands.commands(Command.valueOf(input));
//        }catch(IllegalArgumentException e){
//            if(input.equalsIgnoreCase("exit()")){
//                return;
//            }
//            System.out.println("Invalid Command");
//        }
//    }
//
//    //CHECK THE CHARACTERS LIST
//    //Used in Class AreaKommands
//    public void viewCharacters(){
//        if(npcList.isEmpty()){
//            System.out.println("No characters in this area");
//        }else{
//            printCharacters();
//            System.out.println("Who would you like to speak to?");
//            talkToCharacter();
//        }
//    }
//
//    //use scanner to read input and interact with character. Helper function to viewCharacters(). See above function.
//    private void talkToCharacter(){
//        String input = scanner.nextLine();
//        talksNPC(input);
//    }
//
//    //helper function to talkToCharacter(). See above function.
//    private void talksNPC(String name){
//        for(Object character: npcList){
//            if((((Character)character).getName()).equalsIgnoreCase(name)){
//                ((QuestNPC)character).talk();
//            }
//        }
//    }
//
//    //Helper function to viewCharacters(). See above.
//    //print characters from npcList
//    public void printCharacters(){
//        for(Object character: npcList){
//            QuestNPC current = (QuestNPC) character;
//            System.out.println(current.getName());
//        }
//    }
//
//    //Used in class Commands
//    //show the areas available to the player, get input from the player and check if it is a valid entry
//    public void venture(){
//        System.out.println("Where would you like to go?");
//        areas.view();
//        String destination = scanner.nextLine().toLowerCase(Locale.ROOT);
//        if(areas.areaList.contains(destination)){
//            Game.currentArea = Game.world.get(destination);
//            System.out.println("You have entered "+ Game.currentArea.getName());
//        }else{
//            System.out.println("Invalid Entry");
//            venture();
//        }
//    }
//
//    //*** NOT being used anywhere
//    public void view(){
//        System.out.println(Game.currentArea.areaInfo);
//    }
//
//    //GETTERS
//    public AreaList getAreas(){
//        return this.areas;
//    }
//
//    public void getAreaInfo() {
//        System.out.println(areaInfo.getDescription());
//    }
//
//    public String getName() {
//        return name;
//    }
//
//}//EOC
