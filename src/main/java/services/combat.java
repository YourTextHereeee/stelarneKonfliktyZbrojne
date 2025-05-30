package services;
import units.unit;

import java.util.List;
import java.util.ArrayList;

public class combat {

    private int targetPlanetID;
    private int attackerID;
    private int defenderID;

    private static List<unit> attackingUnits = new ArrayList<>();
    private static List<unit> defendingUnits = new ArrayList<>();

    //konstruktor
    public combat(int targetPlanetID, int attackerID, int defenderID) {
        this.targetPlanetID = targetPlanetID;
        this.attackerID = attackerID;
        this.defenderID = defenderID;
    }


    public int getTargetPlanetID() {
        return targetPlanetID;
    }

    public void setTargetPlanetID(int targetPlanetID) {
        this.targetPlanetID = targetPlanetID;
    }

    public int getAttackerID() {
        return attackerID;
    }

    public void setAttackerID(int attackerID) {
        this.attackerID = attackerID;
    }

    public int getDefenderID() {
        return defenderID;
    }

    public void setDefenderID(int defenderID) {
        this.defenderID = defenderID;
    }


    public static List<unit> getAttackingUnits() {

        return attackingUnits;
    }

    public static void setAttackingUnits(List<unit> attackingUnits) {

        combat.attackingUnits = attackingUnits;
    }

    public static List<unit> getDefendingUnits() {

        return defendingUnits;
    }

    public static void setDefendingUnits(List<unit> defendingUnits) {

        combat.defendingUnits = defendingUnits;
    }
}
