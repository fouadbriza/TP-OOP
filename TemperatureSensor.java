

public class TemperatureSensor extends Sensor {
    private double temperature;
    private String unit;
    ThresholdRange threshold;

    public TemperatureSensor(String code, String zone,ThresholdRange threshold) {
        super(code, zone);
        this.threshold= threshold;
    }

    public void setTemperature(double temperature,String unit) {
        this.temperature = temperature;
        this.unit = unit;
    }

    @Override
    public NumericalReading getReading() {
        return (NumericalReading) super.getReading();
    }



        public NumericalReading sendReading(double temperature) {
        NumericalReading newReading = new NumericalReading(temperature);
        if (threshold.isWithinThreshold(temperature)) {
            newReading.setReading(temperature, AlertLevel.NORMAL);
        } else {
            if (temperature < threshold.getMin() - 5 || temperature > threshold.getMax() + 5) {
                newReading.setReading(temperature, AlertLevel.CRITICAL);
            } else {
                newReading.setReading(temperature, AlertLevel.WARNING);
            }
        }
        addReading(newReading);
        return newReading;
    }

}