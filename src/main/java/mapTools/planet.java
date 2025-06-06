package mapTools;
//import

public class planet {

    public int planetID;
    public int size;
    public int owner;
    public int population;
    public int xcoords;
    public int ycoords;

    public String status;
    // idle, producing, combat

    Boolean carbon;
    Boolean silicon;
    Boolean metal;
    public short carbonCredit;
    public short siliconCredit;
    public short metalCredit;


    public void alterPopulation(String status, int population) {

        if(this.status == "combat"){
            this.population = (int) (this.population*0.9);
        }
        else{
            this.population = (int) (this.population*1.1);
        }

    }

    public static void produceUnit(){


    }

    public static void makeDecision(){


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
