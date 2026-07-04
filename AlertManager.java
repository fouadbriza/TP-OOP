import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlertManager {
    private final List<Alert> alerts;

    public AlertManager() {
        this.alerts = new ArrayList<>();
    }

    public void addAlert(Alert alert) {
        if (alert != null) {
            this.alerts.add(alert);
        }
    }

    public List<Alert> getAlerts() {
        return this.alerts;
    }

    public List<Alert> getAlertsByPeriod(LocalDateTime start, LocalDateTime end) {
        List<Alert> filteredAlerts = new ArrayList<>();

        for (Alert alert : this.alerts) {
            LocalDateTime dateTime = alert.getCreationDate();
            if ((dateTime.isEqual(start) || dateTime.isAfter(start)) &&
                    (dateTime.isEqual(end) || dateTime.isBefore(end))) {
                filteredAlerts.add(alert);
            }
        }

        return filteredAlerts;
    }

    public List<Alert> getAlertsByZone(String zoneCode) {
        List<Alert> filteredAlerts = new ArrayList<>();

        for (Alert alert : this.alerts) {
            Sensor sensor = alert.getSensor();
            if (sensor != null && sensor.getZone().equals(zoneCode)) {
                filteredAlerts.add(alert);
            }
        }

        return filteredAlerts;
    }

    public List<Alert> getAlertsBySensorType(String sensorType) {
        List<Alert> filteredAlerts = new ArrayList<>();

        for (Alert alert : this.alerts) {
            Sensor sensor = alert.getSensor();
            if (sensor != null && sensor.getClass().getSimpleName().equals(sensorType)) {
                filteredAlerts.add(alert);
            }
        }

        return filteredAlerts;
    }

    public List<Alert> getAlertsBySeverity(AlertLevel level) {
        List<Alert> filteredAlerts = new ArrayList<>();

        for (Alert alert : this.alerts) {
            if (alert.getGravityLevel() == level) {
                filteredAlerts.add(alert);
            }
        }

        return filteredAlerts;
    }

    public void printActiveAlertsBySeverity() {
        List<Alert> alerts = new ArrayList<>(this.alerts);

        for (int i = 0; i < alerts.size() - 1; i++) {
            int bestIndex = i;

            for (int j = i + 1; j < alerts.size(); j++) {
                if (severityRank(alerts.get(j).getGravityLevel()) > severityRank(alerts.get(bestIndex).getGravityLevel())) {
                    bestIndex = j;
                }
            }

            if (bestIndex != i) {
                Alert temp = alerts.get(i);
                alerts.set(i, alerts.get(bestIndex));
                alerts.set(bestIndex, temp);
            }
        }

        if (alerts.isEmpty()) {
            System.out.println("No alerts available.");
            return;
        }

        for (Alert alert : alerts) {
            if (alert.isActive()) {
                System.out.println(alert);
            }
        }
    }

    private static int severityRank(AlertLevel level) {
        if (level == AlertLevel.CRITICAL) {
            return 3;
        }
        if (level == AlertLevel.WARNING) {
            return 2;
        }
        return 1;
    }


    //combines multiple filters at once
    public List<Alert> filterAlerts(String zoneCode, String sensorType, AlertLevel level, LocalDateTime start, LocalDateTime end) {
        List<Alert> filteredAlerts = new ArrayList<>();

        for (Alert alert : this.alerts) {
            Sensor sensor = alert.getSensor();
            LocalDateTime dateTime = alert.getCreationDate();

            boolean zoneMatches = zoneCode == null || (sensor != null && sensor.getZone().equals(zoneCode));
            boolean typeMatches = sensorType == null || (sensor != null && sensor.getClass().getSimpleName().equals(sensorType));
            boolean levelMatches = level == null || alert.getGravityLevel() == level;
            boolean periodMatches = (start == null || dateTime.isEqual(start) || dateTime.isAfter(start))
                    && (end == null || dateTime.isEqual(end) || dateTime.isBefore(end));

            if (zoneMatches && typeMatches && levelMatches && periodMatches) {
                filteredAlerts.add(alert);
            }
        }

        return filteredAlerts;
    }

    public void displayCompleteAlertHistory() {
        if (this.alerts.isEmpty()) {
            System.out.println("No alerts available.");
            return;
        }

        for (Alert alert : this.alerts) {
            System.out.println(alert);
        }
    }

    @Override
    public String toString() {
        return "AlertManager{alerts=" + alerts.size() + "}";
    }

}