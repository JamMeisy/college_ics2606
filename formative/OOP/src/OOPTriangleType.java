import java.util.Scanner;

public class OOPTriangleType {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        Triangle triangle = new Triangle();
        System.out.println("Enter the three sides:");
        triangle.setSide1(validatedInput());
        triangle.setSide2(validatedInput());
        triangle.setSide3(validatedInput());

        System.out.printf("\nTriangle type: %s", triangle.type());
        System.out.printf("\nArea: %.2f", triangle.area());
        System.out.printf("\nPerimeter: %.2f", triangle.perimeter());
    }
    public static double validatedInput()
    {
        double x = in.nextDouble();
        while(x <= 0)
        {
            System.out.print("Invalid input! Try again: ");
            x = in.nextDouble();
        }
        return x;
    }
}
class Triangle
{
    private double side1, side2, side3;
    public void setSide1(double side1) {
        this.side1 = side1;
    }
    public void setSide2(double side2) {
        this.side2 = side2;
    }
    public void setSide3(double side3) {
        this.side3 = side3;
    }
    public String type()
    {
        if (side1 == side2) {
            if (side2 == side3)
                return "Equilateral";
            return "Isosceles";
        }
        if (side2 == side3 || side1 == side3)
            return "Isosceles";
        return "Scalene";
    }
    public double area()
    {
        double s = perimeter()/2.0;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }
    public double perimeter()
    {
        return side1 + side2 + side3;
    }

}
