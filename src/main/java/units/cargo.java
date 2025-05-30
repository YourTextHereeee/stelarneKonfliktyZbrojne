package units;

public class cargo extends ship {
    private String cargoType;

    public cargo(int unitID, int owner, int xcoords, int ycoords, int speed, String status) {
        super(unitID, owner, xcoords, ycoords, speed, status);
        this.cargoType = null;
    }

    public void loadCargo(String cargoType) {
        this.cargoType = cargoType;
        //System.out.println("Cargo loaded: " + cargoType);
    }

    public void unloadCargo() {
        //System.out.println("Cargo unloaded: " + cargoType);
        this.cargoType = null;
    }

    public String getCargoType() {
        return cargoType;
    }

    @Override
    public String toString() {
        return "CargoShip{" +
                "unitID=" + getUnitID() +
                ", owner=" + getOwner() +
                ", xcoords=" + getXcoords() +
                ", ycoords=" + getYcoords() +
                ", speed=" + getSpeed() +
                ", status='" + getStatus() + '\'' +
                ", cargoType='" + cargoType + '\'' +
                '}';
    }
}
