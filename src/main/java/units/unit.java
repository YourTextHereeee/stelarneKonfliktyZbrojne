package units;

public abstract class unit {

    private int unitID;
    private int owner;

    // konstruktor
    public unit(int unitID, int owner) {
        this.unitID = unitID;
        this.owner = owner;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public void takeDamage(int damage){
        throw new UnsupportedOperationException();
    }

    public void dealDamage(unit unit) {
        throw new UnsupportedOperationException();
    }

    public boolean hasHealth(){
        return true;
    }

    public int getHealth(){
        throw new UnsupportedOperationException();
    }

    public int getXCoords(){
        throw new UnsupportedOperationException();
    }

    public int getYCoords(){
        throw new UnsupportedOperationException();
    }

    public int getTurretPlanetID(){
        throw new UnsupportedOperationException();
    }

    // metoda do łatwego printowania
    @Override
    public String toString() {
        return "Unit{" +
                "unitID=" + unitID +
                ", owner=" + owner +
                '}';
    }
}