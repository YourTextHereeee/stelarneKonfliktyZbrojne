package units;

public class unit {

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

    // metoda do łatwego printowania
    @Override
    public String toString() {
        return "Unit{" +
                "unitID=" + unitID +
                ", owner=" + owner +
                '}';
    }
}