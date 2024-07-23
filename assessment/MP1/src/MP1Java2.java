//1CSC - Jam Meisy F. Tan

import java.util.Scanner;

@FunctionalInterface
interface Area
{
    double getArea(); //To be overridden
}
@FunctionalInterface
interface Volume{
    double getVolume(); //To be overridden
}
abstract class Shape
{
    protected String name;
    abstract String getName();
}
class Circle extends Shape implements Area
{
    protected double radius;
    Circle(double value)
    {
        radius = value;
        name = "Circle";
    }
    @Override
    public double getArea()
    {
        return 3.1416 * radius * radius;
    }
    String getName() {
        return name;
    }
}
class Square extends Shape implements Area
{
    protected double side;
    Square(double value)
    {
        side = value;
        name = "Square";
    }
    @Override
    public double getArea()
    {
        return side * side;
    }

    String getName() {
        return name;
    }
}
class Cylinder extends Circle implements Volume
{
    protected double height;
    Cylinder(double value, double height_value)
    {
        super(value); //Access Circle Constructor
        height = height_value;
        name = "Cylinder";
    }
    @Override
    public double getVolume()
    {
        return 3.1416 * radius * radius * height;
    }

    String getName()
    {
        return name;
    }
}
class Sphere extends Circle implements Volume
{
    Sphere(double value)
    {
        super(value); //Access Circle Constructor
        name = "Sphere";
    }
    @Override
    public double getVolume()
    {
        return 4/3.0 * 3.1416 * radius * radius * radius;
    }

    String getName()
    {
        return name;
    }
}
class Cube extends Square implements Volume
{
    Cube(double value)
    {
        super(value); //Access Square Constructor
        name = "Cube";
    }
    @Override
    public double getVolume()
    {
        return side * side * side;
    }

    String getName()
    {
        return name;
    }
}
class Glome extends Sphere implements Volume
{
    Glome(double value)
    {
        super(value); //Access Circle Constructor
        name = "Glome";
    }
    @Override
    public double getVolume()
    {
        return 0.5 * 3.1416 * 3.1416 * radius * radius * radius * radius;
    }

    String getName()
    {
        return name;
    }
}

public class MP1Java2
{
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args)
    {


        System.out.print("Enter length/radius: ");
        double v1 = validate(in.nextDouble());
        System.out.print("Enter height (Cylinder): ");
        double v2 = validate(in.nextDouble());
        System.out.print("-------------------------\n");

        Circle a = new Circle(v1);
        System.out.printf("[%s]\n", a.getName());
        System.out.printf("Area: %.2f\n", a.getArea());
        System.out.print("-------------------------\n");

        Square b = new Square(v1);
        System.out.printf("[%s]\n", b.getName());
        System.out.printf("Area: %.2f\n", b.getArea());
        System.out.print("-------------------------\n");

        Cylinder c = new Cylinder(v1, v2);
        System.out.printf("[%s]\n", c.getName());
        System.out.printf("Volume: %.2f\n", c.getVolume());
        System.out.print("-------------------------\n");

        Sphere d = new Sphere(v1);
        System.out.printf("[%s]\n", d.getName());
        System.out.printf("Volume: %.2f\n", d.getVolume());
        System.out.print("-------------------------\n");

        Cube e = new Cube(v1);
        System.out.printf("[%s]\n", e.getName());
        System.out.printf("Volume: %.2f\n", e.getVolume());
        System.out.print("-------------------------\n");

        Glome f = new Glome(v1);
        System.out.printf("[%s]\n", f.getName());
        System.out.printf("Volume: %.2f\n", f.getVolume());
        System.out.print("-------------------------\n");
    }
    public static double validate(double x)
    {
        while (x <= 0)
        {
            System.out.println("Invalid Input! Please try again: ");
            x = in.nextDouble();
        }
        return x;
    }
}