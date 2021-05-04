package com.fourforfour.eldanialight.areas;
import com.fourforfour.eldanialight.Command;
import com.fourforfour.eldanialight.Game;
import com.fourforfour.eldanialight.characters.NPCDict;
import com.fourforfour.eldanialight.characters.ShopNPC;
import com.fourforfour.eldanialight.items.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
public class ShopArea extends SafeArea{
    HashMap<String, Item> items = new HashMap<>();
    public ShopNPC vendor;

    public ShopArea(String name, AreaList areas, List<Command> command, List<Item> itemList, ShopNPC vendor,AreaInfo areaInfo) {
        super(name, areas, command, areaInfo, NPCDict.emptyCharacterList);
        this.vendor = vendor;
        for(Item item: itemList){
            this.items.put(item.getName(), item);
        }
    }

    public void view(){
        items.forEach((k,v) -> System.out.println(k) );
    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public void buy(){
        vendor.talk();
        vendor.barter(items);
        Scanner scanner = new Scanner(System.in);
        String userSelection = scanner.nextLine();
        if(items.containsKey(userSelection)){
            Game.character.addItem(items.get(userSelection));
            items.remove(userSelection);
        }else{
            buy();
        }
    }
}