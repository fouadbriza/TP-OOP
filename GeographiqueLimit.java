
public class GeographiqueLimit {

    private double xLimit; // Distance from the center to the limit in the x direction
    private double yLimit; // Distance from the center to the limit in the y direction
    private double centerX;
    private double centerY;

    public GeographiqueLimit(double xLimit, double yLimit, double centerX, double centerY) {
        this.xLimit = xLimit;
        this.yLimit = yLimit;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public GeographiqueLimit() {
        this.xLimit = 0;
        this.yLimit = 0;
        this.centerX = 0;
        this.centerY = 0;
    }

    public boolean isWithinLimits(double x, double y) {
        return (x >= centerX - xLimit && x <= centerX + xLimit) &&
                (y >= centerY - yLimit && y <= centerY + yLimit);
    }

}
