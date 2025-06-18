package services;

import civilization.civilization;
import mapTools.*;
import units.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CombatTest {

    @BeforeEach
    void setUp() {
        map.planets.clear();
        map.units.clear();
        map.civs.clear();
    }

    @Test
    void constructor_shouldStartCombatAndSetStatus() {
        planet p = new planet();
        p.planetID = 1;
        p.status = "idle";

        map.combatQueue.clear();

        combat c = new combat(p, 1, 2);

        assertEquals("combat", p.status);
        assertTrue(map.combatQueue.contains(c));
    }

    @Test
    void beginCombat_shouldSetPlanetStatusToCombat() {
        planet p = new planet();
        p.status = "idle";

        combat c = new combat(p, 1, 2);
        c.beginCombat();

        assertEquals("combat", p.status);
    }

    @Test
    void progressCombat_shouldResolveOneCombatRound() {
        planet p = new planet();
        p.planetID = 1;
        p.population = 100;
        p.status = "idle";
        p.xcoords = 0;
        p.ycoords = 0;
        p.owner = 2;

        unit attacker = new transporter(1, 1, 0, 0, 5, "idle");
        unit defender = new transporter(2, 2, 0, 0, 5, "idle");

        planet startPlanet = new planet();
        startPlanet.xcoords = 2;
        startPlanet.ycoords = 2;
        startPlanet.size = 2;
        startPlanet.owner = 1;
        startPlanet.population = 100;
        startPlanet.status = "idle";

        map.planets.add(startPlanet);

        map.planets.add(p);
        map.units.add(attacker);
        map.units.add(defender);

        civilization civ1 = new civilization(1);
        civ1.getOwnedUnits().add(attacker);
        civilization civ2 = new civilization(2);
        civ2.getOwnedUnits().add(defender);

        map.civs.add(civ1);
        map.civs.add(civ2);

        p.owner = 2;
        p.setPopulation(100);

        map.units.clear();
        map.units.add(attacker);
        map.units.add(defender);

        combat c = new combat(p, 1, 2);

        Iterator<combat> ite = List.of(c).iterator();

        // Tura bitwy
        c.progressCombat(ite);

        // Sprawdź, czy status planety nadal "combat"
        assertEquals("combat", p.status);
    }

    @Test
    void progressCombat_shouldEndCombatWithDefenderVictory() {
        planet p = new planet();
        p.planetID = 1;
        p.population = 100;
        p.xcoords = 0;
        p.ycoords = 0;
        p.owner = 2;

        unit defender = new transporter(2, 2, 0, 0, 5, "idle");

        map.units.clear();
        map.civs.clear();
        map.planets.clear();

        map.planets.add(p);
        map.units.add(defender);

        civilization civ2 = new civilization(2);
        civ2.getOwnedUnits().add(defender);
        map.civs.add(civ2);
        civilization civ1 = new civilization(1);
        map.civs.add(civ1);

        p.owner = 2;
        p.setPopulation(100);

        planet startPlanet = new planet();
        startPlanet.xcoords = 2;
        startPlanet.ycoords = 2;
        startPlanet.size = 2;
        startPlanet.owner = 1;
        startPlanet.population = 100;
        startPlanet.status = "idle";

        map.planets.add(startPlanet);


        combat c = new combat(p, 1, 2);

        // Brak atakujących jednostek
        Iterator<combat> ite = new ArrayList<>(List.of(c)).iterator();

        ite.next();
        c.progressCombat(ite);

        assertEquals(2, p.owner);
    }

    @Test
    void finishCombat_shouldEndWithAttackerVictoryAndColonization() {
        planet p = new planet();
        p.planetID = 1;
        p.population = 0;
        p.owner = 2;

        transporter attackerUnit = new transporter(1, 1, 0, 0, 5, "idle");

        map.units.clear();
        map.civs.clear();
        map.planets.clear();

        map.planets.add(p);
        map.units.add(attackerUnit);

        civilization civ1 = new civilization(1);
        civ1.getOwnedUnits().add(attackerUnit);

        planet planetOwned = new planet();
        planetOwned.planetID = 99;
        planetOwned.xcoords = attackerUnit.getXCoords();
        planetOwned.ycoords = attackerUnit.getYCoords();
        civ1.getOwnedPlanets().add(planetOwned);

        map.planets.add(planetOwned);
        map.civs.add(civ1);

        map.units.clear();
        map.units.add(attackerUnit);

        civilization civ2 = new civilization(2);
        map.civs.add(civ2);

        planet startPlanet = new planet();
        startPlanet.xcoords = 2;
        startPlanet.ycoords = 2;
        startPlanet.size = 2;
        startPlanet.owner = 1;
        startPlanet.population = 100;
        startPlanet.status = "idle";

        map.planets.add(startPlanet);

        combat c = new combat(p, 1, 2);
        Iterator<combat> ite = new ArrayList<>(List.of(c)).iterator();
        ite.next();


        c.progressCombat(ite); // zainicjalizuje attackingUnits i defendingUnits
        c.finishCombat(ite);

        assertEquals(1, p.owner);
        assertTrue(p.status.equals("idle") || p.status.equals("producing"));
        assertEquals(10, p.combatCooldown);
    }


}
