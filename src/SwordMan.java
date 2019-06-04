public class SwordMan extends Soldier{
    public SwordMan(int id, int team, Location location) {
        super(id, team, location, null, null, 0, 0, 5000);
        type = "SwordMan";
        setShield(new IronShield(this));
        setWeapon(new Sword(this));
        setAttackDelay(weapon.getAdditionalAttackDelay());
        setMovementDelay(2 + ((IronShield) shield).getAdditionalMovementDelay());
    }

    @Override
    public void attacked() {
        super.attacked();
        setAttackDelay(getAttackDelay() + 1);
    }
}
