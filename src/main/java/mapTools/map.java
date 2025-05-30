package mapTools;
import units.unit;
import java.util.ArrayList;
import java.util.List;

public class map {

    public static List<unit> planets = new ArrayList<>();
    public static List<unit> units = new ArrayList<>();

    public int xWidth;
    public int yHeight;


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

    public static void generateMap(){


    }
}
