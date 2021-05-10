package com.fourforfour.eldanialight.items;

import java.util.ArrayList;
import java.util.List;

public class ItemList {
    //List of the items in the new ArrayList
    List<Item> itemList = new ArrayList<>();

    //looping through the items in the list and adding them
    public ItemList(Item... item) {
        for (Item i : item) {
            itemList.add(i);
        }
    }

    //List returns the items after it's done looping
    public List<Item> getList() {
        return itemList;
    }





//    public List<Item> getAreaList() {
//        return itemList;
//    }

//    public void view() {
//        for (Item item : itemList) {
//            System.out.print("->");
//            System.out.println(item);
//        }
//    }
}
