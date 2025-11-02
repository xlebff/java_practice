import java.util.*;

class TemperatureChangedEvent extends EventObject {
    private final double newTemperature;

    public TemperatureChangedEvent(Object source, double newTemperature) {
        super(source);
        this.newTemperature = newTemperature;
    }

    public double getNewTemperature() {
        return newTemperature;
    }
}

interface TemperatureListener extends EventListener {
    void temperatureChanged(TemperatureChangedEvent e);
}

class Thermometer {
    private final List<TemperatureListener> listeners = new ArrayList<>();

    public void addTemperatureListener(TemperatureListener l) { listeners.add(l); }
    public void removeTemperatureListener(TemperatureListener l) { listeners.remove(l); }

    public void setTemperature(double value) {
        TemperatureChangedEvent event = new TemperatureChangedEvent(this, value);
        for (TemperatureListener l : listeners) {
            l.temperatureChanged(event);
        }
    }
}

public class TemperatureDemo {
    public static void main(String[] args) {
        Thermometer thermometer = new Thermometer();

        thermometer.addTemperatureListener(e ->
                System.out.println("Температура изменилась: " + e.getNewTemperature() + "°C"));

        thermometer.setTemperature(22.5);
        thermometer.setTemperature(25.0);
    }
}
