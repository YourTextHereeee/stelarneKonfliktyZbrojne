package services;

import mapTools.planet;
import units.*;

import java.util.ArrayList;
import java.util.List;

public class colonization {

    private int targetPlanetID;
    private int colonizationID;
    private short progress;

    //konstruktor
    public colonization(int targetPlanetID, int colonizationID, short progress) {
        this.targetPlanetID = targetPlanetID;
        this.colonizationID = colonizationID;
        this.progress = progress;
    }

    public int getTargetPlanetID() {

        return targetPlanetID;
    }

    public void setTargetPlanetID(int targetPlanetID) {

        this.targetPlanetID = targetPlanetID;
    }

    public int getColonizationID() {

        return colonizationID;
    }

    public void setColonizationID(int colonizationID) {

        this.colonizationID = colonizationID;
    }

    public short getProgress() {

        return progress;
    }

    public void setProgress(short progress) {

        this.progress = progress;
    }


    public void beginColonization(int targetPlanetID, int unitID){


    }

    public void endColonization(){


    }

    public void runCombat(planet p, int attackerID, int defenderID) {
        System.out.println("Combat started on planet: " + p.planetID);

        // zmiana statusu planety
        p.status = "combat";

        List<unit> attackingUnits = p.getUnitsForCivilization(attackerID);
        List<unit> defendingUnits = p.getUnitsForCivilization(defenderID);

        while (!attackingUnits.isEmpty() && !defendingUnits.isEmpty() && p.getPopulation() > 0) {

            // offence
            List<unit> tempDefenders = new ArrayList<>(defendingUnits);
            for (unit attacker : attackingUnits) {
                if (!tempDefenders.isEmpty()) {
                    int targetIndex = (int) (Math.random() * tempDefenders.size());
                    unit target = tempDefenders.get(targetIndex);
                    attacker.dealDamage(target);
                    if (target.getHealth()==0) {
                        defendingUnits.remove(target);
                        tempDefenders.remove(target);
                    }
                }
            }

            // defence
            List<unit> tempAttackers = new ArrayList<>(attackingUnits);
            for (unit defender : defendingUnits) {
                if (!tempAttackers.isEmpty()) {
                    int targetIndex = (int) (Math.random() * tempAttackers.size());
                    unit target = tempAttackers.get(targetIndex);
                    defender.dealDamage(target);
                    if (target.getHealth()==0) {
                        attackingUnits.remove(target);
                        tempAttackers.remove(target);
                    }
                }
            }

            // zmiana populacji
            p.alterPopulation();
        }

        // koniec
        if (defendingUnits.isEmpty() || p.getPopulation() <= 0) {
            System.out.println("Attacker wins!");
            p.changeOwner(attackerID);
        } else if (attackingUnits.isEmpty()) {
            System.out.println("Defender wins!");
        }

        System.out.println("Combat ended on planet: " + p.planetID);
    }

}
