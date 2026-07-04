public class SoilRequirement {
    private double phMin;
    private double phMax;
    private double humidityMin;
    private double humidityMax;
    //private double nitrogenMin; // Added based on soil sensor description

    public SoilRequirement(double phMin, double phMax, double humMin, double humMax) {
        this.phMin = phMin;
        this.phMax = phMax;
        this.humidityMin = humMin;
        this.humidityMax = humMax;
    }
    
    
}