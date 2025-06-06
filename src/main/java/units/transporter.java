package units;

public class transporter extends ship {
    private boolean loaded = false;

    public transporter(int unitID, int owner, int xcoords, int ycoords, int speed, String status) {
        super(unitID, owner, xcoords, ycoords, speed, status);
        this.loaded = false;
    }

    public void loadPeople() {
        this.loaded = true;
        //System.out.println("People loaded onto transporter ship.");
    }

    public void unloadPeople() {
        this.loaded = false;
        //System.out.println("People unloaded from transporter ship.");
    }

    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public String toString() {
        return "TransporterShip{" +
                "unitID=" + getUnitID() +
                ", owner=" + getOwner() +
                ", xcoords=" + getXcoords() +
                ", ycoords=" + getYcoords() +
                ", speed=" + getSpeed() +
                ", status='" + getStatus() + '\'' +
                ", loaded=" + loaded +
                '}';
    }
}