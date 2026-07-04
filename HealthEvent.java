public class HealthEvent {

    private HealthEventType type;
    private String description;
    private double weight;

    public HealthEvent(HealthEventType type, String description) {

        if (type != HealthEventType.ILLNESS) {
            throw new IllegalArgumentException(
                    "This constructor is only for illness events");
        }

        this.type = type;
        this.description = description;
    }

    public HealthEvent(
            HealthEventType type,
            double weight,
            String description) {

        if (type != HealthEventType.WEIGHT_CHANGE) {
            throw new IllegalArgumentException(
                    "This constructor is only for weight change events");
        }

        this.type = type;
        this.weight = weight;
        this.description = description;
    }

    public HealthEventType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public double getWeight() {
        return weight;
    }

    public String toString() {
        if (type.equals(HealthEventType.WEIGHT_CHANGE)) {
            return "HealthEvent{"
                    + "type=" + type
                    + ", description='" + description + '\''
                    + ", weight=" + weight
                    + '}';
        } else {
            return "HealthEvent{"
                    + "type=" + type
                    + ", description='" + description + '\''
                    + '}';
        }
    }
}
