import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Sensor {
   
    protected String code;
    protected String zone;
    protected SensorStatus status;
    protected int maxValue;
    protected int minValue;
    protected List<Reading> readings;

    public Sensor(String code, String zone) {
        this.code = code;
        this.zone = zone;
        this.status = SensorStatus.ACTIVE;
        this.readings = new ArrayList<>();
    }

    public void suspend() {
        this.status = SensorStatus.SUSPENDED;
        
    }

    public void activate() {
        this.status = SensorStatus.ACTIVE;
    }

    public SensorStatus getStatus() {
        return this.status;
    }

    public void changeStatus(SensorStatus newStatus) {
        this.status = newStatus;
    }

    public String getCode() {
        return this.code;
    }

    public String getZone() {
        return this.zone;
    }

    public List<Reading> getReadings() {
        return this.readings;
    }

    public Reading getReading() { //getting the last reading
        if (this.readings.isEmpty()) {
            return null;
        }
        return this.readings.get(this.readings.size() - 1);
    }

    protected void addReading(Reading reading) {
        this.readings.add(reading);
    }

    public void displayStats() { //not used for gpsSensor,could be interface or i do class numericalSensor but not urgent
        double min = 0;
        double max = 0;
        double sum = 0.0;
        int count = 0;
        boolean firstValue = true;

        for (Reading reading : this.readings) {
            if (reading instanceof NumericalReading numericalReading) {
                double value = numericalReading.getValue();
                if (firstValue) {
                    min = value;
                    max = value;
                    firstValue = false;
                }
                if (value < min) {
                    min = value;
                }
                if (value > max) {
                    max = value;
                }
                sum += value;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No numerical readings available for statistics.");
            return;
        }

        double average = sum / count;
        System.out.println("Statistics for sensor " + code + ":");
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.println("Average: " + average);
    }

    public List<Reading> getReadingsByLevel(AlertLevel level) {
        List<Reading> filteredReadings = new ArrayList<>();

        for (Reading reading : this.readings) {
            if (reading.getLevel() == level) {
                filteredReadings.add(reading);
            }
        }

        return filteredReadings;
    }

    public List<Reading> getReadingsByDateRange(LocalDateTime start, LocalDateTime end) {
        List<Reading> filteredReadings = new ArrayList<>();

        for (Reading reading : this.readings) {
            LocalDateTime dateTime = reading.getDateTime();
            if ((dateTime.isEqual(start) || dateTime.isAfter(start)) &&
                    (dateTime.isEqual(end) || dateTime.isBefore(end))) {
                filteredReadings.add(reading);
            }
        }

        return filteredReadings;
    }
/* 
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{code='" + code + "', zone='" + zone + "'}";
    }
*/
    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + code + "]" + " | " + zone + " range:"+ "[" + minValue+"-"+maxValue+ "]";
    }

    }