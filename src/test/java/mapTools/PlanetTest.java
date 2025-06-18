package mapTools;

import civilization.civilization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import units.*;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

    @BeforeEach
    void setUp() {
        map.planets.clear();
        map.units.clear();
        map.civs.clear();
    }

    @Test
    void alterPopulation_combatMode_decreasesPopulation() {
        planet p = new planet();
        p.status = "combat";
        p.size = 5;
        p.population = 1000;

        p.alterPopulation();

        assertEquals(900, p.getPopulation());
    }

    @Test
    void alterPopulation_normalMode_increasesPopulationMax1000xSize() {
        planet p = new planet();
        p.status = "idle";
        p.size = 2;
        p.population = 1900; // max 2000

        p.alterPopulation();

        assertEquals(2000, p.getPopulation());
    }

    @Test
    void changePopulation(){
        planet p = new planet();
        p.status = "idle";
        p.size = 2;
        p.population = 1000;

        p.setPopulation(2000);

        assertEquals(2000, p.getPopulation());
    }

    @Test
    void getUnits(){
        planet p = new planet();
        p.status = "idle";
        p.size = 2;
        p.population = 1000;
        p.owner = 1;
        p.xcoords = 5;
        p.ycoords = 5;
        largeFighter lf1 = new largeFighter(0, 1, 5, 5, 10, "idle");
        largeFighter lf2 = new largeFighter(0, 1, 7, 5, 10, "idle");
        largeFighter lf3 = new largeFighter(0, 1, 7, 3, 10, "idle");
        largeFighter lf4 = new largeFighter(0, 1, 5, 5, 10, "idle");
        transporter t1 = new transporter(0, 1, 5, 5, 10, "idle");
        lf4.takeDamage(500);
        map.units.add(lf1);
        map.units.add(lf2);
        map.units.add(lf3);
        map.units.add(lf4);
        map.units.add(t1);

        List<unit> result = p.getUnitsForCivilization(1);

        assertEquals(1, result.size());
        assertTrue(result.contains(lf1));
        assertFalse(result.contains(lf4));

    }
}

