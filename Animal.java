import java.util.ArrayList;
import java.util.List;

public class Animal {

    private String uniqueId;
    private String species;
    private int age;
    private double weight;
    private HealthStatus healthStatus;

    private List<Sensor> sensors;
    private List<HealthEvent> healthEvents;

    public Animal(String uniqueId, String species, int age, double weight, HealthStatus healthStatus) {

        this.uniqueId = uniqueId;
        this.species = species;
        this.age = age;
        this.weight = weight;
        this.healthStatus = healthStatus;

        this.sensors = new ArrayList<>();
        this.healthEvents = new ArrayList<>();
    }

    public void logHealthEvent(HealthEvent healthEvent) {

        if (healthEvent.getType() == HealthEventType.WEIGHT_CHANGE) {
            this.weight = healthEvent.getWeight();

        } else if (healthEvent.getType() == HealthEventType.ILLNESS) {
            this.healthStatus = HealthStatus.MALADE;
        }

        healthEvents.add(healthEvent);
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }

    public void displayHealthEventHistory() {

        System.out.println("\nHealth History of " + uniqueId);

        for (HealthEvent event : healthEvents) {
            System.out.println(event);
        }
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public List<HealthEvent> getHealthEvents() {
        return healthEvents;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    
    public String toString() {

        return "Animal{" +
                "uniqueId='" + uniqueId + '\'' +
                ", species='" + species + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", healthStatus=" + healthStatus +
                '}';
    }
}