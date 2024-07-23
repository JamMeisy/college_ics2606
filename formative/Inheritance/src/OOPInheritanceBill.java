class Person
{
    protected String firstName, lastName;
    Person() {}
    Person(String first, String last) {
        firstName = first;
        lastName = last;
    }
    public String nameToString() {
        return firstName + " " + lastName;
    }
    public void setName(String first, String last) {
        firstName = first;
        lastName = last;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
}
class Doctor extends Person
{
    protected String specialty;
    Doctor(String first, String last)
    {
        super(first,last);;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
class Patient extends Person
{
    protected int age;
    Date date_of_birth = new Date();
    Date admit_date = new Date();
    Date discharge_date = new Date();
    Doctor attending;
    Patient(String first, String last, Doctor attending)
    {
        super(first, last);
        this.attending = attending;
    }
}
class Bill extends Patient
{
    protected double pharmacy_charges;
    protected double doctor_fee;
    protected double room_charge;

    Bill(String first, String last, Doctor attending) {
        super(first, last, attending);
    }

    public void setPharmacy_charges(double pharmacy_charges) {
        this.pharmacy_charges = pharmacy_charges;
    }
    public void setDoctor_fee(double doctor_fee) {
        this.doctor_fee = doctor_fee;
    }
    public void setRoom_charge(double room_charge) {
        this.room_charge = room_charge;
    }
}
class Date
{
    private int dMonth, dDay, dYear;
    Date(){}
    Date(int x, int y, int z)
    {
        dMonth = x;
        dDay = y;
        dYear = z;
    }
    public void setDate(int x, int y, int z)
    {
        dMonth = x;
        dDay = y;
        dYear = z;
    }
    public String dateToString() {
        return String.format("%d/%d/%d", dMonth, dDay, dYear);
    }
}
public class OOPInheritanceBill
{
    public static void main(String[] args) {
        Doctor d1 = new Doctor("Bill", "Gamma");
        d1.setSpecialty("Neuroscience");
        Bill b1 = new Bill("Jammes", "Bonde", d1);
        b1.date_of_birth.setDate(2, 24, 2005);
        b1.admit_date.setDate(2, 7, 2023);
        b1.discharge_date.setDate(2, 10, 2023);
        b1.pharmacy_charges = 500;
        b1.doctor_fee = 600;
        b1.room_charge = 100;
        System.out.println("Patient ID: " + b1.nameToString());
        System.out.println("Attending Physician: " + b1.attending.nameToString());
        System.out.println("Birth Date: " + b1.date_of_birth.dateToString());
        System.out.println("Admission Date: " + b1.admit_date.dateToString());
        System.out.println("Discharge Date: " + b1.discharge_date.dateToString());
        System.out.println("Pharmacy Charges: " + b1.pharmacy_charges);
        System.out.println("Doctor Fee: " + b1.doctor_fee);
        System.out.println("Room Charge: " + b1.room_charge);
    }
}
