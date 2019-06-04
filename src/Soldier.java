import java.util.ArrayList;

public abstract class Soldier extends Defender {
    protected boolean isFacingUp;
    protected int movementDelay;
    protected int lastMovementTime;

    public Soldier(int id, int team, Location location, Weapon weapon, Shield shield, int attackDelay, int movementDelay, int hp) {
        super(id, team, location, weapon, shield, attackDelay, hp);
        this.movementDelay = movementDelay;
        if (team == 1)
            this.isFacingUp = false;
        else if (team == 2)
            this.isFacingUp = true;
        else
            System.out.println("ERROR: Team number should only be 1 or 2.");
    }

    public boolean canMove() {
        if (movementDelay <= GameManagement.getTimer() - lastMovementTime && isAlive)
            return true;
        else
            return false;
    }

    public void move(int column, int row) {
        if (GameManagement.getLocation(column, row) != null) {
            if (location.getColumn() != column || location.getRow() != row)
                this.lastMovementTime = GameManagement.getTimer();
            this.previousLocation = this.location;
            this.location = GameManagement.getLocation(column, row);
            if (GameManagement.getLocation(column, row - 1) == null && isFacingUp)
                isFacingUp = false;
            else if (GameManagement.getLocation(column, row + 1) == null && !isFacingUp)
                isFacingUp = true;
        } else {
            System.out.println("ERROR: Invalid coordinates for a location.");
        }
    }

    public void takeDamage(int damageAmount) {
        this.hp -= damageAmount * ((100.0 - shield.getDamageReducePercentage()) / 100.0);
        if (hp <= 0) {
            isAlive = false;
            previousLocation = location;
            location = null;
        }
    }

    public boolean isFacingUp() {
        return isFacingUp;
    }

    public int getMovementDelay() {
        return movementDelay;
    }

    public void setMovementDelay(int movementDelay) {
        this.movementDelay = movementDelay;
    }

    public void setFacingUp(boolean facingUp) {
        isFacingUp = facingUp;
    }

    public int getLastMovementTime() {
        return lastMovementTime;
    }

    @Override
    public Action act() {
        ArrayList<Soldier> attackableSoldiers = new ArrayList<>();
        for (Location location : weapon.getAttackableLocations()) {
            if (location.getDefender() != null && location.getDefender() instanceof Soldier && location.getDefender().getTeam() != team)
                attackableSoldiers.add((Soldier) location.getDefender());
        }
        if (attackableSoldiers.size() == 0) {
            if (canMove() && isFacingUp) {
                if (GameManagement.getLocation(location.getColumn(), location.getRow() - 1).getDefender() == null || (GameManagement.getLocation(location.getColumn(), location.getRow() - 1).getDefender() != null && GameManagement.getLocation(location.getColumn(), location.getRow() - 1).getDefender() instanceof Soldier && !((Soldier) GameManagement.getLocation(location.getColumn(), location.getRow() - 1).getDefender()).isFighting() && ((Soldier) GameManagement.getLocation(location.getColumn(), location.getRow() - 1).getDefender()).canMove())) {
                    return new MovementAction(this, GameManagement.getLocation(location.getColumn(), location.getRow() - 1));
                } else if (GameManagement.getLocation(location.getColumn(), location.getRow() - 1).getDefender() != null && GameManagement.getLocation(location.getColumn(), location.getRow() - 1).getDefender() instanceof Tower && GameManagement.getLocation(location.getColumn() + 1, location.getRow() - 1).getDefender() == null) {
                    return new MovementAction(this, GameManagement.getLocation(location.getColumn() + 1, location.getRow() - 1));
                } else if (GameManagement.getLocation(location.getColumn(), location.getRow() - 1).getDefender() != null && GameManagement.getLocation(location.getColumn(), location.getRow() - 1).getDefender() instanceof Tower && GameManagement.getLocation(location.getColumn() - 1, location.getRow() - 1).getDefender() == null) {
                    return new MovementAction(this, GameManagement.getLocation(location.getColumn() - 1, location.getRow() - 1));
                } else {
                    return new MovementAction(this, location);
                }
            } else if (canMove() && !isFacingUp) {
                if (GameManagement.getLocation(location.getColumn(), location.getRow() + 1).getDefender() == null || (GameManagement.getLocation(location.getColumn(), location.getRow() + 1).getDefender() != null && !((Soldier) GameManagement.getLocation(location.getColumn(), location.getRow() + 1).getDefender()).isFighting() && ((Soldier) GameManagement.getLocation(location.getColumn(), location.getRow() + 1).getDefender()).canMove())) {
                    return new MovementAction(this, GameManagement.getLocation(location.getColumn(), location.getRow() + 1));
                } else if (GameManagement.getLocation(location.getColumn(), location.getRow() + 1).getDefender() != null && GameManagement.getLocation(location.getColumn(), location.getRow() + 1).getDefender() instanceof Tower && GameManagement.getLocation(location.getColumn() - 1, location.getRow() + 1).getDefender() == null) {
                    return new MovementAction(this, GameManagement.getLocation(location.getColumn() - 1, location.getRow() + 1));
                } else if (GameManagement.getLocation(location.getColumn(), location.getRow() + 1).getDefender() != null && GameManagement.getLocation(location.getColumn(), location.getRow() + 1).getDefender() instanceof Tower && GameManagement.getLocation(location.getColumn() + 1, location.getRow() + 1).getDefender() == null) {
                    return new MovementAction(this, GameManagement.getLocation(location.getColumn() + 1, location.getRow() + 1));
                } else {
                    return new MovementAction(this, location);
                }
            } else {
                return new MovementAction(this, location);
            }
        } else {
            if (canHit()) {
                Soldier target;
                if (attackableSoldiers.size() > 1) {
                    for (int i = 0; i < attackableSoldiers.size() - 1; i++) {
                        if (attackableSoldiers.get(i).getId() < attackableSoldiers.get(i + 1).getId()) {
                            Soldier tmp = attackableSoldiers.get(i + 1);
                            attackableSoldiers.add(i + 1, attackableSoldiers.get(i));
                            attackableSoldiers.remove(i + 2);
                            attackableSoldiers.add(i, tmp);
                            attackableSoldiers.remove(i + 1);
                        }
                    }
                    target = attackableSoldiers.get(attackableSoldiers.size() - 1);
                } else {
                    target = attackableSoldiers.get(0);
                }
                return new AttackAction(this, target);
            } else {
                return new AttackAction(this, null);
            }
        }
    }

    public boolean isFighting() {
        for (Location location : weapon.getAttackableLocations())
            if (location.getDefender() != null && location.getDefender() instanceof Soldier && location.getDefender().getTeam() != team)
                return true;
        return false;
    }
}
