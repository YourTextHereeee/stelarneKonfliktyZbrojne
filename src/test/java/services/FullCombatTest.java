package services;

import civilization.civilization;
import mapTools.*;
import org.junit.jupiter.api.Test;
import units.*;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class FullCombatTest {

    @Test
    void combatIntegrationTest_shouldSimulateCombatToEnd() {
        map.planets.clear();
        map.units.clear();
        map.civs.clear();
        map.combatQueue.clear();

        planet p = new planet();
        p.planetID = 1;
        p.size = 3;
        p.population = 100;
        p.owner = 2;
        p.status = "idle";
        p.xcoords = 5;
        p.ycoords = 5;
        map.planets.add(p);

        planet startPlanet = new planet();
        startPlanet.xcoords = 2;
        startPlanet.ycoords = 2;
        startPlanet.size = 2;
        startPlanet.owner = 1;
        startPlanet.population = 100;
        startPlanet.status = "idle";

        map.planets.add(startPlanet);

        civilization attacker = new civilization(1);
        unit attackerUnit = new largeFighter(unit.generateID(), 1, 5, 5, 10, "idle");
        attacker.getOwnedUnits().add(attackerUnit);
        map.units.add(attackerUnit);
        map.civs.add(attacker);

        civilization defender = new civilization(2);
        unit defenderUnit = new largeFighter(unit.generateID(), 2, 5, 5, 10, "idle");
        defender.getOwnedUnits().add(defenderUnit);
        map.units.add(defenderUnit);
        map.civs.add(defender);

        defender.getOwnedPlanets().add(p);

        combat c = new combat(p, 1, 2);
        map.combatQueue.add(c);



            int maxTurns = 100; // zapobiega nieskończonej pętli
            int turns = 0;

            while (turns < maxTurns && !map.combatQueue.isEmpty()) {
                Iterator<combat> it = map.combatQueue.iterator();
                if (it.hasNext()) {
                    combat combatInstance = it.next();
                    combatInstance.progressCombat(it);
                }
                turns++;
            }


        assertTrue(p.owner == 1 || p.owner == 2, "owner");
        assertTrue(p.status.equals("idle") || p.status.equals("producing"), "status");
        assertTrue(p.combatCooldown > 0, "cooldown");
    }

}
