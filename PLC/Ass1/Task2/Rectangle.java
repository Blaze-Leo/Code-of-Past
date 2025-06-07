public class Rectangle extends Shape {
    private double width, height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double sizeOf() {
        return width * height; 
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
        return "Rectangle(width=" + width + ", height=" + height + ", area=" + sizeOf() + ")";
    }

    @Override
    public int cmp(Sortable s) {
        if (!(s instanceof Rectangle)) return Double.compare(this.sizeOf(), ((Shape) s).sizeOf());
        Rectangle r = (Rectangle) s;
        return Double.compare(this.sizeOf(), r.sizeOf());
    }
}
