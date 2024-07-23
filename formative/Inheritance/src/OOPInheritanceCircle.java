class Point {
    protected int x, y;
    public Point() {}
    public String pointToString() {
        return String.format("(%d, %d)", x, y);
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
class Circle extends Point
{
    protected int x, y;
    protected double radius;
    protected void setRadius(double radius){
        this.radius = radius;
    }
    protected String radiusToString() {
        return String.format("Radius: %.2f", radius);
    }
    protected double calculateArea() {
        return Math.PI * radius * radius;
    }
    protected double calculateCircumference() {
        return Math.PI * 2 * radius;
    }
}
class Cylinder extends Circle
{
    protected int height;
    protected void setHeight(int height) {
        this.height = height;
    }
    protected double calculateVolume() {
        return super.radius * calculateArea();
    }
    protected double calculateSurfaceArea() {
        return 2 * calculateArea() + calculateCircumference() * height;
    }
}
public class OOPInheritanceCircle
{
    public static void main(String[] args)
    {
        Cylinder cyl = new Cylinder();
        cyl.setX(5);
        cyl.setY(7);
        cyl.setRadius(5);
        cyl.setHeight(10);
        System.out.println("Coordinates: " + cyl.pointToString());
        System.out.println("Radius: " + cyl.radiusToString());
        System.out.println("Circumference: " + cyl.calculateCircumference());
        System.out.println("Area: " + cyl.calculateArea());
        System.out.println("Surface Area: " + cyl.calculateSurfaceArea());
        System.out.println("Volume: " + cyl.calculateVolume());
    }
}