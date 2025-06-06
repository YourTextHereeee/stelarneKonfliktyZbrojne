package civilization;

import units.unit;
import mapTools.planet;
import java.util.ArrayList;
import java.util.List;

public class civilization {

    private int civID;
    private String name;

    private List<planet> ownedPlanets;
    private List<unit> ownedUnits;

    public civilization(int civID) {
        this.civID = civID;
        this.ownedPlanets = new ArrayList<>();
        this.ownedUnits = new ArrayList<>();
    }

    public int getCivID() {
        return civID;
    }

    public void setCivID(int civID) {
        this.civID = civID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<planet> getOwnedPlanets() {
        return ownedPlanets;
    }

    public void addPlanet(planet p) {
        ownedPlanets.add(p);
    }

    public void removePlanet(planet p) {
        ownedPlanets.remove(p);
    }

    public List<unit> getOwnedUnits() {
        return ownedUnits;
    }

    public void addUnit(unit u) {
        ownedUnits.add(u);
    }

    public void removeUnit(unit u) {
        ownedUnits.remove(u);
    }

    public void colonize() {

        System.out.println(name + " is colonizing a planet");
    }

    public void attack(unit target) {

        System.out.println(name + " is attacking unit " + target.getUnitID());

    }

    public void defend() {

        System.out.println(name + " is defending");

    }

    public void makeDecision() {

    }

    @Override
    public String toString() {
        return "civilization{" +
                "civID=" + civID +
                ", name='" + name + '\'' +
                ", ownedPlanets=" + ownedPlanets.size() +
                ", ownedUnits=" + ownedUnits.size() +
                '}';
    }
}
