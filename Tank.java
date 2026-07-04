import java.util.*;

public class Tank {
   private List<AquacultureSpecie> aquacultureFields;
   private double volume;
   private List<Sensor> sensors;
   private String id;

   public Tank(double volume, String id) {

      this.aquacultureFields = new ArrayList<>();
      this.sensors = new ArrayList<>();
      this.volume = volume;
      this.id = id;
   }
   public void addAquacultureField(AquacultureSpecie aquacultureSpecie) {
      this.aquacultureFields.add(aquacultureSpecie);
   }
   public void addSensor(Sensor sensor) {
      this.sensors.add(sensor);
   }
   public List<AquacultureSpecie> getAquacultureFields() {
      return aquacultureFields;
   }
   public double getVolume() {
      return volume;
   }  
   public List<Sensor> getSensors() {
      return sensors;
   }
   public String getId() {
      return id;
   }
   public void display() {
      System.out.println("Tank ID: " + id);
      System.out.println("Volume: " + volume + " liters");
      System.out.println("Aquaculture Species:");
      for (AquacultureSpecie specie : aquacultureFields) {
         System.out.println("- " + specie.toString());
      }
      
   }

}
