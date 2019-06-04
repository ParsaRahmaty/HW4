public class IronShield extends Shield {
    private int additionalMovementDelay;

    public IronShield(Defender holder) {
        super(holder, 20);
        this.additionalMovementDelay = 3;
    }

    public int getAdditionalMovementDelay() {
        return additionalMovementDelay;
    }
}
