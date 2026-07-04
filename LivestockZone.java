import java.util.ArrayList;
import java.util.List;

public class LivestockZone extends GeographiqueZone {

    
    private List<Animal> animals;
    private FeedingProgram feedingProgram;
    private LiveStockType livestockType;

    

    public LivestockZone(String code, String name, GeographiqueLimit geographiqueLimit, LiveStockType livestockType) {
        super(code, name, geographiqueLimit);
    

        this.livestockType = livestockType;
        this.animals = new ArrayList<>();
        this.feedingProgram = new FeedingProgram();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void setFeedingProgram(FeedingProgram feedingProgram) {
        this.feedingProgram = feedingProgram;
    }

    public void displayFeeding() {
        System.out.println(feedingProgram);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public FeedingProgram getFeedingProgram() {
        return feedingProgram;
    }

    public LiveStockType getLivestockType() {
        return livestockType;
    }
    public List<Animal> filterAnimalsByHealthStatus(HealthStatus status) {

    List<Animal> filteredAnimals = new ArrayList<>();

    for (Animal animal : animals) {

        if (animal.getHealthStatus() == status) {
            filteredAnimals.add(animal);
        }
    }

    return filteredAnimals;
}
public void displayAnimalsByHealthStatus(HealthStatus status) {

    System.out.println("\nZone: " + name);
    System.out.println("Animals with status: " + status);

    for (Animal animal : animals) {

        if (animal.getHealthStatus() == status) {
            System.out.println(animal);
        }
    }
}

}