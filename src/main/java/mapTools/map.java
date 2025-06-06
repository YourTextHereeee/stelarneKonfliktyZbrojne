package mapTools;
import civilization.civilization;
import units.unit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class map {

    public static List<planet> planets = new ArrayList<>();
    public static List<unit> units = new ArrayList<>();
    public static List<civilization> civs = new ArrayList<>();

    public static int xWidth;
    public static int yHeight;


    public static unit getUnitById(int id) {
        for (unit u : units) {
            if (u.getUnitID() == id) return u;
        }
        return null;
    }

    public static float getDistancePlanet(int targetPlanetID, int startPlanetID){

        return 0;
    }

    public static float getDistanceMap(int targetX, int targetY, int startX, int startY){

        return 0;
    }

    public static void generateMap(int planetCount, int civCount){

        Random rng = new Random(simulation.seed);
        int x, y;
        boolean spaceOccupied;
        boolean planetOccupied;

        for (int i = 0; i < planetCount; i++) {

            do {
                x = rng.nextInt(map.xWidth);
                y = rng.nextInt(map.yHeight);

                spaceOccupied = false;

                for (planet planet : planets) {
                    if (planet.xcoords == x && planet.ycoords == y) {
                        spaceOccupied = true;
                        break;
                    }
                }
            } while (spaceOccupied);

            planet planet = new planet();

            planet.planetID = i;
            planet.size = (rng.nextInt(5) + 1);

            planet.owner = 0;
            planet.population = 0;
            planet.status = "idle";

            planet.xcoords = x;
            planet.ycoords = y;

            planet.carbon = rng.nextBoolean();
            planet.silicon = rng.nextBoolean();
            planet.metal = rng.nextBoolean();

            map.planets.add(planet);
        }

        for (int i = 1; i <= civCount; i++) {

            planet startPlanet;
            do {
                startPlanet = map.planets.get(rng.nextInt(planets.size()));

                planetOccupied = false;

                if (startPlanet.owner != 0) {
                    planetOccupied = true;
                }

            } while (planetOccupied);

            civilization civ = new civilization(i);
            civ.getOwnedPlanets().add(startPlanet);

            startPlanet.owner = i;
            startPlanet.population = startPlanet.size * 1000;
            startPlanet.status = "producing";

            startPlanet.carbon = true;
            startPlanet.silicon = true;
            startPlanet.metal = true;

            map.civs.add(civ);
        }
    }
}
