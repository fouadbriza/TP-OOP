public class SoilSensor extends Sensor {
    private double value;
    private String unit;
    private ThresholdRange threshold;

    public SoilSensor(String code, String zone,ThresholdRange threshold) {
        super(code, zone);
        this.threshold= threshold;
    }
    public void setSoilValue(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public Reading getReading() {
        return super.getReading();
    }

    public NumericalReading sendReading(double ph) {
        NumericalReading newReading = new NumericalReading(ph);
        if (threshold.isWithinThreshold(ph)) {
            newReading.setReading(ph, AlertLevel.NORMAL);
        } else {
            newReading.setReading(ph, AlertLevel.CRITICAL);
        }
        addReading(newReading);
        return newReading;
    }

}
