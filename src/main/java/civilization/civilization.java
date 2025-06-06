package civilization;
import units.unit;
import mapTools.planet;
import java.util.ArrayList;
import java.util.List;

public class civilization {

    private int civID;
    //private String name;

    private static List<planet> ownedPlanets = new ArrayList<>();
    private static List<unit> ownedUnits = new ArrayList<>();


    // konstruktor
    public civilization(int civID/*, String name*/) {
        this.civID = civID;
        //this.name = name;
    }


    public int getCivID() {

        return civID;
    }

    public void setCivID(int civID) {

        this.civID = civID;
    }

//    public String getName() {
//
//        return name;
//    }
//
//    public void setName(String name) {
//
//        this.name = name;
//    }


    public static List<planet> getOwnedPlanets() {

        return ownedPlanets;
    }

    public static void setOwnedPlanets(List<planet> ownedPlanets) {

        civilization.ownedPlanets = ownedPlanets;
    }

    public static List<unit> getOwnedUnits() {

        return ownedUnits;
    }

    public static void setOwnedUnits(List<unit> ownedUnits) {

        civilization.ownedUnits = ownedUnits;
    }


    public static void colonize(){

    }

    public static void attack(){

    }

    public static void defend(){

    }


    public static void makeDecision(){

        // to będzie być może najbardziej skomplikowana metoda w całości projektu
    }
}
