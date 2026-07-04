import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class GeographiqueZone {

    protected String code;
    protected String name;

    protected GeographiqueLimit bound;

    protected ZoneStatus status;

    protected List<Sensor> sensors;
    protected List<ProductionRecord> productionRecords;

    public GeographiqueZone(String code, String name, GeographiqueLimit bound) {

        this.code = code;
        this.name = name;

        this.bound = bound;

        this.status = ZoneStatus.ACTIVE;

        this.sensors = new ArrayList<>();
        this.productionRecords = new ArrayList<>();
    }

    public void suspend() {

        this.status = ZoneStatus.SUSPENDED;

        for (Sensor sensor : sensors) {
            sensor.suspend();
        }
    }

    public void activate() {

        this.status = ZoneStatus.ACTIVE;

        for (Sensor sensor : sensors) {
            sensor.activate();
        }
    }

    public void addProductionRecord(ProductionRecord record) {
        productionRecords.add(record);
    }

    public void displayProduction() {

        for (ProductionRecord production : productionRecords) {
            production.display();
        }
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }

    public void display() {

        System.out.println(
                "Zone{" +
                        "code='" + code + '\'' +
                        ", name='" + name + '\'' +
                        ", status=" + status +
                        '}');
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public GeographiqueLimit getBound() {
        return bound;
    }

    public ZoneStatus getStatus() {
        return status;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public List<Sensor> getAllSensors() {
        return sensors;
    }

    public List<ProductionRecord> getProductionRecords() {
        return productionRecords;
    }

    public String getProductionHistoryByDateRange(LocalDate startDate, LocalDate endDate) {
        StringBuilder history = new StringBuilder();
        for (ProductionRecord record : productionRecords) {
            if (record.getDate().compareTo(startDate) >= 0 && record.getDate().compareTo(endDate) <= 0) {
                history.append(record.toString()).append("\n");
            }
        }
        return history.toString();
    }

    public double calculateTotalProduction(LocalDate startDate, LocalDate endDate) {

        double total = 0;

        for (ProductionRecord record : productionRecords) {

            if (record.getDate().compareTo(startDate) >= 0 &&
                    record.getDate().compareTo(endDate) <= 0) {

                total += record.getProductionValue();
            }
        }

        return total;
    }

    public List<ProductionRecord> filterProduction( LocalDate startDate, LocalDate endDate) {

        List<ProductionRecord> result = new ArrayList<>();

        for (ProductionRecord record : productionRecords) {

            if (record.getDate().compareTo(startDate) >= 0 &&
                    record.getDate().compareTo(endDate) <= 0) {

                result.add(record);
            }
        }

        return result;
    }

    public void displayFilteredProduction(LocalDate startDate, LocalDate endDate) {

        System.out.println("\nZone: " + name);

        for (ProductionRecord record : productionRecords) {

            if (record.getDate().compareTo(startDate) >= 0 &&
                    record.getDate().compareTo(endDate) <= 0) {

                System.out.println(record);
            }
        }
    }
}