package units;

public class ship extends unit {
    private float xcoords;
    private float ycoords;
    private int speed;
    private String status;
    // idle, flying, fighting, colonizing

    public ship(int unitID, int owner, int xcoords, int ycoords, int speed, String status) {
        super(unitID, owner);
        this.xcoords = xcoords;
        this.ycoords = ycoords;
        this.speed = speed;
        this.status = status;
    }

    public float getXcoords() {
        return xcoords;
    }

    public void setXcoords(float xcoords) {
        this.xcoords = xcoords;
    }

    public float getYcoords() {
        return ycoords;
    }

    public void setYcoords(float ycoords) {
        this.ycoords = ycoords;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "unitID=" + getUnitID() +
                ", owner=" + getOwner() +
                ", xcoords=" + xcoords +
                ", ycoords=" + ycoords +
                ", speed=" + speed +
                ", status='" + status + '\'' +
                '}';
    }
}