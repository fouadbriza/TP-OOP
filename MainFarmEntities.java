import java.time.LocalDate;

public class MainFarmEntities {

        public static void main(String[] args) {

                // 1. FARM CREATION

                System.out.println("========== FARM CREATION ==========");

                Ferme farm = new Ferme("F001", "Green Valley Farm");

                farm.display();

                // 2. ZONE CREATION

                System.out.println("\n========== ZONE CREATION ==========");

                GeographiqueLimit cropBounds = new GeographiqueLimit(36.5, 3.0, 36.8, 3.5);

                GeographiqueLimit livestockBounds = new GeographiqueLimit(36.6, 3.1, 36.9, 3.6);

                GeographiqueLimit aquaBounds = new GeographiqueLimit(36.4, 2.9, 36.7, 3.3);

                CropZone cropZone = new CropZone("North Field", "Z001", cropBounds);

                LivestockZone livestockZone = new LivestockZone("East Pasture", "Z002", livestockBounds,
                                LiveStockType.RUMINANT);

                AquacultureZone aquacultureZone = new AquacultureZone("South Pond", "Z003", aquaBounds);

                farm.addZone(cropZone);

                farm.addZone(livestockZone);

                farm.addZone(aquacultureZone);

                // 3. ZONE OVERVIEW

                System.out.println("\n========== ZONE OVERVIEW ==========");

                farm.displayAllZones();

                // 4. CROP MANAGEMENT

                System.out.println("\n========== CROP MANAGEMENT ==========");

                Crop wheat = new Crop("Wheat", LocalDate.of(2025, 3, 1), LocalDate.of(2025, 7, 15),
                                new SoilRequirement(6.0, 7.5, 40, 70), CropFamily.CEREAL);

                Crop tomato = new Crop("Tomato", LocalDate.of(2025, 4, 10), LocalDate.of(2025, 8, 20),
                                new SoilRequirement(5.5, 7.0, 50, 80), CropFamily.VEGETABLE);

                Crop apple = new Crop("Apple", LocalDate.of(2025, 2, 15), LocalDate.of(2025, 9, 30),
                                new SoilRequirement(5.0, 6.5, 45, 75), CropFamily.FRUIT);

                cropZone.addCrop(wheat);

                cropZone.addCrop(tomato);

                cropZone.addCrop(apple);

                wheat.updateGrowthStage(GrowthStageType.GERMINATION);

                wheat.updateGrowthStage(GrowthStageType.GROWTH);

                wheat.updateGrowthStage(GrowthStageType.MATURITY);

                System.out.println("\nGrowth history of wheat:");

                wheat.displayGrowthStageHistory();

                // 5. ANIMAL MANAGEMENT

                System.out.println("\n========== ANIMAL MANAGEMENT ==========");

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

                System.out.println("\nHealth history of sheep:");

                sheep1.displayHealthEventHistory();

                // 6. FEEDING PROGRAM

                System.out.println("\n========== FEEDING PROGRAM ==========");

                FeedingProgram feedingProgram = new FeedingProgram("Hay", 15);

                livestockZone.setFeedingProgram(feedingProgram);

                livestockZone.displayFeeding();

                // 7. PRODUCTION RECORDS

                System.out.println("\n========== PRODUCTION RECORDS ==========");

                ProductionRecord milk1 = new ProductionRecord(LocalDate.of(2026, 4, 10), ProductionType.MILK, 120,
                                "Liters");

                ProductionRecord milk2 = new ProductionRecord(LocalDate.of(2026, 4, 15), ProductionType.MILK, 90,
                                "Liters");

                ProductionRecord crop1 = new ProductionRecord(LocalDate.of(2026, 5, 1), ProductionType.CROP, 300, "Kg");

                ProductionRecord crop2 = new ProductionRecord(LocalDate.of(2026, 5, 20), ProductionType.CROP, 150,
                                "Kg");

                ProductionRecord fish1 = new ProductionRecord(LocalDate.of(2026, 6, 10), ProductionType.FISH, 500,
                                "Kg");

                livestockZone.addProductionRecord(milk1);

                livestockZone.addProductionRecord(milk2);

                cropZone.addProductionRecord(crop1);

                cropZone.addProductionRecord(crop2);

                aquacultureZone.addProductionRecord(fish1);

                System.out.println("\nLivestock productions:");

                livestockZone.displayProduction();

                System.out.println("\nCrop productions:");

                cropZone.displayProduction();

                System.out.println("\nAquaculture productions:");

                aquacultureZone.displayProduction();

                // 8. ZONE FILTERS

                System.out.println("\n========== ZONE FILTERS ==========");

                cropZone.displayCropsByFamily(CropFamily.CEREAL);

                livestockZone.displayAnimalsByHealthStatus(HealthStatus.MALADE);

                cropZone.displayFilteredProduction(LocalDate.of(2026, 5, 1),
                                LocalDate.of(2026, 5, 30));

                // 9. FARM FILTERS

                System.out.println("\n========== FARM FILTERS ==========");

                farm.displayCropsByFamily(CropFamily.CEREAL);

                farm.displayAnimalsByHealthStatus(HealthStatus.MALADE);

                farm.displayProductionByTypeAndPeriod(ProductionType.CROP, LocalDate.of(2026, 5, 1),
                                LocalDate.of(2026, 5, 30));

                // 10. TOTAL PRODUCTION

                System.out.println("\n========== TOTAL PRODUCTION ==========");

                double total = cropZone.calculateTotalProduction(LocalDate.of(2026, 5, 1), LocalDate.of(2026, 5, 30));

                System.out.println("Total production = " + total);

                // 11. PRODUCTION HISTORY

                System.out.println("\n========== PRODUCTION HISTORY ==========");

                System.out.println(cropZone.getProductionHistoryByDateRange(LocalDate.of(2026, 5, 1),
                                LocalDate.of(2026, 5, 30)));

                // 12. ZONE SUSPENSION

                System.out.println("\n========== ZONE SUSPENSION ==========");

                System.out.println("Before suspension: " + aquacultureZone.getStatus());

                aquacultureZone.suspend();

                System.out.println("After suspension: " + aquacultureZone.getStatus());

                // 13. FARM REPORT

                System.out.println("\n========== FARM REPORT ==========");

                farm.generateReport();
        }
}