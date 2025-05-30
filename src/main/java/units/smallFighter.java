package units;
import mapTools.map;

public class smallFighter extends ship {
    private int health;
    private final int damage;

    public smallFighter(int unitID, int owner, int xcoords, int ycoords, int speed, String status) {
        super(unitID, owner, xcoords, ycoords, speed, status);
        this.health = 100;
        this.damage = 40;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
        //System.out.println("SmallFighter " + getUnitID() + " took " + damage + " damage, health now " + this.health);
    }

    public void dealDamage(int targetUnitId) {
        unit target = map.getUnitById(targetUnitId);
        switch (target) {
            case null -> {
                System.out.println("Target unit not found.");
            }
            case largeFighter fighter -> fighter.takeDamage(this.damage);
            case smallFighter fighter -> fighter.takeDamage(this.damage);
            case turret turret -> turret.takeDamage(this.damage);
            default -> System.out.println("Target unit cannot take damage.");
        }

    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return "SmallFighter{" +
                "unitID=" + getUnitID() +
                ", owner=" + getOwner() +
                ", xcoords=" + getXcoords() +
                ", ycoords=" + getYcoords() +
                ", speed=" + getSpeed() +
                ", status='" + getStatus() + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                '}';
    }
}
