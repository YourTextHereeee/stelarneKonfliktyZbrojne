package services;

import mapTools.map;
import mapTools.planet;
import units.*;

import java.util.Iterator;

public class logistics {

    private int unitID;
    private int targetPlanetID;
    private int startPlanetID;
    //private float movementCredit;
    private int ETA;
    private float vectorX;
    private float vectorY;


    // konstruktor
    public logistics(int unitID, int targetPlanetID, int startPlanetID) {
        this.unitID = unitID;
        this.targetPlanetID = targetPlanetID;
        this.startPlanetID = startPlanetID;
        //this.movementCredit = 0;
        //System.out.println(map.getUnitById(unitID).getClass().getSimpleName());
        //this.ETA = (int) (map.getDistancePlanet(targetPlanetID, startPlanetID)/((ship) map.getUnitById(unitID)).getSpeed());
        this.vectorX = (map.getPlanetById(targetPlanetID).xcoords - map.getPlanetById(startPlanetID).xcoords) / map.getDistancePlanet(targetPlanetID, startPlanetID);
        this.vectorY = (map.getPlanetById(targetPlanetID).ycoords - map.getPlanetById(startPlanetID).ycoords) / map.getDistancePlanet(targetPlanetID, startPlanetID);
    }


    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public int getTargetPlanetID() {
        return targetPlanetID;
    }

    public void setTargetPlanetID(int targetPlanetID) {
        this.targetPlanetID = targetPlanetID;
    }

    public int getStartPlanetID() {
        return startPlanetID;
    }

    public void setStartPlanetID(int startPlanetID) {
        this.startPlanetID = startPlanetID;
    }

    //public float getMovementCredit() {
    //    return movementCredit;
    //}

    //public void setMovementCredit(float movementCredit) {
    //    this.movementCredit = movementCredit;
    //}

    public static void beginJourney(int unitID, int targetPlanetID, int startPlanetID) {

        if (map.getDistancePlanet(targetPlanetID, startPlanetID) == 0){
            return;
        }

        logistics logistics1 = new logistics(unitID, targetPlanetID, startPlanetID);
        map.logiQueue.add(logistics1);
        ((ship) map.getUnitById(unitID)).setStatus("flying");

    }

    public void finishJourney(Iterator<logistics> ITE){

        ITE.remove();
        ((ship) map.getUnitById(unitID)).setStatus("idle");
    }

    public void moveUnit(Iterator<logistics> ITE){

        planet targetPlanet = map.getPlanetById(this.targetPlanetID);
        ship ship = (ship) map.getUnitById(this.unitID);

        if(ship == null) {
            return;
        }

        if(map.getDistanceMap(targetPlanet.xcoords, targetPlanet.ycoords, ship.getXCoords(), ship.getYCoords()) > ship.getSpeed()){

            ship.setXcoords( ship.getXCoords() + (vectorX * ship.getSpeed()) );
            ship.setYcoords( ship.getYCoords() + (vectorY * ship.getSpeed()) );
        } else {

            ship.setXcoords(targetPlanet.xcoords);
            ship.setYcoords(targetPlanet.ycoords);
            this.finishJourney(ITE);
        }

    }
}
