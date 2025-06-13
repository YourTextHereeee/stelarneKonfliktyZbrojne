package services;
import mapTools.planet;
import mapTools.simulation;
import units.unit;

import java.util.List;
import java.util.ArrayList;

public class combat {

    public static void beginCombat(){}

    public static void finishCombat(){}

    public static void progressCombat(){}

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
