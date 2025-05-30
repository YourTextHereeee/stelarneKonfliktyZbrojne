package units;
import mapTools.map;

public class turret extends unit {
    private int planetID;
    private int health;
    private int damage;
    private int turretStatus;

    public turret(int unitID, int owner, int planetID, int health, int damage, int turretStatus) {
        super(unitID, owner);
        this.planetID = planetID;
        this.health = health;
        this.damage = damage;
        this.turretStatus = turretStatus;
    }

    public int getPlanetID() {
        return planetID;
    }

    public void setPlanetID(int planetID) {
        this.planetID = planetID;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getTurretStatus() {
        return turretStatus;
    }

    public void setTurretStatus(int turretStatus) {
        this.turretStatus = turretStatus;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
        System.out.println("Turret " + getUnitID() + " took " + damage + " damage, health now " + this.health);
    }

    public void dealDamage(int targetUnitId) {
        unit target = map.getUnitById(targetUnitId);
        switch (target) {
            case null -> {
                System.out.println("Target unit not found.");
                return;
            }
            case largeFighter fighter -> fighter.takeDamage(this.damage);
            case smallFighter fighter -> fighter.takeDamage(this.damage);
            case turret turret -> turret.takeDamage(this.damage);
            default -> System.out.println("Target unit cannot take damage.");
        }

    }

    @Override
    public String toString() {
        return "Turret{" +
                "unitID=" + getUnitID() +
                ", owner=" + getOwner() +
                ", planetID=" + planetID +
                ", health=" + health +
                ", damage=" + damage +
                ", turretStatus=" + turretStatus +
                '}';
    }
}
