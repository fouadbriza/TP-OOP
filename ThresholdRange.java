public class ThresholdRange {
    private double a;
    private double b;

    public ThresholdRange(double a,double b) {
        this.a=a;
        this.b=b;
    }

    public boolean isWithinThreshold(double value) {
        return value >= a && value <= b;
    }

    public double getMin() {
        return a;
    }

    public double getMax() {
        return b;
    }

    @Override
    public String toString() {
        return "ThresholdRange{" + "min=" + a + ", max=" + b + '}';
    }
}
