package services;

import mapTools.*;
import units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogisticsTest {

    @Test
    void constructor_shouldCalculateETAAndVectors() {
        // Przygotuj planety na mapie
        planet start = new planet();
        start.planetID = 1; start.xcoords = 0; start.ycoords = 0;
        planet target = new planet();
        target.planetID = 2; target.xcoords = 3; target.ycoords = 4;
        map.planets.clear();
        map.planets.add(start);
        map.planets.add(target);

        // Przygotuj statek o prędkości 1
        ship ship = new transporter(100, 1, 0, 0, 1, "idle");
        map.units.clear();
        map.units.add(ship);

        logistics logi = new logistics(ship.getUnitID(), target.planetID, start.planetID);

        // Odległość 5 (3-0,4-0) więc ETA = 5/1 = 5
        assertEquals(5, logi.getETA());

        // Wektory powinny być (3/5, 4/5)
        assertEquals(0.6f, logi.getVectorX(), 0.0001);
        assertEquals(0.8f, logi.getVectorY(), 0.0001);
    }

}
