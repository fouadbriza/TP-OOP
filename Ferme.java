import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ferme {

    private String id;
    private String name;

    private List<GeographiqueZone> zones;
    private List<Alert> alerts;

    public Ferme(String id, String name) {

        this.id = id;
        this.name = name;

        this.zones = new ArrayList<>();
        this.alerts = new ArrayList<>();
    }

    public Ferme(String name) {
        this("", name);
    }

    public void addZone(GeographiqueZone zone) {
        zones.add(zone);
    }

    public void displayAllZones() {

        for (GeographiqueZone zone : zones) {
            zone.display();
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<GeographiqueZone> getZones() {
        return zones;
    }

    public void addAlert(Alert alert) {
        if (alert != null) {
            alerts.add(alert);
        }
    }

    public List<Alert> getActiveAlerts() {
        return alerts;
    }

    public void displayActiveAlerts() {
        if (alerts.isEmpty()) {
            System.out.println("No active alerts.");
            return;
        }

        for (Alert alert : alerts) {
            System.out.println(alert);
        }
    }

    public void dismissAlert(Alert alert) {
        if (alerts.contains(alert)) {
            alert.dismiss();
            alerts.remove(alert);
        } else {
            System.out.println("Alert not found in the active alerts list.");
        }
    }

    public void displayAllSensor(String zoneCode) {
        for (GeographiqueZone zone : zones) {
            if (zone.getCode().equals(zoneCode)) {
                for (Sensor sensor : zone.getAllSensors()) {
                    Reading reading = sensor.getReading();
                    if (reading == null) {
                        System.out.println("Sensor: " + sensor.getCode() + ", Last Reading: none");
                    } else {
                        System.out.println("Sensor: " + sensor.getCode() + ", Last Reading Level: " + reading.getLevel());
                    }
                }
            }
        }
    }

    public void display() {

        System.out.println(
                "Ferme{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", numberOfZones=" + zones.size() +
                        '}');
    }

    public List<ProductionRecord> filterProduction(
            ProductionType type,
            LocalDate startDate,
            LocalDate endDate) {

        List<ProductionRecord> filteredProductions = new ArrayList<>();

        for (GeographiqueZone zone : zones) {

            for (ProductionRecord record : zone.getProductionRecords()) {

                if (record.getType() == type &&
                        record.getDate().compareTo(startDate) >= 0 &&
                        record.getDate().compareTo(endDate) <= 0) {

                    filteredProductions.add(record);
                }
            }
        }

        return filteredProductions;
    }

    public List<Animal> filterAnimalsByHealthStatus(HealthStatus status) {

        List<Animal> filteredAnimals = new ArrayList<>();

        for (GeographiqueZone zone : zones) {

            if (zone instanceof LivestockZone) {

                LivestockZone livestockZone = (LivestockZone) zone;

                for (Animal animal : livestockZone.getAnimals()) {

                    if (animal.getHealthStatus() == status) {

                        filteredAnimals.add(animal);
                    }
                }
            }
        }

        return filteredAnimals;
    }

    public void displayAnimalsByHealthStatus(HealthStatus status) {

        System.out.println("\nAnimals with status : " + status);

        for (GeographiqueZone zone : zones) {

            if (zone instanceof LivestockZone) {

                LivestockZone livestockZone = (LivestockZone) zone;
                zone.display();

                for (Animal animal : livestockZone.getAnimals()) {

                    if (animal.getHealthStatus() == status) {

                        System.out.println(animal);
                    }
                }
            }
        }
    }

    public List<Crop> filterCropsByFamily(CropFamily family) {

        List<Crop> filteredCrops = new ArrayList<>();

        for (GeographiqueZone zone : zones) {

            if (zone instanceof CropZone) {

                CropZone cropZone = (CropZone) zone;

                for (Crop crop : cropZone.getCropFields()) {

                    if (crop.getFamily() == family) {

                        filteredCrops.add(crop);
                    }
                }
            }
        }

        return filteredCrops;
    }

    public void displayCropsByFamily(CropFamily family) {

        System.out.println("\nCrops with family : " + family);

        for (GeographiqueZone zone : zones) {

            if (zone instanceof CropZone) {

                CropZone cropZone = (CropZone) zone;

                zone.display();

                for (Crop crop : cropZone.getCropFields()) {

                    if (crop.getFamily() == family) {

                        System.out.println(crop);
                    }
                }
            }
        }
    }

    public void displayProductionByTypeAndPeriod(
            ProductionType type,
            LocalDate startDate,
            LocalDate endDate) {

        System.out.println("\nProductions of type : " + type);

        for (GeographiqueZone zone : zones) {

            boolean displayed = false;

            for (ProductionRecord record : zone.getProductionRecords()) {

                if (record.getType() == type &&
                        record.getDate().compareTo(startDate) >= 0 &&
                        record.getDate().compareTo(endDate) <= 0) {

                    if (!displayed) {

                        zone.display();
                        displayed = true;
                    }

                    System.out.println(record);
                }
            }
        }
    }

    public void generateReport() {

        System.out.println("\n========== FARM REPORT ==========");

        System.out.println("\nFarm Information");
        display();

        System.out.println("\n========== ZONES ==========");

        for (GeographiqueZone zone : zones) {

            zone.display();

            if (zone instanceof CropZone) {

                CropZone cropZone = (CropZone) zone;

                if (!cropZone.getCropFields().isEmpty()) {

                    System.out.println("\nCrops :");

                    for (Crop crop : cropZone.getCropFields()) {
                        System.out.println(crop);
                    }
                }
            }

            else if (zone instanceof LivestockZone) {

                LivestockZone livestockZone = (LivestockZone) zone;

                if (!livestockZone.getAnimals().isEmpty()) {

                    System.out.println("\nAnimals :");

                    for (Animal animal : livestockZone.getAnimals()) {
                        System.out.println(animal);
                    }
                }
            }

            else if (zone instanceof AquacultureZone) {

                AquacultureZone aquaZone = (AquacultureZone) zone;

                if (!aquaZone.getTanks().isEmpty()) {

                    System.out.println("\nTanks :");

                    for (Tank tank : aquaZone.getTanks()) {
                        System.out.println(tank);
                    }
                }
            }

            if (!zone.getProductionRecords().isEmpty()) {

                System.out.println("\nProductions :");

                zone.displayProduction();
            }

            System.out.println("\n--------------------------------");
        }
    }
}