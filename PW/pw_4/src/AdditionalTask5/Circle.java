package AdditionalTask5;

public class Circle {
    private float radius;

    public Circle(float radius) {
        this.radius = radius;
    }

    public void printArea() {
        System.out.println(MathUtils.circleArea(radius));
    }
}
