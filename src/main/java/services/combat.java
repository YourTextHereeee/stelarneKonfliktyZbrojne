package services;
import mapTools.map;
import mapTools.planet;
import units.transporter;
import units.unit;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class combat {

    private final planet p;
    private final int attackerID;
    private final int defenderID;
    private List<unit> attackingUnits;
    private List<unit> defendingUnits;
    private int cooldown;

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

    public void beginCombat(){

        System.out.println("Combat started on planet: " + p.planetID);

        // zmiana statusu planety
        p.status = "combat";
    }

    public void finishCombat(Iterator<combat> ITE3){
        if (defendingUnits.isEmpty() || p.getPopulation() <= 0) {
            System.out.println("Attacker wins!");
            p.changeOwner(attackerID);

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
            } else {
                p.status = "producing";
            }

        } else if (attackingUnits.isEmpty()) {
            System.out.println("Defender wins!");
        }

        System.out.println("Combat ended on planet: " + p.planetID);
        ITE3.remove();
        p.combatCooldown = 10;

    }

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
