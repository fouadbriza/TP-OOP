import java.time.*;

public class Reading {
    protected AlertLevel alertLevel;

    private LocalDateTime dateTime;

    public Reading() {
        this.dateTime = LocalDateTime.now();
        this.alertLevel = null;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public AlertLevel getLevel() {
        return alertLevel;
    }

    public void setLevel(AlertLevel level) {
        this.alertLevel = level;
    }

   /* @Override
    public String toString() {
        return getClass().getSimpleName() + "{level=" + alertLevel + "}";
    }*/


@Override
    public String toString() {
        return  dateTime + " | "+ 
                " | "+
                 alertLevel ;
    }

}