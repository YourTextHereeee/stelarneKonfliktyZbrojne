package services;

import mapTools.map;
import mapTools.planet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import units.transporter;

import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ColonizationTest {

    @BeforeEach
    void setup() {
        map.colonizationQueue.clear();
        map.planets.clear();
        map.units.clear();
    }

    @Test
    void beginColonization_shouldAddToQueue_whenNoCooldown() {
        planet p = new planet();
        p.planetID = 1;
        p.colonizationCooldown = 0;
        map.planets.add(p);

        transporter t = new transporter(100, 1, 0, 0, 5, "idle");
        map.units.add(t);

        colonization.beginColonization(1, 100, 1);

        assertEquals(1, map.colonizationQueue.size());
        assertEquals(1, map.colonizationQueue.get(0).getTargetPlanetID());
    }

    @Test
    void beginColonization_shouldNotAddToQueue_whenCooldownActive() {
        planet p = new planet();
        p.planetID = 1;
        p.colonizationCooldown = 5;
        map.planets.add(p);

        colonization.beginColonization(1, 100, 1);

        assertEquals(0, map.colonizationQueue.size(), "nie powinno dodac, bo cooldown > 0");
    }

    @Test
    void progressColonization() {

        planet p = new planet();
        p.planetID = 10;
        p.size = 2;
        map.planets.add(p);

        transporter t = new transporter(42, 1, 0, 0, 5, "idle");
        map.units.add(t);

        colonization col = new colonization(10, 42, 99);
        map.colonizationQueue.add(col);

        Iterator<colonization> iterator = map.colonizationQueue.iterator();
        colonization c = iterator.next();

        // 1. zmiana statusu i wlasciciel
        c.progressColonization(iterator);
        assertEquals("colonizing", t.getStatus());
        assertEquals(99, p.owner);
        assertEquals(0, c.getProgress(), "powinnen byc 0 chyba, bo dopiero zmienil status");

        // 2. zwiekszenie progressu
        c.progressColonization(iterator);
        assertEquals(1, c.getProgress());
    }

    @Test
    void finishColonization_shouldUpdateMapAndState() {
        planet p = new planet();
        p.planetID = 10;
        p.size = 2;
        map.planets.add(p);

        transporter t = new transporter(42, 1, 0, 0, 5, "idle");
        map.units.add(t);

        colonization col = new colonization(10, 42, 99);
        map.colonizationQueue.add(col);

        col.setProgress((short) (p.size + 5));  // gotowe do zakończenia

        LinkedList<colonization> list = new LinkedList<>();
        list.add(col);
        Iterator<colonization> iterator = list.iterator();

        colonization c = iterator.next();
        c.finishColonization(iterator);

        assertEquals("idle", t.getStatus());
        assertEquals("producing", p.status);
        assertEquals(10, p.colonizationCooldown);
        assertEquals(p.size * 100, p.getPopulation(), "powinno ustawic populacje");
        assertFalse(list.contains(c), "powinno usunac z listy");
    }


}

