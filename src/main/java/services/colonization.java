package services;

import mapTools.map;
import mapTools.planet;
import units.*;

import java.util.Iterator;

public class colonization {

    private int targetPlanetID;
    private int unitID;
    private int colonizerID;
    private short progress;

    //konstruktor
    public colonization(int targetPlanetID, int unitID, int colonizerID) {
        this.targetPlanetID = targetPlanetID;
        this.unitID = unitID;
        this.colonizerID = colonizerID;
        this.progress = 0;
    }

    public int getTargetPlanetID() {
        return targetPlanetID;
    }

    public void setTargetPlanetID(int targetPlanetID) {
        this.targetPlanetID = targetPlanetID;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int colonizationID) {
        this.unitID = colonizationID;
    }

    public short getProgress() {
        return progress;
    }

    public void setProgress(short progress) {
        this.progress = progress;
    }


    public static void beginColonization(int targetPlanetID, int unitID, int colonizerID) {

        System.out.println("AAAAAAAAA" + map.getPlanetById(targetPlanetID).colonizationCooldown);

        if (map.getPlanetById(targetPlanetID).colonizationCooldown > 0) {
            System.out.println("col cooldown exitsts");
            return;
        }

        colonization colonization1 = new colonization(targetPlanetID, unitID, colonizerID);
        map.colonizationQueue.add(colonization1);
    }

    public void finishColonization(Iterator<colonization> ITE2){

        ITE2.remove();
        ((ship) map.getUnitById(unitID)).setStatus("idle");
        map.getPlanetById(targetPlanetID).status = "producing";
        map.getPlanetById(targetPlanetID).colonizationCooldown = 10;
    }

    public void progressColonization(Iterator<colonization> ITE2) {

        ship ship = (ship) map.getUnitById(this.unitID);
        planet planet = (planet) map.getPlanetById(this.targetPlanetID);

        if (ship.getStatus() == "idle"){

            ship.setStatus("colonizing");
            planet.owner = this.colonizerID;
        }

        if (ship.getStatus() == "colonizing") {
            if (this.progress < planet.size + 5) {
                this.progress++;
            } else {
                planet.setPopulation(planet.size * 100);
                this.finishColonization(ITE2);
            }
        }
    }
}
