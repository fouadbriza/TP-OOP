// NumericReading.java
public class NumericalReading extends Reading {
    private double value;
    private String unit;
    private Alert alert;

    public NumericalReading(double value) {
        super();
        this.value = value;
        this.alertLevel = AlertLevel.WARNING;
        this.alert = null;
    }

    public void setReading(double value,AlertLevel alertLevel) {
        this.value=value;
        this.alertLevel = alertLevel;
    }

    public double getValue() {
        return value;
    }
/* 
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{value=" + value + ", level=" + alertLevel + "}";
    }
*/


    @Override
    public String toString() {
        return getClass().getSimpleName() + " "+ value +" --> value:" +value + " | level:" + alertLevel;
    }
}