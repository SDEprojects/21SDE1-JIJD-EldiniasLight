package com.fourforfour.eldanialight.items;

import java.util.ArrayList;
import java.util.List;

public class ItemList {
    List<Item> itemList = new ArrayList<>();

    public ItemList(Item... item) {
        for (Item i : item) {
            itemList.add(i);
        }
    }

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
