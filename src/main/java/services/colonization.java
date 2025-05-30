package services;

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
}
