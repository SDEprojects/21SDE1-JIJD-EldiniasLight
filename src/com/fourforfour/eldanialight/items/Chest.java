package com.fourforfour.eldanialight.items;
// A Chest class so the player can open and get bezos or items

public class Chest {
    //bezos
   private int bezos;
   private UtilityItem utilityItem;

   public Chest(int bezos){
       this.bezos = bezos;
   }
   public Chest(UtilityItem utilityItem){
       this.utilityItem= utilityItem;
   }

   Chest chest1 = new Chest(20);


    public int getBezos() {
        return bezos;
    }

    public UtilityItem getUtilityItem() {
        return utilityItem;
    }
}//EOC