public class SpearMan extends Soldier {
    public SpearMan(int id, int team, Location location) {
        super(id, team, location, null, null, 0, 0, 3000);
        type = "SpearMan";
        setShield(new IronShield(this));
        setWeapon(new Spear(this));
        setAttackDelay(weapon.getAdditionalAttackDelay());
        setMovementDelay(1 + ((IronShield) shield).getAdditionalMovementDelay());
    }
}
