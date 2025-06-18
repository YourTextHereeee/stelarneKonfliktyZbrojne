package units;

/**
 * Klasa reprezentująca jednostkę małego statku bojowego,
 * przechowuje jego parametry w tym życie, i zadawane obrażenia
 */
public class smallFighter extends ship {
    public static final int DAMAGE = 40;
    private static final int HEALTH = 100;
    private int health;

    /**
     * konstruktor klasy
     *
     * @param unitID
     * @param owner
     * @param xcoords
     * @param ycoords
     * @param speed
     * @param status
     */
    public smallFighter(int unitID, int owner, int xcoords, int ycoords, int speed, String status) {
        super(unitID, owner, xcoords, ycoords, speed, status);
        this.health = HEALTH;
    }

    /**
     * Metoda przyjmowania obrażeń - zmienia życie jendostki
     *
     * @param damage
     */
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
        System.out.println("SmallFighter " + getUnitID() + " took " + damage + " damage, health now " + this.health);
    }

    /**
     * Metoda zadawania obrażeń innej podanej jednostce
     *
     * @param unit
     */
    public void dealDamage(unit unit) {
        unit.takeDamage(DAMAGE);
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return DAMAGE;
    }

    @Override
    public String toString() {
        return "SmallFighter{" +
                "unitID=" + getUnitID() +
                ", owner=" + getOwner() +
                ", xcoords=" + getXCoords() +
                ", ycoords=" + getYCoords() +
                ", speed=" + getSpeed() +
                ", status='" + getStatus() + '\'' +
                ", health=" + health +
                ", damage=" + DAMAGE +
                '}';
    }
}
