public abstract class Action {
    protected Defender actor;

    public Action(Defender actor) {
        this.actor = actor;
    }

    public Defender getActor() {
        return actor;
    }

    public abstract void doAction();
}
