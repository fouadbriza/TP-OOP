import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MainMerged {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("                MERGED OOP DEMO");
        System.out.println("==================================================");

        Ferme farm = new Ferme("F001", "Green Valley Farm");
        AlertManager alertManager = new AlertManager();

        GeographiqueLimit cropBounds = new GeographiqueLimit(36.5, 3.0, 36.8, 3.5);
        GeographiqueLimit livestockBounds = new GeographiqueLimit(36.6, 3.1, 36.9, 3.6);
        GeographiqueLimit aquaBounds = new GeographiqueLimit(36.4, 2.9, 36.7, 3.3);

        CropZone cropZone = new CropZone("North Field", "Z001", cropBounds);
        LivestockZone livestockZone = new LivestockZone("East Pasture", "Z002", livestockBounds, LiveStockType.RUMINANT);
        AquacultureZone aquacultureZone = new AquacultureZone("South Pond", "Z003", aquaBounds);

        farm.addZone(cropZone);
        farm.addZone(livestockZone);
        farm.addZone(aquacultureZone);

        Crop wheat = new Crop("Wheat", LocalDate.of(2025, 3, 1), LocalDate.of(2025, 7, 15),
                new SoilRequirement(6.0, 7.5, 40, 70), CropFamily.CEREAL);
        Crop tomato = new Crop("Tomato", LocalDate.of(2025, 4, 10), LocalDate.of(2025, 8, 20),
                new SoilRequirement(5.5, 7.0, 50, 80), CropFamily.VEGETABLE);
        Crop apple = new Crop("Apple", LocalDate.of(2025, 2, 15), LocalDate.of(2025, 9, 30),
                new SoilRequirement(5.0, 6.5, 45, 75), CropFamily.FRUIT);

        cropZone.addCrop(wheat);
        cropZone.addCrop(tomato);
        cropZone.addCrop(apple);

        Animal cow1 = new Animal("A001", "Cow", 4, 520, HealthStatus.SAIN);
        Animal cow2 = new Animal("A002", "Cow", 3, 480, HealthStatus.SAIN);
        Animal sheep1 = new Animal("A003", "Sheep", 2, 65, HealthStatus.MALADE);
        Animal goat1 = new Animal("A004", "Goat", 1, 40, HealthStatus.QUARANTAINE);

        livestockZone.addAnimal(cow1);
        livestockZone.addAnimal(cow2);
        livestockZone.addAnimal(sheep1);
        livestockZone.addAnimal(goat1);

        cow2.logHealthEvent(new HealthEvent(HealthEventType.WEIGHT_CHANGE, 490, "Weight updated after check"));
        sheep1.logHealthEvent(new HealthEvent(HealthEventType.ILLNESS, "High fever"));

        livestockZone.setFeedingProgram(new FeedingProgram("Hay", 15));

        ProductionRecord milk1 = new ProductionRecord(LocalDate.of(2026, 4, 10), ProductionType.MILK, 120, "Liters");
        ProductionRecord milk2 = new ProductionRecord(LocalDate.of(2026, 4, 15), ProductionType.MILK, 90, "Liters");
        ProductionRecord crop1 = new ProductionRecord(LocalDate.of(2026, 5, 1), ProductionType.CROP, 300, "Kg");
        ProductionRecord crop2 = new ProductionRecord(LocalDate.of(2026, 5, 20), ProductionType.CROP, 150, "Kg");
        ProductionRecord fish1 = new ProductionRecord(LocalDate.of(2026, 6, 10), ProductionType.FISH, 500, "Kg");

        livestockZone.addProductionRecord(milk1);
        livestockZone.addProductionRecord(milk2);
        cropZone.addProductionRecord(crop1);
        cropZone.addProductionRecord(crop2);
        aquacultureZone.addProductionRecord(fish1);

        TemperatureSensor tempSensor = new TemperatureSensor("S001", "Z001", new ThresholdRange(15.0, 35.0));
        SoilSensor soilSensor = new SoilSensor("S002", "Z001", new ThresholdRange(6.0, 7.5));
        WaterSensor waterSensor = new WaterSensor("S003", "Z003", new ThresholdRange(5.0, 12.0));
        BiometricSensor biometricSensor = new BiometricSensor("S004", "Z002", new ThresholdRange(37.5, 39.5));
        GpsSensor gpsSensor = new GpsSensor("S005", "Z002");

        cropZone.addSensor(tempSensor);
        cropZone.addSensor(soilSensor);
        aquacultureZone.addSensor(waterSensor);
        livestockZone.addSensor(biometricSensor);
        livestockZone.addSensor(gpsSensor);

        NumericalReading tempR1 = tempSensor.sendReading(25.0);
        NumericalReading tempR2 = tempSensor.sendReading(37.0);
        NumericalReading tempR3 = tempSensor.sendReading(55.0);
        NumericalReading soilR1 = soilSensor.sendReading(6.8);
        NumericalReading soilR2 = soilSensor.sendReading(5.0);
        NumericalReading waterR1 = waterSensor.sendReading(6.8);
        NumericalReading bioR1 = biometricSensor.sendReading(38.0);
        NumericalReading bioR2 = biometricSensor.sendReading(41.0);
        GPSReading gpsR1 = gpsSensor.sendReading(36.72, 3.18);

        alertManager.addAlert(new Alert("A001", tempSensor, tempR2.getValue(), tempR2.getLevel()));
        alertManager.addAlert(new Alert("A002", tempSensor, tempR3.getValue(), tempR3.getLevel()));
        alertManager.addAlert(new Alert("A003", soilSensor, soilR2.getValue(), soilR2.getLevel()));
        alertManager.addAlert(new Alert("A004", biometricSensor, bioR2.getValue(), bioR2.getLevel()));
        alertManager.addAlert(new Alert("A005", waterSensor, waterR1.getValue(), waterR1.getLevel()));

        LocalDateTime start = LocalDateTime.now().minusHours(1);
        LocalDateTime end = LocalDateTime.now().plusHours(1);

        printSection("1. Manage farm zones and entities / Gérer les zones et entités de la ferme");
        farm.display();
        farm.displayAllZones();
        System.out.println("\nCrop zone production summary:");
        cropZone.displayProduction();
        System.out.println("\nLivestock zone production summary:");
        livestockZone.displayProduction();
        System.out.println("\nAquaculture zone production summary:");
        aquacultureZone.displayProduction();

        printSection("2. Manage crops / Gérer les cultures");
        System.out.println(wheat);
        System.out.println(tomato);
        System.out.println(apple);
        System.out.println("\nGrowth history of wheat:");
        wheat.updateGrowthStage(GrowthStageType.GERMINATION);
        wheat.updateGrowthStage(GrowthStageType.GROWTH);
        wheat.updateGrowthStage(GrowthStageType.MATURITY);
        wheat.displayGrowthStageHistory();
        System.out.println("\nCrops by family in crop zone:");
        cropZone.displayCropsByFamily(CropFamily.CEREAL);
        System.out.println("\nCrop status report per zone:");
        farm.displayCropsByFamily(CropFamily.CEREAL);

        printSection("3. Manage animals / Gérer les animaux");
        System.out.println(cow1);
        System.out.println(cow2);
        System.out.println(sheep1);
        System.out.println(goat1);
        System.out.println("\nHealth history of sheep:");
        sheep1.displayHealthEventHistory();
        System.out.println("\nAnimals with status MALADE:");
        livestockZone.displayAnimalsByHealthStatus(HealthStatus.MALADE);
        System.out.println("\nFeeding program for livestock zone:");
        livestockZone.displayFeeding();

        printSection("4. Manage sensors / Gérer les capteurs");
        System.out.println("\nSensor reading history filtered by date range:");
        List<Reading> tempHistoryByDate = tempSensor.getReadingsByDateRange(start, end);
        for (Reading reading : tempHistoryByDate) {
            System.out.println(reading);
        }
        System.out.println("\nDisplay sensor statistics (min, max, average):");
        tempSensor.displayStats();
        System.out.println("\nFilter readings by level (NORMAL, WARNING, CRITICAL):");
        System.out.println("Critical temp readings: " + tempSensor.getReadingsByLevel(AlertLevel.CRITICAL));
        System.out.println("Warning temp readings: " + tempSensor.getReadingsByLevel(AlertLevel.WARNING));
        System.out.println("Normal temp readings: " + tempSensor.getReadingsByLevel(AlertLevel.NORMAL));
        System.out.println("\nZone sensor dashboard:");
        displayZoneDashboard(cropZone);
        displayZoneDashboard(livestockZone);
        displayZoneDashboard(aquacultureZone);

        printSection("5. Manage the alert system / Gérer le système d'alertes");
        System.out.println("\nBrowse the complete alert history:");
        alertManager.displayCompleteAlertHistory();
        System.out.println("\nFilter alerts by zone, sensor type, severity and period:");
        List<Alert> filteredAlerts = alertManager.filterAlerts("Z001", "TemperatureSensor", AlertLevel.CRITICAL, start, end);
        for (Alert alert : filteredAlerts) {
            System.out.println(alert);
        }
        System.out.println("\nCombine multiple filter criteria:");
        List<Alert> combinedFilters = alertManager.filterAlerts("Z001", null, AlertLevel.WARNING, start, end);
        for (Alert alert : combinedFilters) {
            System.out.println(alert);
        }
        System.out.println("\nDisplay active alerts sorted by severity:");
        alertManager.printActiveAlertsBySeverity();

        printSection("6. Production records / Historique des productions");
        System.out.println("Farm production filter for crop type and period:");
        farm.displayProductionByTypeAndPeriod(ProductionType.CROP, LocalDate.of(2026, 5, 1), LocalDate.of(2026, 5, 30));
        System.out.println("\nTotal crop production between 2026-05-01 and 2026-05-30: " +
                cropZone.calculateTotalProduction(LocalDate.of(2026, 5, 1), LocalDate.of(2026, 5, 30)));
        System.out.println("\nCrop production history by date range:");
        System.out.println(cropZone.getProductionHistoryByDateRange(LocalDate.of(2026, 5, 1), LocalDate.of(2026, 5, 30)));

        printSection("7. Zone report / Rapport des zones");
        farm.generateReport();

        printSection("8. Quick sensor readings / Relevés rapides");
        System.out.println("Temp readings: " + tempSensor.getReadings());
        System.out.println("Soil readings: " + soilSensor.getReadings());
        System.out.println("Water readings: " + waterSensor.getReadings());
        System.out.println("Biometric readings: " + biometricSensor.getReadings());
        System.out.println("GPS last reading: " + gpsR1);
    }

    private static void printSection(String title) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println(title);
        System.out.println("==================================================");
    }

    private static void displayZoneDashboard(GeographiqueZone zone) {
        System.out.println("\nZone: " + zone.getCode() + " | " + zone.getName() + " | " + zone.getStatus());
        System.out.println("Sensors in zone: " + zone.getAllSensors().size());

        for (Sensor sensor : zone.getAllSensors()) {
            System.out.println("- " + sensor);
            Reading lastReading = sensor.getReading();
            if (lastReading != null) {
                System.out.println("  Last reading: " + lastReading);
            } else {
                System.out.println("  Last reading: no reading yet");
            }
        }
    }
}
