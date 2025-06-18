package units;

import mapTools.map;
import mapTools.planet;

/**
 * Klasa reprezentująca jednostkę działka sacjonarnego - przypisanego
 * do konkretnej planety
 *
 * @see map
 */
public class turret extends unit {
    public static final int DAMAGE = 80;
    private static final int HEALTH = 200;
    private int planetID;
    private int health;
    private int damage;
    private int turretStatus;

    /**
     * Konstruktor
     *
     * @param unitID
     * @param owner
     * @param planetID
     * @param turretStatus
     */
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

    @Override
    public int getXCoords() {
        return map.planets.get(planetID).xcoords;
    }

    @Override
    public int getYCoords() {
        return map.planets.get(planetID).ycoords;
    }

    /**
     * Metoda przyjmowania obrażeń - zmienia życie jendostki
     *
     * @param damage
     */
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
        System.out.println("Turret " + getUnitID() + " took " + damage + " damage, health now " + this.health);
    }

    /**
     * Metoda zadawania obrażeń innej podanej jednostce
     *
     * @param unit
     */
    public void dealDamage(unit unit) {
        unit.takeDamage(DAMAGE);
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
