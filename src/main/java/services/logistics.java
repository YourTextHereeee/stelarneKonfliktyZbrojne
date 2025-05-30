package services;

public class logistics {

    private int unitID;
    private int targetPlanetID;
    private int startPlanetID;
    private float movementCredit;

    // konstruktor
    public logistics(int unitID, int targetPlanetID, int startPlanetID, float movementCredit) {
        this.unitID = unitID;
        this.targetPlanetID = targetPlanetID;
        this.startPlanetID = startPlanetID;
        this.movementCredit = movementCredit;
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


    public void beginJourney(){

    }

    public void finishJourney(){

    }

    public void moveUnit(){

    }
}
