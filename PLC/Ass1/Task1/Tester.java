public class Tester {
    public static void main(String[] args) {
        // Creating shape objects
        Shape circle1 = new Circle(3);       // Area: ~28.27
        Shape circle2 = new Circle(3);       // Identical to circle1
        Shape square1 = new Square(4);       // Area: 16
        Shape square2 = new Square(3);       // Area: 9
        Shape rectangle1 = new Rectangle(2, 5); // Area: 10
        Shape rectangle2 = new Rectangle(2, 8); // Area: 12

        // Testing toString() method
        System.out.println("Testing toString():");
        System.out.println(circle1);
        System.out.println(square1);
        System.out.println(rectangle1);
        System.out.println();

        // Testing sizeOf() method
        System.out.println("Testing sizeOf():");
        System.out.println("Circle(3) area: " + circle1.sizeOf());
        System.out.println("Square(4) area: " + square1.sizeOf());
        System.out.println("Rectangle(2,5) area: " + rectangle1.sizeOf());
        System.out.println("Rectangle(2,8) area: " + rectangle2.sizeOf());
        System.out.println();

        // Testing equals() method
        System.out.println("Testing equals():");
        System.out.println("circle1.equals(circle2): " + circle1.equals(circle2)); // true
        System.out.println("square1.equals(square2): " + square1.equals(square2)); // false
        System.out.println("rectangle1.equals(rectangle2): " + rectangle1.equals(rectangle2)); // false
        System.out.println("square1.equals(rectangle2): " + square1.equals(rectangle2)); // false
    }
}
