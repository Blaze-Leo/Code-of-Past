public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double sizeOf() {
        return Math.PI * radius * radius; 
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shape)) return false; 
        Shape other = (Shape) o;
        return Double.compare(this.sizeOf(), other.sizeOf()) == 0; 
    }
    

    @Override
    public String toString() {
        return "Circle(radius=" + radius + ", area=" + sizeOf() + ")";
    }

}
