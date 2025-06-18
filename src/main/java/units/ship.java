package units;

/**
 * Klasa reprezentująca jednostki mobilne - statki. Statki mają
 * swoje koordynaty i prędkość, wykorzystywane przy przemieszczaniu
 * się
 */
public class ship extends unit {
    private int xcoords;
    private int ycoords;
    private int speed;
    private String status;
    // idle, flying, fighting, colonizing

    /**
     * Konstruktor dla ship
     *
     * @param unitID
     * @param owner
     * @param xcoords
     * @param ycoords
     * @param speed
     * @param status
     */
    public ship(int unitID, int owner, int xcoords, int ycoords, int speed, String status) {
        super(unitID, owner);
        this.xcoords = xcoords;
        this.ycoords = ycoords;
        this.speed = speed;
        this.status = status;
    }

    @Override
    public int getXCoords() {
        return xcoords;
    }

    public void setXcoords(float xcoords) {
        this.xcoords = (int) xcoords;
    }

    public int getYCoords() {
        return ycoords;
    }

    public void setYcoords(float ycoords) {
        this.ycoords = (int) ycoords;
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