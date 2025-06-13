package mapTools;
import units.*;
import services.*;
import civilization.*;

import java.util.Iterator;
import java.util.Scanner;

public class simulation {

    public static long seed;
    public static long generation;

    //MAIN \/
    public static void main(String[] args) {

        System.out.print("kompilacja zakończona sukcesem :) \n\n");
        //simulation simulation = new simulation();


        //testowe kilka statków + wymiana obrażeń

        //to będzie zarządzanie przez planetę
//        largeFighter lf1 = new largeFighter(1, 0, 100, 100, 10, "active");
//        smallFighter sf1 = new smallFighter(2, 1, 100, 100, 12, "active");
//        turret t1 = new turret(3, 1, 1, 1);

//        map.units.add(lf1);
//        map.units.add(sf1);
//        map.units.add(t1);


        //to będzie zarządzanie przez combat
//        lf1.dealDamage(2);
//        lf1.dealDamage(3);
//        t1.dealDamage(1);
//        t1.dealDamage(lf1);
//        lf1.dealDamage(t1);
//        lf1.dealDamage(sf1);


//        System.out.println("SmallFighter " + sf1.getUnitID() + " health: " + sf1.getHealth());
//        System.out.println("Turret " + t1.getUnitID() + " health: " + t1.getHealth());
//        System.out.println("Large Fighter " + lf1);

        //START
        beginSimulation();
        //START


        //Display


        System.out.print("\nPrzykładowa odległość po koordynatach: " + map.getDistanceMap(1, 1, 4, 5));
        System.out.print("\nPrzykładowa odległość po planetach: " + map.getDistancePlanet(1, 2));


        civilization civ1 = map.civs.get(0);
        civ1.addPlanet(map.planets.get(5));
        System.out.println("\ncywilizacja 1");
        System.out.println("Civilization " + civ1);
        System.out.println("planety posiadane przez cyw 1");
        System.out.println(civ1.getOwnedPlanets());
        System.out.println("wszystkie cyw");
        System.out.println(map.civs);
        System.out.println("wszystkie planety");
        System.out.println(map.planets);

        map.generateMapPreview();
        map.showMap();

        planet pla = map.planets.get(0);
        //pla.setPopulation(1000);

        System.out.println(map.planets.get(0));

        nextGeneration();
        System.out.println(map.planets.get(0));

    }
    //MAIN /\

    public static void beginSimulation(){

        //Wprowadzanie info do startu symulacji (na razie w wersji konsolowej)
        map map = new map();

        Scanner scanner = new Scanner(System.in);

        System.out.print("\n\nKoniec testu zadawania i przyjmowania obrażeń\n\nProszę wprowadzić seed");
        seed = scanner.nextInt();

        // rozmiar mapy, ze sprawdzaniem
        int mapSize;
        do {
            System.out.print("\nPodaj rozmiar mapy [10-1000]: ");
            mapSize = scanner.nextInt();
        } while (mapSize < 10 || mapSize > 1000);

        map.xWidth = mapSize;
        map.yHeight = mapSize;

        // ilość planet - od rozmiaru mapy do kwadratu rozmiaru/10
        int planetMin = mapSize;
        int planetMax = (mapSize * mapSize) / 10;
        int planetCount;
        do {
            System.out.print("\nPodaj ilość planet [" + planetMin + " - " + planetMax + "]: ");
            planetCount = scanner.nextInt();
        } while (planetCount < planetMin || planetCount > planetMax);

        // ilość cywilizacji - od 3 do 1/10 ilości planet, chyba że ilość planet <30
        int civMax = Math.max(3, planetCount / 10);
        int civCount;
        do {
            System.out.print("\nPodaj ilość cywilizacji [3 - " + civMax + "]: ");
            civCount = scanner.nextInt();
        } while (civCount < 3 || civCount > civMax);

        // tworzenie mapy (temp1 powinien tu być ilością planet, a temp3 ilością cywilizacji
        mapTools.map.generateMap(planetCount, civCount);

        // warunek na zakańczanie symulacji

        while (mapTools.map.civs.size() > 1 && generation<101) { //na razie wyłączone bo warunek zakańczający nigdy się nie spełnia
            nextGeneration();
        }
        endSimulation();
    }

    public static void endSimulation(){

        System.out.print("\n\nZwycięzca: ");
        System.out.print(map.civs);
    }

    public static void nextGeneration() {

        generation++;
        System.out.print("\ngeneration: " + generation);

        //wszystkie metody które mają coś robić co generację - np planet.alterPopulation
        for (planet i : map.planets) {
            i.alterPopulation();
        }

        for (civilization i : map.civs) {
           i.makeDecision();
        }

        for (planet i : map.planets) {
            if (i.status.equals("producing")) {
                i.makeDecision();
            }
        }

        final Iterator<logistics> ITE = map.logiQueue.iterator();
        while (ITE.hasNext()) {
            final logistics item = ITE.next();
            item.moveUnit(ITE);
        }


        final Iterator<colonization> ITE2 = map.colonizationQueue.iterator();
        while (ITE2.hasNext()) {
            final colonization item = ITE2.next();
            item.progressColonization(ITE2);
        }

        final Iterator<combat> ITE3 = map.combatQueue.iterator();
        while (ITE3.hasNext()) {
            final combat item = ITE3.next();
            item.progressCombat(ITE3);
        }

        for (planet i : map.planets) {
            if(i.combatCooldown > 0){
                i.combatCooldown--;
            }
        }

        map.civs.removeIf(c -> c.getOwnedPlanets().isEmpty());

        map.generateMapPreview();
        map.showMap();
    }
}