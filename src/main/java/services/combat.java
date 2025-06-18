package services;

import mapTools.map;
import mapTools.planet;
import units.transporter;
import units.unit;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * Reprezentuje bitwę o konkretną planetę pomiędzy dwoma cywilizacjami
 *
 * Klasa odpowiada za przetwarzanie logiki bitwy, progresowanie jej i
 * zakańćzanie
 *
 * @see map
 * @see units
 */
public class combat {

    private final planet p;
    private final int attackerID;
    private final int defenderID;
    private List<unit> attackingUnits;
    private List<unit> defendingUnits;
    private int cooldown;

    /**
     * Konstruktor klasy
     *
     * @param p
     * @param attackerID
     * @param defenderID
     */
    public combat(planet p, int attackerID, int defenderID) {
        this.p = p;
        this.attackerID = attackerID;
        this.defenderID = defenderID;
        beginCombat();
        map.combatQueue.add(this);
        this.cooldown = 0;
    }

    public planet getPlanet() {return p;}
    public int getAttackerID() {return attackerID;}
    public int getDefenderID() {return defenderID;}

    /**
     * Rozpoczynanie bitwy
     */
    public void beginCombat(){

        System.out.println("Combat started on planet: " + p.planetID);

        // zmiana statusu planety
        p.status = "combat";
    }

    /**
     * Algorytm zakańczania bitwy
     * @param ITE3
     */
    public void finishCombat(Iterator<combat> ITE3){
        if (defendingUnits.isEmpty() || p.getPopulation() <= 0) {
            System.out.println("Attacker wins!");
            System.out.println("atcID:" + attackerID + " defID:" + defenderID);
            System.out.println(p.owner);
            p.changeOwner(attackerID);
            System.out.println(p.owner);
            System.out.println(p.xcoords);
            System.out.println(p.ycoords);

            // ZROBIONE - ACHTUNG zmiana statusu planety i rozpoczęcie kolonizacji przez attackera na ifie

            if (p.getPopulation() <= 0) {

                int transporterPlanetID;
                int transporterID = 0;
                unit attackerTransporter;

                for (unit ship : map.getCivilizationById(attackerID).getOwnedUnits()) {
                    if (ship instanceof transporter) {
                        transporterID = ship.getUnitID();
                    }
                }
                attackerTransporter = map.getUnitById(transporterID);
                for (planet planet : map.getCivilizationById(attackerID).getOwnedPlanets()) {
                    if (planet.xcoords == attackerTransporter.getXCoords() && planet.ycoords == attackerTransporter.getYCoords()) {
                        transporterPlanetID = planet.planetID;
                    }
                }
                map.getCivilizationById(attackerID).colonize(p.planetID, transporterID);
                p.status = "idle";
                p.owner = 0;
            } else {
                p.status = "producing";
                p.owner = attackerID;
            }

        } else if (attackingUnits.isEmpty()) {
            System.out.println("Defender wins!");
        }

        System.out.println("Combat ended on planet: " + p.planetID);
        ITE3.remove();
        p.combatCooldown = 10;

    }

    /**
     * Algorytm przetwarzania bitwy z generacji na generację - zadaje obrażenia,
     * zmienia populację, podejmuje decyzję czy bitwa może być kontynuowana
     * @param ITE3
     */
    public void progressCombat(Iterator<combat> ITE3){

        attackingUnits = p.getUnitsForCivilization(attackerID);
        defendingUnits = p.getUnitsForCivilization(defenderID);
        map.getCivilizationById(attackerID).moveUnitsToCombat();
        map.getCivilizationById(defenderID).moveUnitsToCombat();

        if (!attackingUnits.isEmpty() && !defendingUnits.isEmpty() && p.getPopulation() > 0) {

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
                        map.getCivilizationById(defenderID).getOwnedUnits().remove(target);
                        map.units.remove(target);
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
                        map.getCivilizationById(attackerID).getOwnedUnits().remove(target);
                        map.units.remove(target);
                    }
                }
            }

            // zmiana populacji
            //p.alterPopulation(); <- to już jest w pętli w simulation
        }
        else if (cooldown >= 10) {
            finishCombat(ITE3);
        }
        cooldown ++;
    }
}
