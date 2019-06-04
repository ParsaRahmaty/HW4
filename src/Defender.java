public abstract class Defender {
    protected int id;
    protected int team;
    protected String type;
    protected Location location;
    protected Location previousLocation;
    protected Weapon weapon;
    protected Shield shield;
    protected boolean isAlive;
    protected int attackDelay;
    protected int hp;
    protected int lastAttackTime;

    public Defender(int id, int team, Location location, Weapon weapon, Shield shield, int attackDelay, int hp) {
        this.id = id;
        this.team = team;
        this.location = location;
        this.previousLocation = location;
        this.location.setDefender(this);
        this.weapon = weapon;
        this.shield = shield;
        this.attackDelay = attackDelay;
        this.hp = hp;
        isAlive = true;
    }

    public int getId() {
        return id;
    }

    public int getTeam() {
        return team;
    }

    public Location getLocation() {
        return location;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Shield getShield() {
        return shield;
    }

    public int getAttackDelay() {
        return attackDelay;
    }

    public int getHp() {
        return hp;
    }

    public String getType() {
        return type;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAttackDelay(int attackDelay) {
        this.attackDelay = attackDelay;
    }

    public void attacked() {
        this.lastAttackTime = GameManagement.getTimer();
    }

    public abstract Action act();

    public boolean canHit() {
        if (attackDelay <= GameManagement.getTimer() - lastAttackTime)
            return true;
        else
            return false;
    }

    public Location getPreviousLocation() {
        return previousLocation;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setPreviousLocation(Location previousLocation) {
        this.previousLocation = previousLocation;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
