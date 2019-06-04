public abstract class Shield {
    protected int damageReducePercentage;
    protected Defender holder;

    public Shield(Defender holder, int damageReducePercentage) {
        this.holder = holder;
        this.damageReducePercentage = damageReducePercentage;
    }

    public Defender getHolder() {
        return holder;
    }

    public int getDamageReducePercentage() {
        return damageReducePercentage;
    }
}
