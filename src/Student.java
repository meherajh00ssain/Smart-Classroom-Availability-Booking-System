public class Student extends User {

    private String department;

    // Default Constructor
    public Student() {
        super();
        department = "Unknown";
    }

    // Student information
    public Student(String name, String userId, String department) {
        super(name, userId, "");
        this.department = department;
    }

    // Getter
    public String getDepartment() {
        return department;
    }

    // Setter
    public void setDepartment(String department) {
        this.department = department;
    }

    // Student can check room information
    public void checkRoomAvailability(String roomNumber) {
        System.out.println("Student " + getName()
                + " is checking room " + roomNumber);
    }

    // Method Overriding
    @Override
    public void showUserInfo() {
        super.showUserInfo();
        System.out.println("Department: " + department);
    }
}