public class AquacultureSpecie {
    private String name;
    private int numberOfFish;

    public AquacultureSpecie(String name, int numberOfFish) {
        this.name = name;
        this.numberOfFish = numberOfFish;
    }

    public String getName() {
        return name;
    }

    public double getNumberOfFish() {
        return numberOfFish;
    }
    public void addFish(int number) {
        this.numberOfFish += number;
    }
    public void removeFish(int number) {
        if (number <= this.numberOfFish) {
            this.numberOfFish -= number;
        } else {
            System.out.println("Cannot remove more fish than currently present.");
        }
    }
    public String toString() {
        return "AquacultureSpecie{" +
                "name='" + name + '\'' +
                ", numberOfFish=" + numberOfFish +
                '}';
    }

}
