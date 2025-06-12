package units;

public class turret extends unit {
    public static final int DAMAGE = 80;
    private static final int HEALTH = 200;
    private int planetID;
    private int health;
    private int damage;
    private int turretStatus;

    public turret(int unitID, int owner, int planetID, int turretStatus) {
        super(unitID, owner);
        this.planetID = planetID;
        this.health = HEALTH;
        this.damage = DAMAGE;
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

    public void dealDamage(unit unit) {
        unit.takeDamage(DAMAGE);
    }

    public int getTurretPlanetID(){
        return planetID;
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
