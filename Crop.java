import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Crop {

    private String name;
    private CropFamily family;

    private LocalDate plantingDate;
    private LocalDate harvestingDate;

    private GrowthStageType growthStage;

    private Map<LocalDate, GrowthStageType> growthStagesHistory;

    private SoilRequirement soilRequirement;

    private List<Sensor> sensors;

    public Crop(String name,LocalDate plantingDate,LocalDate harvestingDate,SoilRequirement soilRequirement,CropFamily family) {

        this.name = name;
        this.family = family;

        this.plantingDate = plantingDate;
        this.harvestingDate = harvestingDate;

        this.soilRequirement = soilRequirement;

        this.growthStage = GrowthStageType.SOWING;

        this.growthStagesHistory = new TreeMap<>();

        growthStagesHistory.put(plantingDate, growthStage);

        this.sensors = new ArrayList<>();
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }

    public void updateGrowthStage(GrowthStageType growthStage) {

        this.growthStage = growthStage;

        growthStagesHistory.put(LocalDate.now(), growthStage);
    }

    public void displayGrowthStageHistory() {

        System.out.println("\nGrowth History of " + name);

        for (Map.Entry<LocalDate, GrowthStageType> entry
                : growthStagesHistory.entrySet()) {

            System.out.println(
                    entry.getKey() +
                    " -> " +
                    entry.getValue()
            );
        }
    }

    public String getName() {
        return name;
    }

    public CropFamily getFamily() {
        return family;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public LocalDate getHarvestingDate() {
        return harvestingDate;
    }

    public GrowthStageType getGrowthStage() {
        return growthStage;
    }

    public SoilRequirement getSoilRequirement() {
        return soilRequirement;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public Map<LocalDate, GrowthStageType> getGrowthStagesHistory() {
        return growthStagesHistory;
    }


    public String toString() {

        return "Crop{" +
                "name='" + name + '\'' +
                ", family=" + family +
                ", plantingDate=" + plantingDate +
                ", harvestingDate=" + harvestingDate +
                ", growthStage=" + growthStage +
                '}';
    }
}