public class GpsSensor extends Sensor {

    private double x;
    private double y;


    public GpsSensor(String code, String zone) {
        super(code, zone);
    }

    public void setCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Reading getReading() {
        return super.getReading();
    }
    
    public GPSReading sendReading(double x,double y) {
        GPSReading newReading = new GPSReading(new Coordinate(x, y));
        addReading(newReading);
        return newReading;    
    }



}
