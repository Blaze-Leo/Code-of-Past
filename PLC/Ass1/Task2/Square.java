public class Square extends Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double sizeOf() {
        return side * side; 
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
        return "Square(side=" + side + ", area=" + sizeOf() + ")";
    }

    @Override
    public int cmp(Sortable s) {
        if (!(s instanceof Square)) return Double.compare(this.sizeOf(), ((Shape) s).sizeOf());
        Square sq = (Square) s;
        return Double.compare(this.sizeOf(), sq.sizeOf());
    }
}
