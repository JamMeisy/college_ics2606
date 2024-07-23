
//Note: Verification method for shapes are referenced at www.geeksforgeeks.org
public class PolymorphismQuadrilateral {
    public static void main(String[] args) {
        Quadrilateral quad;
        quad = new Quadrilateral(new Point(7, 4), new Point(6,9),
                            new Point(3,7), new Point(4,2));
        System.out.printf("%s\n", quad.toString());

        quad = new Trapezoid(new Point(1, 1), new Point(3,7),
                            new Point(10,7), new Point(13,1));
        System.out.printf("%s\n", quad.toString());

        quad = new Parallelogram(new Point(2, 4), new Point(5,10),
                            new Point(11,10), new Point(8,4));
        System.out.printf("%s\n", quad.toString());

        quad = new Rectangle(new Point(-3, 4), new Point(-3,10),
                            new Point(5,10), new Point(5,4));
        System.out.printf("%s\n", quad.toString());

        quad = new Square(new Point(6, 2), new Point(6,6),
                            new Point(10,6), new Point(10,2));
        System.out.printf("%s\n", quad.toString());
    }
}
class Point {
    protected double x, y;
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public String toString() {
        return String.format("(%.1f,%.1f)", x, y);
    }
}
class Quadrilateral {
    protected Point p1, p2, p3, p4;
    Quadrilateral(Point p1, Point p2, Point p3, Point p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }
    //Used for verification
    public double distance(Point point1, Point point2) {
        return Math.hypot(point1.x - point2.x, point1.y - point2.y);
    }
    public String getCoordinatesAsString() {
        return String.format("%s, %s, %s, %s", p1.toString(), p2.toString(), p3.toString(), p4.toString());
    }
    public double getArea() {
        return Math.abs(0.5 *
                ((p1.x * p2.y + p2.x * p3.y + p3.x * p4.y + p4.x * p1.y)
                - (p2.x * p1.y + p3.x * p2.y + p4.x * p3.y + p1.x * p4.y)));
    }
    public boolean verify()
    {
        return !(distance(p1,p2) == 0 || distance(p2,p3) == 0
                || distance(p3,p4) == 0 || distance(p4,p1) == 0);
    }
    public String toString() {
        return String.format("Coordinates of the quadrilateral are: %s" +
                "\nArea is %.1f", getCoordinatesAsString(), getArea());
    }
}
class Trapezoid extends Quadrilateral {
    protected double height;
    Trapezoid(Point p1, Point p2, Point p3, Point p4) {
        super(p1, p2, p3, p4);
        height = Math.abs(p2.y - p1.y);
    }
    public double getHeight() {
        return height;
    }
    public double getSumOfTwoSides() {
        double slope12 = (p2.y - p1.y)/(p2.x - p1.x);
        double slope34 = (p4.y - p3.y)/(p4.x - p3.x);

        if (slope12 == slope34)
            return distance(p1,p2) + distance(p3,p4);
        return distance(p2,p3) + distance(p4,p1);
    }
    public double getArea() {
        return 0.5 * getSumOfTwoSides() * getHeight();
    }
    @Override
    public boolean verify() {
        if (!super.verify())
            return false;
        double slope12 = (p2.y - p1.y)/(p2.x - p1.x);
        double slope23 = (p3.y - p2.y)/(p3.x - p2.x);
        double slope34 = (p4.y - p3.y)/(p4.x - p3.x);
        double slope41 = (p1.y - p4.y)/(p1.x - p4.x);

        return slope12 == slope34 || slope23 == slope41;
    }
    public String toString() {
        if (!verify())
            return super.toString();
        return String.format("Coordinates of the Trapezoid are: %s" +
                "\nHeight is %.1f" +
                "\nArea is %.1f", getCoordinatesAsString(), getHeight(), getArea());
    }
}
class Parallelogram extends Trapezoid {
    Parallelogram(Point p1, Point p2, Point p3, Point p4) {
        super(p1, p2, p3, p4);
    }
    public double getWidth() {
        return Math.min(distance(p1, p2), distance(p2, p3));
    }
    public double getArea() {
        return getWidth() * getHeight();
    }
    @Override
    public boolean verify() {
        if (!super.verify())
            return false;
        return distance(p1, p2) == distance(p3, p4)
                && distance(p2, p3) == distance(p1, p4);
    }
    public String toString() {
        if (!verify())
            return super.toString();
        return String.format("Coordinates of the Parallelogram are: %s" +
                "\nWidth is %.1f" +
                "\nHeight is %.1f" +
                "\nArea is %.1f", getCoordinatesAsString(), getWidth(), getHeight(), getArea());
    }
}
class Rectangle extends Parallelogram {
    Rectangle(Point p1, Point p2, Point p3, Point p4) {
        super(p1, p2, p3, p4);
        super.height = Math.max(distance(p1, p2), distance(p2, p3));
    }
    @Override
    public boolean verify() {
        if (!super.verify())
            return false;
        return distance(p1, p3) == Math.hypot(distance(p1,p2),distance(p2,p3));
    }
    public String toString() {
        if (!verify())
            return super.toString();
        return String.format("Coordinates of the Rectangle are: %s" +
                "\nLength is %.1f" +
                "\nWidth is %.1f" +
                "\nArea is %.1f", getCoordinatesAsString(), getHeight(), getWidth(), getArea());
    }
}
class Square extends Parallelogram {
    Square(Point p1, Point p2, Point p3, Point p4) {
        super(p1, p2, p3, p4);
    }
    @Override
    public boolean verify() {
        if (!super.verify())
            return false;
        return Math.pow(p3.x - p1.x, 2) + Math.pow(p3.y - p1.y, 2)
                == 2 * (Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }
    public String toString() {
        if (!verify())
            return super.toString();
        return String.format("Coordinates of the Square are: %s" +
                "\nSide is %.1f" +
                "\nArea is %.1f", getCoordinatesAsString(), getWidth(), getArea());
    }
}