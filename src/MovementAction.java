public class MovementAction extends Action {
    private Location destination;

    public MovementAction(Defender actor, Location destination) {
        super(actor);
        this.destination = destination;
    }

    public Location getDestination() {
        return destination;
    }

    @Override
    public void doAction() {
        if (actor.isAlive())
            ((Soldier)actor).move(destination.getColumn(), destination.getRow());
    }
}
