package services;

import civilization.civilization;
import mapTools.map;
import units.*;
import java.util.ArrayList;
import java.util.List;

public class logistics {

    private int unitID;
    private int targetPlanetID;
    private int startPlanetID;
    private float movementCredit;
    private int ETA;
    private float vectorX;
    private float vectorY;


    // konstruktor
    public logistics(int unitID, int targetPlanetID, int startPlanetID) {
        this.unitID = unitID;
        this.targetPlanetID = targetPlanetID;
        this.startPlanetID = startPlanetID;
        this.movementCredit = 0;
        this.ETA = (int) (map.getDistancePlanet(targetPlanetID, startPlanetID)/((ship) map.getUnitById(unitID)).getSpeed());
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

    public float getMovementCredit() {
        return movementCredit;
    }

    public void setMovementCredit(float movementCredit) {
        this.movementCredit = movementCredit;
    }


    public static void beginJourney(int unitID, int targetPlanetID, int startPlanetID) {

        logistics logistics1 = new logistics(unitID, targetPlanetID, startPlanetID);
        map.logiQueue.add(logistics1);

        //if (Math.abs(map.getPlanetById(targetPlanetID).xcoords - map.getPlanetById(startPlanetID).xcoords) >= Math.abs(map.getPlanetById(targetPlanetID).ycoords - map.getPlanetById(startPlanetID).ycoords)){
        //} else {
        //}
    }

    public void finishJourney(){

    }

    public void moveUnit(){

        if(map.getDistanceMap(map.getPlanetById(this.targetPlanetID).xcoords, map.getPlanetById(this.targetPlanetID).ycoords, ((ship) map.getUnitById(unitID)).getXcoords(), ((ship) map.getUnitById(unitID)).getYcoords()) > ((ship)map.getUnitById(this.unitID)).getSpeed()){

            ((ship) map.getUnitById(this.unitID)).setXcoords( ((ship) map.getUnitById(this.unitID)).getXcoords() + (vectorX * ((ship) map.getUnitById(this.unitID)).getSpeed()) );
            ((ship) map.getUnitById(this.unitID)).setYcoords( ((ship) map.getUnitById(this.unitID)).getYcoords() + (vectorY * ((ship) map.getUnitById(this.unitID)).getSpeed()) );
        } else {


        }

    }
}
