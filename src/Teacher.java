public class Teacher extends User {
   private String department;

    // Default Constructor
   public Teacher() {

        super();

        department = "Unknown";
    }

    // Parameterized Constructor
  public Teacher(String name,
                   String userId,
                   String password,
                   String department) {

        super(name, userId, password);

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

    // Teacher Specific Method
   public void requestBooking() {

        System.out.println(
                "Teacher " + getName()
                        + " sent a booking request."
        );
    }

    // Method Overriding
    @Override
    public void showUserInfo() {

        super.showUserInfo();

        System.out.println(
                "Department: "
                        + department
        );
    }

    // toString()
    @Override
    public String toString() {

        return "Teacher{" +
                "name='" + getName() + '\'' +
                ", userId='" + getUserId() + '\'' +
                ", department='" + department + '\'' +
                ", status='" + getStatus() + '\'' +
                '}';
    }
}