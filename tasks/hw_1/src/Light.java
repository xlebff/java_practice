public class Light {
    private boolean isOn = false;

    public void turnOn() {
        isOn = true;
        System.out.println("Лампочка включена");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("Лампочка выключена");
    }

    public boolean isOn() {
        return isOn;
    }
}