public class WaterSensor extends Sensor {
    private double value;
    private String unit;
    private ThresholdRange threshold;

    public WaterSensor(String code, String zone,ThresholdRange threshold) {
        super(code, zone);
        this.threshold = threshold;
    }

    public void setWaterValue(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public Reading getReading() {
        return super.getReading();

    };

    public NumericalReading sendReading(double oxy) {
        NumericalReading newReading = new NumericalReading(oxy);
        if (threshold.isWithinThreshold(oxy)) {
            newReading.setReading(oxy, AlertLevel.NORMAL);
        } else {
            newReading.setReading(oxy, AlertLevel.CRITICAL);
        }
        addReading(newReading);
        return newReading;
    }
}
