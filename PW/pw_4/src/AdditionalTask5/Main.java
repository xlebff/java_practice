package AdditionalTask5;

public class Main {
    public static void main(String[] args) {
        Circle[] circles = new Circle[5];

        circles[0] = new Circle(5);
        circles[1] = new Circle(8);
        circles[2] = new Circle(125);
        circles[3] = new Circle(1);
        circles[4] = new Circle(500);

        for (Circle circle : circles) {
            circle.printArea();
        }
    }
}
