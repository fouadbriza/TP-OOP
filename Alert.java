import java.time.LocalDateTime;

public class Alert {
    private final String id;
    private final Sensor sensor;
    private final double recordedValue;
    private final AlertLevel alertLevel;
    private StatusAlert status;
    private final LocalDateTime creationDate;

    public Alert(String id, Sensor sensor, double recordedValue, AlertLevel AlertLevel) {
        this.id = id;
        this.sensor = sensor;
        this.recordedValue = recordedValue;
        this.alertLevel = AlertLevel;
        this.status = StatusAlert.ACTIVE;
        this.creationDate = LocalDateTime.now();
    }

    public void acknowledge() {
        this.status = StatusAlert.ACKNOWLEDGED;
        System.out.println("Alert " + id + " acknowledged.");
    }

    public void dismiss() {
        this.status = StatusAlert.DISMISSED;
        System.out.println("Alert " + id + " dismissed.");
    }

    public boolean isActive() {
        return this.status == StatusAlert.ACTIVE;
    }

    // Getters
    public String getId() {
        return id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public double getRecordedValue() {
        return recordedValue;
    }

    public AlertLevel getGravityLevel() {
        return alertLevel;
    }

    public StatusAlert getStatus() {
        return status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
/* 
    @Override
    public String toString() {
        
        String sensorCode = "null";
        if (sensor != null) {
            sensorCode = sensor.getCode();
        }
        return "Alert{" +
                "id='" + id + '\'' +
                ", sensor=" + sensorCode +
                ", value=" + recordedValue +
                ", level=" + alertLevel +
                ", date=" + creationDate +
                ", status=" + status +
                '}';
    }
*/

     @Override
    public String toString() {
        
        String sensorCode = "null";
        if (sensor != null) {
            sensorCode = sensor.getCode();
        }
        return  sensorCode+ " | " + sensor.getClass()+ " | " +
                 recordedValue + " | "+
                 alertLevel + " | " + sensor.getStatus();
    }
}