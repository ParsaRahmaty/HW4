import java.util.ArrayList;

public abstract class Weapon {
    protected int damage;
    protected int additionalAttackDelay;
    protected Defender holder;

    public Weapon(Defender holder, int damage, int additionalAttackDelay) {
        this.holder = holder;
        this.damage = damage;
        this.additionalAttackDelay = additionalAttackDelay;
    }

    public Defender getHolder() {
        return holder;
    }

    public int getDamage() {
        return damage;
    }

    public int getAdditionalAttackDelay() {
        return additionalAttackDelay;
    }

    public abstract ArrayList<Location> getAttackableLocations();
}
