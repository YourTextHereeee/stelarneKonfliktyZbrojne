package units;
import mapTools.map;

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

    public void dealDamage(int targetUnitId) {
        unit target = map.getUnitById(targetUnitId);
        switch (target) {
            case null -> {
                System.out.println("Target unit not found.");
            }
            case largeFighter fighter -> fighter.takeDamage(DAMAGE);
            case smallFighter fighter -> fighter.takeDamage(DAMAGE);
            case turret turret -> turret.takeDamage(DAMAGE);
            default -> System.out.println("Target unit cannot take damage.");
        }

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
