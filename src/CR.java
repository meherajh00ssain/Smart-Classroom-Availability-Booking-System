public class CR extends User {
    private String department;
    private String batch;
    private String advisorName;

    // Default Constructor
  public CR() {

        super();

        department = "Unknown";
        batch = "Unknown";
        advisorName = "Unknown";
    }

    // Parameterized Constructor
    public CR(String name,
              String userId,
              String password,
              String department,
              String batch,
              String advisorName) {

        super(name, userId, password);

        this.department = department;
        this.batch = batch;
        this.advisorName = advisorName;
    }
 // Getters
   public String getDepartment() {

        return department;
    }


    public String getBatch() {

        return batch;
    }


    public String getAdvisorName() {

        return advisorName;
    }

    // Setters
  public void setDepartment(String department) {

        this.department = department;
    }


    public void setBatch(String batch) {

        this.batch = batch;
    }


    public void setAdvisorName(String advisorName) {

        this.advisorName = advisorName;
    }
    // CR Specific Method
   public void sendBookingRequest() {

        System.out.println(
                "CR " + getName()
                        + " sent a booking request for Batch "
                        + batch
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

        System.out.println(
                "Batch: "
                        + batch
        );

        System.out.println(
                "Advisor: "
                        + advisorName
        );
    }
    // toString()
  @Override
    public String toString() {

        return "CR{" +
                "name='" + getName() + '\'' +
                ", userId='" + getUserId() + '\'' +
                ", department='" + department + '\'' +
                ", batch='" + batch + '\'' +
                ", advisorName='" + advisorName + '\'' +
                ", status='" + getStatus() + '\'' +
                '}';
    }
}