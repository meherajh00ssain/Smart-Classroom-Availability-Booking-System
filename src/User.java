public class User {

    private String name;
    private String userId;
    private String password;
    private String status;

    // Default Constructor
    public User() {
        name = "Unknown";
        userId = "U001";
        password = "1234";
        status = "Approved";
    }

    // Parameterized Constructor
    public User(String name, String userId, String password) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        status = "Pending";
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Show user information
    public void showUserInfo() {
        System.out.println("Name: " + name);
        System.out.println("User ID: " + userId);
        System.out.println("Status: " + status);
    }
}