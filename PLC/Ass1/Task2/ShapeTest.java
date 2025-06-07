public class ShapeTest {
    public static void main(String[] args) {
        Sortable[] shapes = {
            new Circle(3),       // Area: ~28.27
            new Square(4),       // Area: 16
            new Rectangle(2, 5), // Area: 10
            new Circle(2),       // Area: ~12.57
            new Square(3)        // Area: 9
        };

        // Sorting using the first shape instance
        (shapes[0]).sort(shapes);

        // Printing sorted shapes
        System.out.println("Sorted Shapes by Area:");
        for (Sortable s : shapes) {
            System.out.println(s);
        }
    }
}
