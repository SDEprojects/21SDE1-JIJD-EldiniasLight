package com.fourforfour.eldanialight.areas;

import com.fourforfour.eldanialight.Command;
import com.fourforfour.eldanialight.battle.BattleSequence;
import com.fourforfour.eldanialight.characters.Character;
import com.fourforfour.eldanialight.characters.Enemy;

import java.util.List;


public class BattleArea extends DangerArea{
    public Enemy enemy;
    private String previousArea;
    private BattleSequence battleSequence;
    private BattleAreaTypes battleAreaType;

    public BattleArea(String name, AreaList areas, List<Command> command, AreaInfo areaInfo, String previousArea, BattleAreaTypes battleAreaType) {
        super(name, areas, command, areaInfo);
        battleSequence = new BattleSequence(battleAreaType);
        this.previousArea = previousArea;
        this.battleAreaType = battleAreaType;
    }

    /**
     * Band aid- Reviving enemy needs to be pulled out... also, may be wise to create an enemy generator.
     */
    public void battle(){
        enemy = EnemyGenerator.generate(battleAreaType);
        battleSequence.battle();
    }

    public String getPreviousArea(){
        return this.previousArea;
    }

    public void showEnemy(){
        System.out.println( "Current enemy is :" +this.enemy.getName());

    }
}
