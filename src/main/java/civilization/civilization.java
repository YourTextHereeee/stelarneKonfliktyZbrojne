package civilization;

import mapTools.map;
import mapTools.simulation;
import mapTools.planet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import units.*;

import services.*;

public class civilization {

    private int civID;
    private String name;
    private float saturation;
    private int combatCooldown;

    private List<planet> ownedPlanets;
    private List<unit> ownedUnits;

    public civilization(int civID) {
        this.civID = civID;
        this.ownedPlanets = new ArrayList<>();
        this.ownedUnits = new ArrayList<>();
        this.combatCooldown = 10;
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

    public void colonize(int targetPlanetID, int startPlanetID) {

        System.out.println(civID + " is colonizing a planet" + targetPlanetID);
        int transporterID = 0;

        for(unit ship: this.ownedUnits){
            if (ship instanceof transporter) { transporterID = ship.getUnitID(); }
        }

        logistics.beginJourney(transporterID, targetPlanetID, startPlanetID);
        colonization.beginColonization(targetPlanetID, transporterID, this.civID);
    }

    public void attack(planet targetPlanet, int attackerID, int defenderID) {

        //System.out.println(name + " is attacking unit " + target.getUnitID());
        if(targetPlanet.combatCooldown > 0){
            System.out.println("planet on cooldown");
            return;
        }
        new combat(targetPlanet, attackerID, defenderID);
    }

    public void moveUnitsToCombat() {

        // ACHTUNG zrobić funkcję wysyłania frajerów na śmierć na froncie

        float distance = Float.MAX_VALUE;
        combat closestCombat = null;
        int startPlanetID = 0;

        for (unit unit : this.ownedUnits){
            if (!(unit instanceof turret)){

                for (combat combat : map.combatQueue){
                    if (map.getDistanceMap(unit.getXCoords(), unit.getYCoords(), combat.getPlanet().xcoords, combat.getPlanet().ycoords) < distance && (combat.getAttackerID() == this.civID || combat.getDefenderID() == this.civID)){
                        distance = map.getDistanceMap(unit.getXCoords(), unit.getYCoords(), combat.getPlanet().xcoords, combat.getPlanet().ycoords);
                        closestCombat = combat;
                    }
                }

                for (planet planet : this.ownedPlanets) {
                    if (planet.xcoords == unit.getXCoords() && planet.ycoords == unit.getYCoords()) {
                        startPlanetID = planet.planetID;
                    }
                }

                logistics.beginJourney(unit.getUnitID(), closestCombat.getPlanet().planetID, startPlanetID);



            }
        }
    }

    public void makeDecision() {

        this.saturation = (float) this.getOwnedUnits().size() / this.getOwnedPlanets().size();

        if (this.saturation >= 3){

            Random rng = new Random(simulation.seed);
            //planet ownedPlanet = map.planets.get(rng.nextInt(map.planets.size()));
            planet ownedPlanet = this.ownedPlanets.get(rng.nextInt(this.ownedPlanets.size()));
            System.out.println("1TO:::::::::::::::::::::::::::::" + this.ownedPlanets);

            planet targetPlanet = null;
            float bestDist = Float.MAX_VALUE;

            for (planet candidate : map.planets) {

                if (candidate.owner == this.civID){
                    continue;
                }
                float dist = map.getDistancePlanet(candidate.planetID, ownedPlanet.planetID);

                if (dist < bestDist) {
                    bestDist = dist;
                    targetPlanet = candidate;
                }
            }

            assert targetPlanet != null;
            System.out.println("TA:::---------" +  targetPlanet);
            if(targetPlanet.owner == 0){
                this.colonize(targetPlanet.planetID, ownedPlanet.planetID);
            } else if (!targetPlanet.status.equals("combat") ) {
                this.attack(targetPlanet, this.civID, targetPlanet.owner);
                System.out.println("attacker:" + this.civID + "targetplanetownerid:" + targetPlanet.owner);
            }
        }
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
