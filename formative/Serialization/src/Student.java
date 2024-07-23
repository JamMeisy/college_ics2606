import java.io.Serializable;

public class Student implements Serializable {
    String name;
    int id;
    double quiz1, quiz2, quiz3;

    Student(String name, int id, double q1, double q2, double q3) {
        this.name = name;
        this.id = id;
        quiz1 = q1;
        quiz2 = q2;
        quiz3 = q3;
    }
}
