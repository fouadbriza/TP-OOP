import java.time.LocalDate;
import java.util.*;

public class CropZone extends GeographiqueZone {

    private List<Crop> cropFields;

    public CropZone(String name, String code, GeographiqueLimit bound) {

        super(code, name, bound);

        this.cropFields = new ArrayList<>();
    }

    public void addCrop(Crop crop) {
        this.cropFields.add(crop);
    }

    public List<Crop> getCropFields() {
        return cropFields;
    }

    public List<Crop> filterCropsByFamily(CropFamily family) {

        List<Crop> result = new ArrayList<>();

        for (Crop crop : cropFields) {

            if (crop.getFamily() == family) {
                result.add(crop);
            }
        }

        return result;
    }

    public void displayCropsByFamily(CropFamily family) {

        System.out.println("\nZone: " + name);
        System.out.println("Crops family: " + family);

        for (Crop crop : cropFields) {

            if (crop.getFamily() == family) {
                System.out.println(crop);
            }
        }
    }

}
