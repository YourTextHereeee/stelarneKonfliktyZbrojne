package mapTools;
import units.*;

public class planet {

    public int planetID;
    public int size;
    public int owner;
    public int population;
    public int xcoords;
    public int ycoords;

    public String status;
    // idle, producing, combat



    public void alterPopulation() {

        if(this.status == "combat"){
            this.population = (int) (this.population*0.9);
        }
        else{
            this.population = (int) (this.population*1.1);
        }

    }

    public void chpop(int a){
        this.population = a;
    }

    public void produceUnit(int unitType){
        // 1 - sFighter 2 - lFighter 3 - turret 4 - transporter 5 - cargo
        if(unitType == 1){

            smallFighter sFighter = new smallFighter(
                    map.units.size() + 1,         // unitID
                    this.owner,               // owner
                    this.xcoords,          // starting X
                    this.ycoords,          // starting Y
                    5,                            // speed
                    "idle"
            );

            map.units.add(sFighter);
        }

        if(unitType == 2){

            largeFighter lFighter = new largeFighter(
                    map.units.size() + 1,         // unitID
                    this.owner,               // owner
                    this.xcoords,          // starting X
                    this.ycoords,          // starting Y
                    5,                            // speed
                    "idle"
            );

            map.units.add(lFighter);
        }

        if(unitType == 3){

            turret turret = new turret(
                    map.units.size() + 1,         // unitID
                    this.owner,               // owner
                    this.planetID,          // location
                    1                       // status

            );

            map.units.add(turret);
        }

        if(unitType == 4){

            transporter transporter = new transporter(
                    map.units.size() + 1,         // unitID
                    this.owner,               // owner
                    this.xcoords,          // starting X
                    this.ycoords,          // starting Y
                    5,                            // speed
                    "idle"
            );

            map.units.add(transporter);
        }
    }

    public void makeDecision(){

        // 1 - sFighter 2 - lFighter 3 - turret 4 - transporter 5 - cargo z czego te dwa ostatnie nie są produkowane
        //produceUnit(1);
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