// GPSReading.java
public class GPSReading extends Reading {
    private Coordinate coordinates;

    public GPSReading(Coordinate coordinate) {
        super();
        this.coordinates = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinates;
    }

    public void setReading(double x,double y) {
        this.coordinates.setValue(x,y);
    }
/* 
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{coordinates=(" + coordinates.getX() + ", " + coordinates.getY() + "), level=" + alertLevel + "}";
    }
*/
    @Override
    public String toString() {
        return  "GPS  --> lat: " +coordinates.getX() +" | lon: " +coordinates.getY();
    }
}