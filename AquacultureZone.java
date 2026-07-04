
import java.util.*;

public class AquacultureZone extends GeographiqueZone {

    private List<Tank> tanks;
    GeographiqueLimit geographiqueLimit;

    private FeedingProgram feedingProgram;

    public AquacultureZone(String name, String code, GeographiqueLimit geographiqueLimit) {
        super(name, code, geographiqueLimit);

        this.tanks = new ArrayList<>();
        this.feedingProgram = new FeedingProgram();

    }

    public void addTank(Tank tank) {
        this.tanks.add(tank);
    }

    public void setFeedingProgram(FeedingProgram feedingProgram) {
        this.feedingProgram = feedingProgram;
    }

    public void dispalyFeedingProgram() {
        System.out.println("Feeding Program for Aquaculture Zone " + getName() + ":");
        feedingProgram.toString();

    }
    public List<Tank> getTanks() {
        return tanks;
    }

}
