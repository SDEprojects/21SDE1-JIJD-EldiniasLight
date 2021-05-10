package com.fourforfour.eldanialight.items;


public class Chest {
    //Chest class so the player can open and get bezos or items
    //field to initialize bezos(money)
   private int bezos;
   private UtilityItem utilityItem;

   public Chest(int bezos){
       this.bezos = bezos;
   }

   public Chest(UtilityItem utilityItem){
       this.utilityItem= utilityItem;
   }

   //Chest instantiation with 20 bezos
   Chest chest1 = new Chest(20);


    public int getBezos() {
        return bezos;
    }

    public UtilityItem getUtilityItem() {
        return utilityItem;
    }
}//EOC