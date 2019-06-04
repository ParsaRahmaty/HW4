public class AttackAction extends Action {
    private Soldier reactor;

    public AttackAction(Defender actor, Soldier reactor) {
        super(actor);
        this.reactor = reactor;
    }

    @Override
    public void doAction() {
        if (reactor != null) {
            actor.attacked();
            reactor.takeDamage(actor.getWeapon().getDamage());
            if (!reactor.isAlive() && actor instanceof SwordMan)
                ((Soldier) actor).move(reactor.getPreviousLocation().getColumn(), reactor.getPreviousLocation().getRow());
        }
    }
}
