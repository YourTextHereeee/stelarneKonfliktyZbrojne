package mapTools;

import civilization.civilization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import units.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MapTest {

    @BeforeEach
    void setUp() {
        map.planets.clear();
        map.units.clear();
        map.civs.clear();
    }

    @Test
    void getUnitById() {
        unit u = new smallFighter(1, 1, 0, 0, 5, "idle");
        map.units.add(u);

        unit found = map.getUnitById(1);

        assertNotNull(found);
        assertEquals(1, found.getUnitID());
    }

    @Test
    void getPlanetById() {
        planet p = new planet();
        p.status = "idle";
        p.size = 2;
        p.population = 1000;
        p.owner = 1;
        map.planets.add(p);

        planet found = map.getPlanetById(0);

        assertNotNull(found);
        assertEquals(0, found.planetID);
    }

    @Test
    void getCivilizationById() {
        civilization c = new civilization(5);
        map.civs.add(c);

        civilization found = map.getCivilizationById(5);

        assertNotNull(found);
        assertEquals(5, found.getCivID());
    }

    @Test
    void getDistancePlanet() {
        planet p1 = new planet();
        p1.planetID = 1;
        p1.xcoords = 0;
        p1.ycoords = 0;

        planet p2 = new planet();
        p2.planetID = 2;
        p2.xcoords = 3;
        p2.ycoords = 4;

        map.planets.add(p1);
        map.planets.add(p2);

        float distance = map.getDistancePlanet(2, 1);

        assertEquals(5.f, distance, 0.001, "powinno byc 5.0");
    }

    @Test
    void getDistanceMap_diffPoints() {
        float distance = map.getDistanceMap(1f, 2f, 4f, 6f);
        assertEquals(5.0f, distance, 0.001, "Odległość między tymi samymi punktami powinna wynosić 0");
    }

    @Test
    void getDistanceMap_samePoints() {
        float distance = map.getDistanceMap(3f, 3f, 3f, 3f);
        assertEquals(0.0f, distance, 0.001, "Odległość między tymi samymi punktami powinna wynosić 0");
    }

    @Test
    void getDistanceMap_negativeCoordinates() {
        float distance = map.getDistanceMap(-1f, -1f, 2f, 3f); // sqrt(9 + 16) = 5.0
        assertEquals(5.0f, distance, 0.001, "Odległość z ujemnymi współrzędnymi powinna być poprawna");
    }

}
