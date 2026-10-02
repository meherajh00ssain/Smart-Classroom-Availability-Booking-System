import java.util.ArrayList;

public class AccountManager {

    private ArrayList<User> users;

    // Constructor
    public AccountManager() {

        users = new ArrayList<>();

        // Fixed Admin account
        Admin admin = new Admin(
                "System Admin",
                "admin",
                "admin123"
        );

        users.add(admin);
    }

    // Register Teacher
    public boolean registerTeacher(String name, String userId,
                                   String password, String department) {

        if (findUser(userId) != null) {
            return false;
        }

        Teacher teacher = new Teacher(
                name,
                userId,
                password,
                department
        );

        teacher.setStatus("Pending");

        users.add(teacher);

        return true;
    }

    // Register CR
    public boolean registerCR(String name, String userId,
                              String password,
                              String department,
                              String batch,
                              String advisorName) {

        if (findUser(userId) != null) {
            return false;
        }

        CR cr = new CR(
                name,
                userId,
                password,
                department,
                batch,
                advisorName
        );

        cr.setStatus("Pending");

        users.add(cr);

        return true;
    }

    // Login
    public User login(String userId, String password) {

        User user = findUser(userId);

        if (user == null) {
            return null;
        }

        // Only approved accounts can login
        if (!user.getStatus().equals("Approved")) {
            return null;
        }

        if (user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    // Find user by User ID
    private User findUser(String userId) {

        for (User user : users) {

            if (user.getUserId().equals(userId)) {
                return user;
            }
        }

        return null;
    }

    // Find user for login status checking
    public User findUserForLogin(String userId) {
        return findUser(userId);
    }

    // Get all users
    public ArrayList<User> getUsers() {
        return users;
    }

    // Get pending accounts
    public ArrayList<User> getPendingUsers() {

        ArrayList<User> pendingUsers =
                new ArrayList<>();

        for (User user : users) {

            if (user.getStatus().equals("Pending")) {
                pendingUsers.add(user);
            }
        }

        return pendingUsers;
    }
}