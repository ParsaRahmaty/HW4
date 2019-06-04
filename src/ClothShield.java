public class ClothShield extends Shield {
    private int additionalMovementDelay;

    public ClothShield(Defender holder) {
        super(holder, 1);
        this.additionalMovementDelay = 0;
    }

    public int getAdditionalMovementDelay() {
        return additionalMovementDelay;
    }
}
