package mapTools;
import civilization.civilization;
import services.logistics;
import units.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import services.*;

public class map {

    public static List<planet> planets = new ArrayList<>();
    public static List<unit> units = new ArrayList<>();
    public static List<civilization> civs = new ArrayList<>();
    public static List<logistics> logiQueue = new ArrayList<>();
    public static List<colonization> colonizationQueue = new ArrayList<>();
    public static List<combat> combatQueue = new ArrayList<>();

    public static int xWidth;
    public static int yHeight;

    static int [][] plan;


    public static unit getUnitById(int id) {
        for (unit u : units) {
            if (u.getUnitID() == id) return u;
        }
        return null;
    }

    public static planet getPlanetById(int id) {
        for (planet u : planets) {
            if (u.planetID == id) return u;
        }
        return null;
    }

    public static civilization getCivilizationById(int id) {
        for (civilization u : civs) {
            if (u.getCivID() == id) return u;
        }
        return null;
    }

    public static float getDistancePlanet(int targetPlanetID, int startPlanetID){

        planet targetPlanet = null;
        planet startPlanet = null;
        int differenceX;
        int differenceY;

        for (planet i : planets) {

            if (i.planetID == targetPlanetID) targetPlanet = i;
            if (i.planetID == startPlanetID) startPlanet = i;
        }

        differenceX = targetPlanet.xcoords - startPlanet.xcoords;
        differenceY = targetPlanet.ycoords - startPlanet.ycoords;

        return (float) Math.sqrt(differenceX*differenceX + differenceY*differenceY);
    }

    public static float getDistanceMap(float targetX, float targetY, float startX, float startY){

        return (float) Math.sqrt(Math.pow(targetX - startX, 2) + Math.pow(targetY - startY, 2));
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

            map.planets.add(planet);
        }

        for (int i = 1; i <= civCount; i++) {

            planet startPlanet;
            do {
                startPlanet = map.planets.get(rng.nextInt(planets.size()));

                planetOccupied = startPlanet.owner != 0;

            } while (planetOccupied);

            civilization civ = new civilization(i);
            civ.getOwnedPlanets().add(startPlanet);

            startPlanet.owner = i;
            startPlanet.population = startPlanet.size * 1000;
            startPlanet.status = "producing";

            transporter ts = new transporter(
                    map.units.size() + 1,         // unitID
                    civ.getCivID(),               // owner
                    startPlanet.xcoords,          // starting X
                    startPlanet.ycoords,          // starting Y
                    5,                            // speed
                    "idle"                        // status
            );

            civ.getOwnedUnits().add(ts);

            map.units.add(ts);

            map.civs.add(civ);
        }
    }

    public static void generateMapPreview(){
        plan = new int[xWidth][yHeight];
        for (planet pl1 : map.planets){
            plan[pl1.xcoords][pl1.ycoords] = pl1.owner+1;
        }
    }
    public static void showMap(){

        for(int i = 0; i < map.xWidth; i++){
            for(int j = 0; j < map.yHeight; j++){
                if (plan[i][j] == 0)
                    System.out.print(" ");
                else
                    System.out.print(plan[i][j]-1);
            }
            System.out.println();
        }
    }
}