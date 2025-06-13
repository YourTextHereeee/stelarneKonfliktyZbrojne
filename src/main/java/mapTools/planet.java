package mapTools;
import units.*;

import java.sql.SQLOutput;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static mapTools.map.getCivilizationById;

public class planet {

    public int planetID;
    public int size;
    public int owner;
    public int population;
    public int xcoords;
    public int ycoords;
    public int saturation;
    public int combatCooldown = 0;
    public int colonizationCooldown = 10;

    public String status;
    // idle, producing, combat

    public void alterPopulation() {

        if(Objects.equals(this.status, "combat")){
            this.population = (int) (this.population*0.9);
            if (this.population < 10){ this.population = 0; }
        }
        else{
            if (this.population >= this.size * 1000){ this.population = this.size * 1000; }
            this.population = (int) (this.population*1.1);
            if (this.population >= this.size * 1000){ this.population = this.size * 1000; }
        }

    }

    public void setPopulation(int a){
        this.population = a;
    }

    public int getPopulation(){
        return this.population;
    }

    public void changeOwner(int owner) {

        map.getCivilizationById(owner).getOwnedPlanets().remove(this);
        this.owner = owner;
        map.getCivilizationById(owner).getOwnedPlanets().add(this);
    }

    public void produceUnit(int unitType){
        // 1 - sFighter 2 - lFighter 3 - turret 4 - transporter 5 - cargo
        if(unitType == 1){

            smallFighter sFighter = new smallFighter(
                    unit.generateID(),         // unitID
                    this.owner,               // owner
                    this.xcoords,          // starting X
                    this.ycoords,          // starting Y
                    5,                            // speed
                    "idle"
            );

            map.units.add(sFighter);
            map.getCivilizationById(this.owner).getOwnedUnits().add(sFighter);
        }

        if(unitType == 2){

            largeFighter lFighter = new largeFighter(
                    unit.generateID(),         // unitID
                    this.owner,               // owner
                    this.xcoords,          // starting X
                    this.ycoords,          // starting Y
                    4,                            // speed
                    "idle"
            );

            map.units.add(lFighter);
            map.getCivilizationById(this.owner).getOwnedUnits().add(lFighter);
        }

        if(unitType == 3){

            turret turret = new turret(
                    unit.generateID(),         // unitID
                    this.owner,               // owner
                    this.planetID,          // location
                    1                       // status

            );

            map.units.add(turret);
            map.getCivilizationById(this.owner).getOwnedUnits().add(turret);
        }

//        if(unitType == 4){
//
//            transporter transporter = new transporter(
//                    unit.generateID(),         // unitID
//                    this.owner,               // owner
//                    this.xcoords,          // starting X
//                    this.ycoords,          // starting Y
//                    5,                            // speed
//                    "idle"
//            );
//
//            map.units.add(transporter);
//        }
    }

    public void makeDecision() {

        // 1 - sFighter 2 - lFighter 3 - turret 4 - transporter 5 - cargo z czego te dwa ostatnie nie są produkowane
        if (this.saturation >= 10 - this.population/1000) {

            saturation = 0;
            int i = 0;
            Random rng = new Random(simulation.seed);

            for (unit u : getCivilizationById(this.owner).getOwnedUnits()) {

                if (u instanceof turret) {
                    i++;
                }
            }

            if (i == size) {
                this.produceUnit(rng.nextInt(1) + 1);
            } else {
                this.produceUnit(3);
            }
        } else {
            saturation++;
        }
    }

    public List<unit> getUnitsForCivilization(int ownerID) {
        List<unit> units = new ArrayList<>();
        for(unit unit : map.units){
            if(!unit.hasHealth())
                continue;
            if(unit.getXCoords() == this.xcoords && unit.getYCoords() == this.ycoords && unit.getOwner() == ownerID && unit.getHealth() > 0){
                //System.out.println(unit);
                units.add(unit);
            }
        }
        return units;
    }

    @Override
    public String toString(){
        return "Planet{" +
                "planetID:" + planetID +
                ", owner:" + owner +
                ", size:" + size +
                ", population:" + population +
                ", xCoords:" + xcoords +
                ", yCoords:" + ycoords +
                ", status:" + status + '\'' +
                "}";

    }


}