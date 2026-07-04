import java.time.LocalDate;
import java.util.*;

public class ProductionRecord {
    private LocalDate date;
    private double productionValue;
    private ProductionType type;
    private String unit;

    public ProductionRecord(LocalDate date, ProductionType p, double productionValue, String unit) {
        this.date = date;
        this.productionValue = productionValue;
        this.type = p;
        this.unit = unit;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getProductionValue() {
        return productionValue;
    }
    public ProductionType getType() {
        return type;
    }

    public void display() {
        System.out.println("type is:" + type + ".enregestree is " + date + "  ist value is" + productionValue + unit);
    }

    
public String toString() {

    return "ProductionRecord{" +
            "date=" + date +
            ", type=" + type +
            ", value=" + productionValue +
            ", unit='" + unit + '\'' +
            '}';
}
}
