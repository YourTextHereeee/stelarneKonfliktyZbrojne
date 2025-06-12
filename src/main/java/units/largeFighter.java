package units;

public class largeFighter extends ship {
    public static final int DAMAGE = 50;
    private static final int HEALTH = 150;
    private int health;

    public largeFighter(int unitID, int owner, int xcoords, int ycoords, int speed, String status) {
        super(unitID, owner, xcoords, ycoords, speed, status);
        this.health = HEALTH;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
        System.out.println("LargeFighter " + getUnitID() + " took " + damage + " damage, health now " + this.health);
    }

    public void dealDamage(unit unit) {
        unit.takeDamage(DAMAGE);
    }


    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return "LargeFighter {" +
                "unitID=" + getUnitID() +
                ", owner=" + getOwner() +
                ", xcoords=" + getXcoords() +
                ", ycoords=" + getYcoords() +
                ", speed=" + getSpeed() +
                ", status='" + getStatus() + '\'' +
                ", health=" + health +
                ", damage=" + DAMAGE +
                '}';
    }
}
