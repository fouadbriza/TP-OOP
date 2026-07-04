public class BiometricSensor extends Sensor {
    private double value;
    private String unit;
    ThresholdRange threshold;

   public BiometricSensor(String code, String zone,ThresholdRange 
    threshold) {
        super(code, zone);
        this.threshold= threshold;
    }

    public void setBiometricValue(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public NumericalReading getReading() {
        return (NumericalReading) super.getReading();
    }

    public NumericalReading sendReading(double biometricValue) {
        NumericalReading newReading = new NumericalReading(biometricValue);
        if (threshold.isWithinThreshold(biometricValue)) {
            newReading.setReading(biometricValue, AlertLevel.NORMAL);
        } else {
            newReading.setReading(biometricValue, AlertLevel.CRITICAL);
        }
        addReading(newReading);
        return newReading;
    }
}
