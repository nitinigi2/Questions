package hackerrank;

// DO NOT EDIT
public enum SeatClass {
    FIRST_CLASS(100.0),
    SECOND_CLASS(50.0);

    private final double fixedPrice;

    SeatClass(double fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public double getFixedPrice() {
        return this.fixedPrice;
    }
}
