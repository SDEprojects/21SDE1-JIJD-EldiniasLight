package com.fourforfour.eldanialight.items;

public class Item {


    private String name;
    private int count;
    private int itemWorth;
    private WearItemType wearItemType;

    public Item(String name) {
        this.setName(name);
        setCount(1);
    }

    public Item(String name, int count){
        setName(name);
        setCount(count);
    }

    public Item(String name, int count,int itemWorth){
        setName(name);
        setCount(count);
        setItemWorth(itemWorth);
    }
    public Item(String name, int count,int itemWorth, WearItemType wearItemType){
        setName(name);
        setCount(count);
        setItemWorth(itemWorth);
        setWearItemType(wearItemType);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public int getItemWorth() {
        return itemWorth;
    }

    public void setItemWorth(int itemWorth) {
        this.itemWorth = itemWorth;
    }

    public WearItemType getWearItemType() {
        return wearItemType;
    }

    public void setWearItemType(WearItemType wearItemType) {
        this.wearItemType = wearItemType;
    }

}
