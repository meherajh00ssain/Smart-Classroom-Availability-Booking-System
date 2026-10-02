import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ProjectGUI extends JFrame {

    private AccountManager accountManager;
    private BookingManager bookingManager;
    private Admin admin;
    private JPanel mainPanel;

    // Constructor
    public ProjectGUI() {

        accountManager = new AccountManager();
        bookingManager = new BookingManager();

        admin = (Admin) accountManager.login(
                "admin",
                "admin123"
        );

        addRooms();

        setTitle(
                "Classroom Availability and Booking System"
        );

        setSize(650, 500);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        mainPanel = new JPanel();

        add(mainPanel);

        showWelcomePage();

        setVisible(true);
    }

    // Add Rooms
    private void addRooms() {

        bookingManager.addRoom(
                new LectureRoom(
                        "301",
                        3,
                        40,
                        true
                )
        );

        bookingManager.addRoom(
                new LaboratoryRoom(
                        "Lab-201",
                        2,
                        30,
                        25,
                        "Computer Lab",
                        "Computer, Projector"
                )
        );

        bookingManager.addRoom(
                new MultimediaLectureRoom(
                        "302",
                        3,
                        50,
                        true,
                        true
                )
        );
    }

    // Enter navigation
    private void addEnterMove(JComponent current, JComponent next) {
        current.setFocusable(true);
        current.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    e.consume();
                    next.requestFocusInWindow();
                }
            }
        });
    }

    // Enter navigation compatibility
    private void addEnterNavigation(JComponent current, JComponent next) {
        addEnterMove(current, next);
    }

    // Enter click for buttons
    private void addEnterClick(JButton button) {
        button.setFocusable(true);
        button.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    e.consume();
                    button.doClick();
                }
            }
        });
    }

    // Welcome Page
    private void showWelcomePage() {

        mainPanel.removeAll();

        mainPanel.setLayout(
                new GridLayout(
                        6,
                        1,
                        10,
                        10
                )
        );

        JLabel title =
                new JLabel(
                        "Classroom Availability and Booking System",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        JButton adminButton =
                new JButton("Admin Login");

        JButton userButton =
                new JButton("Teacher / CR Login");

        JButton registerButton =
                new JButton("Register");

        JButton studentButton =
                new JButton("Student Access");

        JButton exitButton =
                new JButton("Exit");

        mainPanel.add(title);
        mainPanel.add(adminButton);
        mainPanel.add(userButton);
        mainPanel.add(registerButton);
        mainPanel.add(studentButton);
        mainPanel.add(exitButton);

        adminButton.addActionListener(
                e -> showAdminLoginPage()
        );

        userButton.addActionListener(
                e -> showUserLoginPage()
        );

        registerButton.addActionListener(
                e -> showRegisterPage()
        );

        studentButton.addActionListener(
                e -> showStudentPanel()
        );

        exitButton.addActionListener(
                e -> System.exit(0)
        );

        refreshPanel();
    }

    // Admin Login
    private void showAdminLoginPage() {

        mainPanel.removeAll();

        mainPanel.setLayout(
                new GridLayout(
                        5,
                        1,
                        10,
                        10
                )
        );

        JLabel title =
                new JLabel(
                        "Admin Login",
                        SwingConstants.CENTER
                );

        JTextField userIdField =
                new JTextField();

        JPasswordField passwordField =
                new JPasswordField();

        userIdField.setBorder(
                BorderFactory.createTitledBorder(
                        "Admin User ID"
                )
        );

        passwordField.setBorder(
                BorderFactory.createTitledBorder(
                        "Password"
                )
        );

        JButton loginButton =
                new JButton("Login");

        JButton backButton =
                new JButton("Back");

        mainPanel.add(title);
        mainPanel.add(userIdField);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(backButton);

        addEnterNavigation(
                userIdField,
                passwordField
        );

        addEnterNavigation(
                passwordField,
                loginButton
        );

        loginButton.addActionListener(e -> {

            String userId =
                    userIdField.getText().trim();

            String password =
                    new String(
                            passwordField.getPassword()
                    );

            if (userId.equals("admin")
                    && password.equals("admin123")) {

                showAdminPanel();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Admin ID or Password."
                );
            }
        });

        backButton.addActionListener(
                e -> showWelcomePage()
        );

        refreshPanel();

        userIdField.requestFocusInWindow();
    }

    // Teacher and CR Login
    private void showUserLoginPage() {

        mainPanel.removeAll();

        mainPanel.setLayout(
                new GridLayout(
                        5,
                        1,
                        10,
                        10
                )
        );

        JLabel title =
                new JLabel(
                        "Teacher / CR Login",
                        SwingConstants.CENTER
                );

        JTextField userIdField =
                new JTextField();

        JPasswordField passwordField =
                new JPasswordField();

        userIdField.setBorder(
                BorderFactory.createTitledBorder(
                        "User ID"
                )
        );

        passwordField.setBorder(
                BorderFactory.createTitledBorder(
                        "Password"
                )
        );

        JButton loginButton =
                new JButton("Login");

        JButton backButton =
                new JButton("Back");

        mainPanel.add(title);
        mainPanel.add(userIdField);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(backButton);

        addEnterNavigation(
                userIdField,
                passwordField
        );

        addEnterNavigation(
                passwordField,
                loginButton
        );

        loginButton.addActionListener(e -> {

            String userId =
                    userIdField.getText().trim();

            String password =
                    new String(
                            passwordField.getPassword()
                    );

            User user =
                    accountManager.login(
                            userId,
                            password
                    );

            if (user == null) {

                User checkUser =
                        accountManager.findUserForLogin(
                                userId
                        );

                if (checkUser != null
                        && checkUser.getStatus()
                        .equals("Pending")) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Your account is waiting for Admin approval."
                    );

                } else if (checkUser != null
                        && checkUser.getStatus()
                        .equals("Rejected")) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Your account has been rejected."
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Invalid User ID or Password."
                    );
                }

                return;
            }

            if (user instanceof Teacher) {

                showTeacherPanel(
                        (Teacher) user
                );

            } else if (user instanceof CR) {

                showCRPanel(
                        (CR) user
                );
            }
        });

        backButton.addActionListener(
                e -> showWelcomePage()
        );

        refreshPanel();

        userIdField.requestFocusInWindow();
    }

    // Register
    private void showRegisterPage() {

        mainPanel.removeAll();

        mainPanel.setLayout(
                new GridLayout(
                        10,
                        1,
                        5,
                        5
                )
        );

        JLabel title =
                new JLabel(
                        "Create Account",
                        SwingConstants.CENTER
                );

        JComboBox<String> roleBox =
                new JComboBox<>(
                        new String[]{
                                "Teacher",
                                "CR"
                        }
                );

        JTextField nameField =
                new JTextField();

        JTextField userIdField =
                new JTextField();

        JPasswordField passwordField =
                new JPasswordField();

        JTextField departmentField =
                new JTextField();

        JTextField batchField =
                new JTextField();

        JTextField advisorField =
                new JTextField();

        JButton registerButton =
                new JButton("Register");

        JButton backButton =
                new JButton("Back");

        roleBox.setBorder(
                BorderFactory.createTitledBorder(
                        "Account Type"
                )
        );

        nameField.setBorder(
                BorderFactory.createTitledBorder(
                        "Name"
                )
        );

        userIdField.setBorder(
                BorderFactory.createTitledBorder(
                        "User ID"
                )
        );

        passwordField.setBorder(
                BorderFactory.createTitledBorder(
                        "Password"
                )
        );

        departmentField.setBorder(
                BorderFactory.createTitledBorder(
                        "Department"
                )
        );

        batchField.setBorder(
                BorderFactory.createTitledBorder(
                        "Batch"
                )
        );

        advisorField.setBorder(
                BorderFactory.createTitledBorder(
                        "Advisor Name"
                )
        );

        mainPanel.add(title);
        mainPanel.add(roleBox);
        mainPanel.add(nameField);
        mainPanel.add(userIdField);
        mainPanel.add(passwordField);
        mainPanel.add(departmentField);
        mainPanel.add(batchField);
        mainPanel.add(advisorField);
        mainPanel.add(registerButton);
        mainPanel.add(backButton);

        batchField.setVisible(false);
        advisorField.setVisible(false);
        addEnterClick(registerButton);
        addEnterClick(backButton);
        addEnterMove(roleBox, nameField);
        addEnterMove(nameField, userIdField);
        addEnterMove(userIdField, passwordField);
        addEnterMove(passwordField, departmentField);
        departmentField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    e.consume();
                    if (roleBox.getSelectedItem().equals("CR")) {
                        batchField.setVisible(true);
                        advisorField.setVisible(true);
                        mainPanel.revalidate();
                        mainPanel.repaint();
                        batchField.requestFocusInWindow();
                    } else {
                        registerButton.requestFocusInWindow();
                        registerButton.doClick();
                    }
                }
            }
        });
        addEnterMove(batchField, advisorField);
        addEnterMove(advisorField, registerButton);
        roleBox.addActionListener(e -> {
            boolean isCR = roleBox.getSelectedItem().equals("CR");
            batchField.setVisible(isCR);
            advisorField.setVisible(isCR);
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        SwingUtilities.invokeLater(() -> roleBox.requestFocusInWindow());

        registerButton.addActionListener(e -> {

            String role =
                    roleBox.getSelectedItem()
                            .toString();

            String name =
                    nameField.getText().trim();

            String userId =
                    userIdField.getText().trim();

            String password =
                    new String(
                            passwordField.getPassword()
                    );

            String department =
                    departmentField.getText().trim();

            if (name.isEmpty()
                    || userId.isEmpty()
                    || password.trim().isEmpty()
                    || department.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all required fields."
                );

                return;
            }

            boolean result;

            if (role.equals("Teacher")) {

                result =
                        accountManager.registerTeacher(
                                name,
                                userId,
                                password,
                                department
                        );

            } else {

                String batch =
                        batchField.getText().trim();

                String advisor =
                        advisorField.getText().trim();

                if (batch.isEmpty()
                        || advisor.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter Batch and Advisor Name."
                    );

                    return;
                }

                result =
                        accountManager.registerCR(
                                name,
                                userId,
                                password,
                                department,
                                batch,
                                advisor
                        );
            }

            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Registration successful.\n"
                                + "Wait for Admin approval."
                );

                showWelcomePage();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "This User ID is already used."
                );
            }
        });

        backButton.addActionListener(
                e -> showWelcomePage()
        );

        refreshPanel();
        roleBox.requestFocusInWindow();
    }

    // Admin Panel
    private void showAdminPanel() {

        mainPanel.removeAll();

        mainPanel.setLayout(
                new GridLayout(
                        7,
                        1,
                        10,
                        10
                )
        );

        JLabel title =
                new JLabel(
                        "Admin Panel",
                        SwingConstants.CENTER
                );

        JButton accountButton =
                new JButton("Account Requests");

        JButton bookingButton =
                new JButton("Booking Requests");

        JButton roomButton =
                new JButton("View Rooms");

        JButton equipmentButton =
                new JButton(
                        "Update Room Equipment"
                );

        JButton scheduleButton =
                new JButton(
                        "Manage Routine"
                );

        JButton logoutButton =
                new JButton("Logout");

        mainPanel.add(title);
        mainPanel.add(accountButton);
        mainPanel.add(bookingButton);
        mainPanel.add(roomButton);
        mainPanel.add(equipmentButton);
        mainPanel.add(scheduleButton);
        mainPanel.add(logoutButton);

        accountButton.addActionListener(
                e -> showAccountRequests()
        );

        bookingButton.addActionListener(
                e -> showBookingRequests()
        );

        roomButton.addActionListener(
                e -> showRooms()
        );

        equipmentButton.addActionListener(
                e -> updateRoomEquipment()
        );

        scheduleButton.addActionListener(
                e -> showAdminScheduleManagement()
        );

        logoutButton.addActionListener(
                e -> showWelcomePage()
        );

        refreshPanel();
    }

    // Update room equipment
    private void updateRoomEquipment() {

        String[] roomOptions = {
                "Lecture Room 301",
                "Multimedia Lecture Room 302",
                "Laboratory Room Lab-201"
        };

        String selectedRoom =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Select room:",
                        "Update Room Equipment",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        roomOptions,
                        roomOptions[0]
                );

        if (selectedRoom == null) {
            return;
        }

        Room selectedRoomObject = null;

        for (Room room :
                bookingManager.getRooms()) {

            if (selectedRoom.equals(
                    "Lecture Room 301")
                    && room instanceof LectureRoom
                    && !(room instanceof MultimediaLectureRoom)
                    && room.getRoomNumber()
                    .equals("301")) {

                selectedRoomObject = room;
                break;
            }

            if (selectedRoom.equals(
                    "Multimedia Lecture Room 302")
                    && room instanceof MultimediaLectureRoom
                    && room.getRoomNumber()
                    .equals("302")) {

                selectedRoomObject = room;
                break;
            }

            if (selectedRoom.equals(
                    "Laboratory Room Lab-201")
                    && room instanceof LaboratoryRoom
                    && room.getRoomNumber()
                    .equals("Lab-201")) {

                selectedRoomObject = room;
                break;
            }
        }

        if (selectedRoomObject == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Room not found."
            );

            return;
        }

        String[] equipmentOptions;

        if (selectedRoomObject
                instanceof LaboratoryRoom) {

            equipmentOptions =
                    new String[]{
                            "Projector",
                            "Computer"
                    };

        } else {

            equipmentOptions =
                    new String[]{
                            "Projector"
                    };
        }

        String selectedEquipment =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Select equipment:",
                        "Update Equipment",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        equipmentOptions,
                        equipmentOptions[0]
                );

        if (selectedEquipment == null) {
            return;
        }

        String currentStatus = "Available";

        if (selectedRoomObject
                instanceof LectureRoom) {

            LectureRoom room =
                    (LectureRoom)
                            selectedRoomObject;

            currentStatus =
                    room.getProjectorStatus();

        } else if (selectedRoomObject
                instanceof LaboratoryRoom) {

            LaboratoryRoom lab =
                    (LaboratoryRoom)
                            selectedRoomObject;

            if (selectedEquipment
                    .equals("Projector")) {

                currentStatus =
                        lab.getProjectorStatus();

            } else {

                currentStatus =
                        lab.getComputerStatus();
            }
        }

        String[] statusOptions = {
                "Available",
                "Broken"
        };

        String selectedStatus =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Current Status: "
                                + currentStatus
                                + "\nSelect new status:",
                        "Equipment Status",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        statusOptions,
                        currentStatus
                );

        if (selectedStatus == null) {
            return;
        }

        if (selectedRoomObject
                instanceof LectureRoom) {

            admin.updateEquipmentStatus(
                    (LectureRoom)
                            selectedRoomObject,
                    selectedEquipment,
                    selectedStatus
            );

        } else if (selectedRoomObject
                instanceof LaboratoryRoom) {

            admin.updateEquipmentStatus(
                    (LaboratoryRoom)
                            selectedRoomObject,
                    selectedEquipment,
                    selectedStatus
            );
        }

        JOptionPane.showMessageDialog(
                this,
                "Equipment status updated successfully."
        );
    }

    // Account requests
    private void showAccountRequests() {

        ArrayList<User> pending =
                accountManager.getPendingUsers();

        if (pending.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No pending account requests."
            );

            return;
        }

        String[] options =
                new String[pending.size()];

        for (int i = 0;
             i < pending.size();
             i++) {

            User user =
                    pending.get(i);

            String role = "User";

            if (user instanceof Teacher) {
                role = "Teacher";
            } else if (user instanceof CR) {
                role = "CR";
            }

            options[i] =
                    role
                            + " - "
                            + user.getName()
                            + " - "
                            + user.getUserId();
        }

        String selected =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Select an account:",
                        "Account Requests",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

        if (selected == null) {
            return;
        }

        int index =
                findSelectedIndex(
                        options,
                        selected
                );

        User user =
                pending.get(index);

        String[] actions = {
                "Approve",
                "Reject",
                "Cancel"
        };

        int choice =
                JOptionPane.showOptionDialog(
                        this,
                        "Name: "
                                + user.getName()
                                + "\nUser ID: "
                                + user.getUserId()
                                + "\nStatus: "
                                + user.getStatus(),
                        "Account Request",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        actions,
                        actions[0]
                );

        if (choice == 0) {

            admin.approveAccount(user);

            JOptionPane.showMessageDialog(
                    this,
                    "Account approved."
            );

        } else if (choice == 1) {

            admin.rejectAccount(user);

            JOptionPane.showMessageDialog(
                    this,
                    "Account rejected."
            );
        }
    }

    // Booking requests
    private void showBookingRequests() {

        ArrayList<Booking> bookings =
                bookingManager.getBookings();

        if (bookings.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No booking requests."
            );

            return;
        }

        String[] options =
                new String[bookings.size()];

        for (int i = 0;
             i < bookings.size();
             i++) {

            Booking booking =
                    bookings.get(i);

            options[i] =
                    booking.getBookingId()
                            + " - "
                            + booking.getTeacherName()
                            + " - Room "
                            + booking.getRoomNumber()
                            + " - "
                            + booking.getStartTime()
                            + " to "
                            + booking.getEndTime()
                            + " - "
                            + booking.getStatus();
        }

        String selected =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Select a booking:",
                        "Booking Requests",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

        if (selected == null) {
            return;
        }

        Booking booking =
                bookings.get(
                        findSelectedIndex(
                                options,
                                selected
                        )
                );

        String[] actions = {
                "Approve",
                "Reject",
                "Cancel"
        };

        int choice =
                JOptionPane.showOptionDialog(
                        this,
                        booking.toString(),
                        "Booking Request",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        actions,
                        actions[0]
                );

        if (choice == 0) {

            if (booking.getStatus()
                    .equals("Approved")) {

                JOptionPane.showMessageDialog(
                        this,
                        "This booking is already approved."
                );

                return;
            }

            boolean approved =
                    admin.approveBooking(
                            booking,
                            bookingManager
                    );

            if (approved) {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking approved."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Cannot approve this booking.\n"
                                + "Room is already booked "
                                + "or has a class schedule.",
                        "Booking Conflict",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } else if (choice == 1) {

            admin.rejectBooking(booking);

            JOptionPane.showMessageDialog(
                    this,
                    "Booking rejected."
            );
        }
    }

    // Admin schedule menu
    private void showAdminScheduleManagement() {

        String[] options = {
                "Add Routine",
                "View Full Routine",
                "Edit Routine",
                "Cancel Class",
                "Delay Class",
                "Delete Routine",
                "Back"
        };

        String selected =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Select an option:",
                        "Routine Management",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

        if (selected == null
                || selected.equals("Back")) {

            return;
        }

        if (selected.equals(
                "Add Routine")) {

            addClassScheduleByAdmin();

        } else if (selected.equals(
                "View Full Routine")) {

            viewAllClassSchedules();

        } else if (selected.equals(
                "Edit Routine")) {

            editRoutine();

        } else if (selected.equals(
                "Cancel Class")) {

            adminCancelClass();

        } else if (selected.equals(
                "Delay Class")) {

            adminDelayClass();

        } else if (selected.equals(
                "Delete Routine")) {

            deleteRoutine();
        }
    }

    // Add class schedule by Admin
    private void addClassScheduleByAdmin() {

        String batch =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Batch:"
                );

        if (batch == null
                || batch.trim().isEmpty()) {
            return;
        }

        String teacherName =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Teacher Name:"
                );

        if (teacherName == null
                || teacherName.trim().isEmpty()) {
            return;
        }

        String roomNumber =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Room Number:"
                );

        if (roomNumber == null
                || roomNumber.trim().isEmpty()) {
            return;
        }

        String date =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Date (dd-MM-yyyy):",
                        LocalDate.now().format(
                                DateTimeFormatter.ofPattern(
                                        "dd-MM-yyyy"
                                )
                        )
                );

        if (date == null
                || date.trim().isEmpty()) {
            return;
        }

        String startTime =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Start Time (HH:mm):"
                );

        if (startTime == null
                || startTime.trim().isEmpty()) {
            return;
        }

        String endTime =
                JOptionPane.showInputDialog(
                        this,
                        "Enter End Time (HH:mm):"
                );

        if (endTime == null
                || endTime.trim().isEmpty()) {
            return;
        }

        try {

            validateDate(date);

            int start =
                    convertTime(startTime);

            int end =
                    convertTime(endTime);

            if (start >= end) {

                JOptionPane.showMessageDialog(
                        this,
                        "End time must be after start time."
                );

                return;
            }

            boolean available =
                    bookingManager.checkAvailability(
                            roomNumber.trim(),
                            date.trim(),
                            startTime.trim(),
                            endTime.trim()
                    );

            if (!available) {

                JOptionPane.showMessageDialog(
                        this,
                        "Room is not available for this time."
                );

                return;
            }

            Room selectedRoom =
                    bookingManager.searchRoom(
                            roomNumber.trim()
                    );

            if (selectedRoom == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Room not found."
                );

                return;
            }

            String actualRoomNumber =
                    selectedRoom.getRoomNumber();

            ClassSchedule schedule =
                    new ClassSchedule(
                            batch.trim(),
                            teacherName.trim(),
                            actualRoomNumber,
                            date.trim(),
                            startTime.trim(),
                            endTime.trim()
                    );

            bookingManager
                    .getScheduleManager()
                    .addSchedule(schedule);

            JOptionPane.showMessageDialog(
                    this,
                    "Class Schedule Added Successfully.\n\n"
                            + "Batch: "
                            + batch
                            + "\nTeacher: "
                            + teacherName
                            + "\nRoom: "
                            + roomNumber
                            + "\nDate: "
                            + date
                            + "\nTime: "
                            + startTime
                            + " - "
                            + endTime
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid date or time.\n"
                            + "Date: dd-MM-yyyy\n"
                            + "Time: HH:mm"
            );
        }
    }

    // View all class schedules
    private void viewAllClassSchedules() {

        ArrayList<ClassSchedule> schedules =
                bookingManager
                        .getScheduleManager()
                        .getSchedules();

        if (schedules.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No class schedules found."
            );

            return;
        }

        StringBuilder text =
                new StringBuilder();

        for (ClassSchedule schedule :
                schedules) {

            text.append(
                    "Batch: "
            ).append(
                    schedule.getBatch()
            ).append("\n");

            text.append(
                    "Teacher: "
            ).append(
                    schedule.getTeacherName()
            ).append("\n");

            text.append(
                    "Room: "
            ).append(
                    schedule.getRoomNumber()
            ).append("\n");

            text.append(
                    "Date: "
            ).append(
                    schedule.getDate()
            ).append("\n");

            text.append(
                    "Time: "
            ).append(
                    schedule.getStartTime()
            ).append(
                    " - "
            ).append(
                    schedule.getEndTime()
            ).append("\n");

            text.append(
                    "Status: "
            ).append(
                    schedule.getStatus()
            ).append("\n");

            if (!schedule.getUpdateReason()
                    .isEmpty()) {

                text.append(
                        "Reason: "
                ).append(
                        schedule.getUpdateReason()
                ).append("\n");
            }

            text.append(
                    "-----------------------------\n"
            );
        }

        JTextArea area =
                new JTextArea(
                        text.toString()
                );

        area.setEditable(false);

        JScrollPane scrollPane =
                new JScrollPane(area);

        scrollPane.setPreferredSize(
                new Dimension(
                        550,
                        350
                )
        );

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "All Class Schedules",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Admin cancels class
    private void adminCancelClass() {

        ArrayList<ClassSchedule> schedules =
                bookingManager
                        .getScheduleManager()
                        .getSchedules();

        if (schedules.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No class schedules found."
            );

            return;
        }

        String[] options =
                new String[
                        schedules.size()
                        ];

        for (int i = 0;
             i < schedules.size();
             i++) {

            ClassSchedule schedule =
                    schedules.get(i);

            options[i] =
                    schedule.getBatch()
                            + " - "
                            + schedule.getTeacherName()
                            + " - Room "
                            + schedule.getRoomNumber()
                            + " - "
                            + schedule.getDate()
                            + " - "
                            + schedule.getStartTime()
                            + " to "
                            + schedule.getEndTime()
                            + " - "
                            + schedule.getStatus();
        }

        String selected =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Select class:",
                        "Cancel Class",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

        if (selected == null) {
            return;
        }

        ClassSchedule schedule =
                schedules.get(
                        findSelectedIndex(
                                options,
                                selected
                        )
                );

        if (schedule.getStatus()
                .equalsIgnoreCase(
                        "Cancelled"
                )) {

            JOptionPane.showMessageDialog(
                    this,
                    "This class is already cancelled."
            );

            return;
        }

        String reason =
                JOptionPane.showInputDialog(
                        this,
                        "Enter cancellation reason:"
                );

        if (reason == null
                || reason.trim().isEmpty()) {
            return;
        }

        schedule.cancelClass(
                reason.trim()
        );

        JOptionPane.showMessageDialog(
                this,
                "Class cancelled successfully.\n\n"
                        + "Room "
                        + schedule.getRoomNumber()
                        + " is now AVAILABLE.\n"
                        + "Date: "
                        + schedule.getDate()
                        + "\nTime: "
                        + schedule.getStartTime()
                        + " - "
                        + schedule.getEndTime()
        );
    }

    // Admin delays class
    private void adminDelayClass() {

        ArrayList<ClassSchedule> schedules =
                bookingManager
                        .getScheduleManager()
                        .getSchedules();

        if (schedules.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No class schedules found."
            );

            return;
        }

        String[] options =
                new String[
                        schedules.size()
                        ];

        for (int i = 0;
             i < schedules.size();
             i++) {

            ClassSchedule schedule =
                    schedules.get(i);

            options[i] =
                    schedule.getBatch()
                            + " - "
                            + schedule.getTeacherName()
                            + " - Room "
                            + schedule.getRoomNumber()
                            + " - "
                            + schedule.getDate()
                            + " - "
                            + schedule.getStartTime()
                            + " to "
                            + schedule.getEndTime();
        }

        String selected =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Select class:",
                        "Delay Class",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

        if (selected == null) {
            return;
        }

        ClassSchedule schedule =
                schedules.get(
                        findSelectedIndex(
                                options,
                                selected
                        )
                );

        if (schedule.getStatus()
                .equalsIgnoreCase(
                        "Cancelled"
                )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cancelled class cannot be delayed."
            );

            return;
        }

        delaySchedule(schedule);
    }

    // Delay schedule
    private void delaySchedule(
            ClassSchedule schedule) {

        String newStartTime =
                JOptionPane.showInputDialog(
                        this,
                        "Enter new start time (HH:mm):"
                );

        if (newStartTime == null
                || newStartTime.trim().isEmpty()) {
            return;
        }

        String newEndTime =
                JOptionPane.showInputDialog(
                        this,
                        "Enter new end time (HH:mm):"
                );

        if (newEndTime == null
                || newEndTime.trim().isEmpty()) {
            return;
        }

        String reason =
                JOptionPane.showInputDialog(
                        this,
                        "Enter delay reason:"
                );

        if (reason == null
                || reason.trim().isEmpty()) {
            return;
        }

        try {

            int newStart =
                    convertTime(newStartTime);

            int newEnd =
                    convertTime(newEndTime);

            if (newStart >= newEnd) {

                JOptionPane.showMessageDialog(
                        this,
                        "End time must be after start time."
                );

                return;
            }

            String oldStart =
                    schedule.getStartTime();

            String oldEnd =
                    schedule.getEndTime();

            schedule.cancelClass(
                    "Temporary availability check"
            );

            boolean available =
                    bookingManager.checkAvailability(
                            schedule.getRoomNumber(),
                            schedule.getDate(),
                            newStartTime.trim(),
                            newEndTime.trim()
                    );

            schedule.setStatus(
                    "Scheduled"
            );

            schedule.setUpdateReason(
                    ""
            );

            if (!available) {

                JOptionPane.showMessageDialog(
                        this,
                        "Room is not available at the new time.\n"
                                + "Old schedule remains unchanged."
                );

                return;
            }

            schedule.delayClass(
                    newStartTime.trim(),
                    newEndTime.trim(),
                    reason.trim()
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Class delayed successfully.\n\n"
                            + "Old Time: "
                            + oldStart
                            + " - "
                            + oldEnd
                            + "\nNew Time: "
                            + newStartTime
                            + " - "
                            + newEndTime
                            + "\n\n"
                            + "Old time is now AVAILABLE."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid time format.\nUse HH:mm"
            );
        }
    }

    private void editRoutine() {

        ArrayList<ClassSchedule> schedules =
                bookingManager
                        .getScheduleManager()
                        .getSchedules();

        if (schedules.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "No routines found."
            );
            return;
        }

        String[] options = new String[schedules.size()];

        for (int i = 0; i < schedules.size(); i++) {
            ClassSchedule schedule = schedules.get(i);
            options[i] = schedule.getBatch()
                    + " - " + schedule.getTeacherName()
                    + " - Room " + schedule.getRoomNumber()
                    + " - " + schedule.getDate()
                    + " - " + schedule.getStartTime()
                    + " to " + schedule.getEndTime();
        }

        String selected = (String) JOptionPane.showInputDialog(
                this,
                "Select routine to edit:",
                "Edit Routine",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (selected == null) {
            return;
        }

        ClassSchedule oldSchedule = schedules.get(
                findSelectedIndex(options, selected)
        );

        String batch = JOptionPane.showInputDialog(
                this,
                "Enter Batch:",
                oldSchedule.getBatch()
        );
        if (batch == null || batch.trim().isEmpty()) return;

        String teacherName = JOptionPane.showInputDialog(
                this,
                "Enter Teacher Name:",
                oldSchedule.getTeacherName()
        );
        if (teacherName == null || teacherName.trim().isEmpty()) return;

        String roomNumber = JOptionPane.showInputDialog(
                this,
                "Enter Room Number:",
                oldSchedule.getRoomNumber()
        );
        if (roomNumber == null || roomNumber.trim().isEmpty()) return;

        String date = JOptionPane.showInputDialog(
                this,
                "Enter Date (dd-MM-yyyy):",
                oldSchedule.getDate()
        );
        if (date == null || date.trim().isEmpty()) return;

        String startTime = JOptionPane.showInputDialog(
                this,
                "Enter Start Time (HH:mm):",
                oldSchedule.getStartTime()
        );
        if (startTime == null || startTime.trim().isEmpty()) return;

        String endTime = JOptionPane.showInputDialog(
                this,
                "Enter End Time (HH:mm):",
                oldSchedule.getEndTime()
        );
        if (endTime == null || endTime.trim().isEmpty()) return;

        try {
            validateDate(date);

            int start = convertTime(startTime);
            int end = convertTime(endTime);

            if (start >= end) {
                JOptionPane.showMessageDialog(
                        this,
                        "End time must be after start time."
                );
                return;
            }

            schedules.remove(oldSchedule);

            boolean available = bookingManager.checkAvailability(
                    roomNumber.trim(),
                    date.trim(),
                    startTime.trim(),
                    endTime.trim()
            );

            if (!available) {
                schedules.add(oldSchedule);
                JOptionPane.showMessageDialog(
                        this,
                        "New room/time is not available.\nOld routine remains unchanged."
                );
                return;
            }

            Room selectedRoom =
                    bookingManager.searchRoom(
                            roomNumber.trim()
                    );

            if (selectedRoom == null) {
                schedules.add(oldSchedule);
                JOptionPane.showMessageDialog(
                        this,
                        "Room not found."
                );
                return;
            }

            ClassSchedule newSchedule = new ClassSchedule(
                    batch.trim(),
                    teacherName.trim(),
                    selectedRoom.getRoomNumber(),
                    date.trim(),
                    startTime.trim(),
                    endTime.trim()
            );

            schedules.add(newSchedule);

            JOptionPane.showMessageDialog(
                    this,
                    "Routine updated successfully."
            );

        } catch (Exception e) {
            schedules.remove(oldSchedule);
            schedules.add(oldSchedule);
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid date or time.\nDate: dd-MM-yyyy\nTime: HH:mm"
            );
        }
    }

    private void deleteRoutine() {

        ArrayList<ClassSchedule> schedules =
                bookingManager
                        .getScheduleManager()
                        .getSchedules();

        if (schedules.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "No routines found."
            );
            return;
        }

        String[] options = new String[schedules.size()];

        for (int i = 0; i < schedules.size(); i++) {
            ClassSchedule schedule = schedules.get(i);
            options[i] = schedule.getBatch()
                    + " - " + schedule.getTeacherName()
                    + " - Room " + schedule.getRoomNumber()
                    + " - " + schedule.getDate()
                    + " - " + schedule.getStartTime()
                    + " to " + schedule.getEndTime();
        }

        String selected = (String) JOptionPane.showInputDialog(
                this,
                "Select routine to delete:",
                "Delete Routine",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (selected == null) {
            return;
        }

        int index = findSelectedIndex(options, selected);
        ClassSchedule schedule = schedules.get(index);

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete this routine?\n\n" + selected,
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        schedules.remove(schedule);

        JOptionPane.showMessageDialog(
                this,
                "Routine deleted successfully.\n"
                        + "Room " + schedule.getRoomNumber()
                        + " is now available for this time slot."
        );
    }

    // Teacher Panel
    private void showTeacherPanel(
            Teacher teacher) {

        mainPanel.removeAll();

        mainPanel.setLayout(
                new GridLayout(
                        7,
                        1,
                        10,
                        10
                )
        );

        JLabel title =
                new JLabel(
                        "Teacher Panel - "
                                + teacher.getName(),
                        SwingConstants.CENTER
                );

        JButton roomButton =
                new JButton("View Rooms");

        JButton bookingButton =
                new JButton(
                        "Create Booking Request"
                );

        JButton scheduleButton =
                new JButton(
                        "Manage Class Schedule"
                );

        JButton myBookingButton =
                new JButton("My Bookings");

        JButton infoButton =
                new JButton("My Information");

        JButton logoutButton =
                new JButton("Logout");

        mainPanel.add(title);
        mainPanel.add(roomButton);
        mainPanel.add(bookingButton);
        mainPanel.add(scheduleButton);
        mainPanel.add(myBookingButton);
        mainPanel.add(infoButton);
        mainPanel.add(logoutButton);

        roomButton.addActionListener(
                e -> showRooms()
        );

        bookingButton.addActionListener(
                e -> createBooking(
                        teacher.getName()
                )
        );

        scheduleButton.addActionListener(
                e -> showUserScheduleManagement(
                        teacher
                )
        );

        myBookingButton.addActionListener(
                e -> showMyBookings(
                        teacher.getName()
                )
        );

        infoButton.addActionListener(
                e -> showUserInfo(
                        teacher
                )
        );

        logoutButton.addActionListener(
                e -> showWelcomePage()
        );

        refreshPanel();
    }

    // CR Panel
    private void showCRPanel(
            CR cr) {

        mainPanel.removeAll();

        mainPanel.setLayout(
                new GridLayout(
                        7,
                        1,
                        10,
                        10
                )
        );

        JLabel title =
                new JLabel(
                        "CR Panel - "
                                + cr.getName(),
                        SwingConstants.CENTER
                );

        JButton roomButton =
                new JButton("View Rooms");

        JButton bookingButton =
                new JButton(
                        "Create Class Booking"
                );

        JButton scheduleButton =
                new JButton(
                        "Manage Class Schedule"
                );

        JButton myBookingButton =
                new JButton("My Bookings");

        JButton infoButton =
                new JButton("My Information");

        JButton logoutButton =
                new JButton("Logout");

        mainPanel.add(title);
        mainPanel.add(roomButton);
        mainPanel.add(bookingButton);
        mainPanel.add(scheduleButton);
        mainPanel.add(myBookingButton);
        mainPanel.add(infoButton);
        mainPanel.add(logoutButton);

        roomButton.addActionListener(
                e -> showRooms()
        );

        bookingButton.addActionListener(
                e -> createBooking(
                        cr.getName()
                )
        );

        scheduleButton.addActionListener(
                e -> showUserScheduleManagement(
                        cr
                )
        );

        myBookingButton.addActionListener(
                e -> showMyBookings(
                        cr.getName()
                )
        );

        infoButton.addActionListener(
                e -> showUserInfo(
                        cr
                )
        );

        logoutButton.addActionListener(
                e -> showWelcomePage()
        );

        refreshPanel();
    }

    // Teacher and CR schedule menu
    private void showUserScheduleManagement(
            User user) {

        String[] options = {
                "View My Class Schedules",
                "Cancel Class",
                "Delay Class",
                "Back"
        };

        String selected =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Select an option:",
                        "Class Schedule Management",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

        if (selected == null
                || selected.equals("Back")) {
            return;
        }

        if (selected.equals(
                "View My Class Schedules")) {

            viewMyClassSchedules(user);

        } else if (selected.equals(
                "Cancel Class")) {

            cancelMyClass(user);

        } else if (selected.equals(
                "Delay Class")) {

            delayMyClass(user);
        }
    }

    // Get my schedules
    private ArrayList<ClassSchedule> getMySchedules(
            User user) {

        ArrayList<ClassSchedule> result =
                new ArrayList<>();

        for (ClassSchedule schedule :
                bookingManager
                        .getScheduleManager()
                        .getSchedules()) {

            if (user instanceof Teacher) {

                Teacher teacher =
                        (Teacher) user;

                if (schedule.getTeacherName()
                        .equalsIgnoreCase(
                                teacher.getName()
                        )) {

                    result.add(schedule);
                }

            } else if (user instanceof CR) {

                CR cr =
                        (CR) user;

                if (schedule.getBatch()
                        .equalsIgnoreCase(
                                cr.getBatch()
                        )) {

                    result.add(schedule);
                }
            }
        }

        return result;
    }

    // View my class schedules
    private void viewMyClassSchedules(
            User user) {

        ArrayList<ClassSchedule> schedules =
                getMySchedules(user);

        if (schedules.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No class schedule found."
            );

            return;
        }

        StringBuilder text =
                new StringBuilder();

        for (ClassSchedule schedule :
                schedules) {

            text.append(
                    "Batch: "
            ).append(
                    schedule.getBatch()
            ).append("\n");

            text.append(
                    "Teacher: "
            ).append(
                    schedule.getTeacherName()
            ).append("\n");

            text.append(
                    "Room: "
            ).append(
                    schedule.getRoomNumber()
            ).append("\n");

            text.append(
                    "Date: "
            ).append(
                    schedule.getDate()
            ).append("\n");

            text.append(
                    "Time: "
            ).append(
                    schedule.getStartTime()
            ).append(
                    " - "
            ).append(
                    schedule.getEndTime()
            ).append("\n");

            text.append(
                    "Status: "
            ).append(
                    schedule.getStatus()
            ).append("\n");

            if (!schedule.getUpdateReason()
                    .isEmpty()) {

                text.append(
                        "Reason: "
                ).append(
                        schedule.getUpdateReason()
                ).append("\n");
            }

            text.append(
                    "-----------------------------\n"
            );
        }

        JTextArea area =
                new JTextArea(
                        text.toString()
                );

        area.setEditable(false);

        JScrollPane scrollPane =
                new JScrollPane(area);

        scrollPane.setPreferredSize(
                new Dimension(
                        550,
                        350
                )
        );

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "My Class Schedules",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Cancel my class
    private void cancelMyClass(
            User user) {

        ArrayList<ClassSchedule> schedules =
                getMySchedules(user);

        if (schedules.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "You have no class schedules."
            );

            return;
        }

        String[] options =
                new String[
                        schedules.size()
                        ];

        for (int i = 0;
             i < schedules.size();
             i++) {

            ClassSchedule schedule =
                    schedules.get(i);

            options[i] =
                    schedule.getBatch()
                            + " | Room "
                            + schedule.getRoomNumber()
                            + " | "
                            + schedule.getDate()
                            + " | "
                            + schedule.getStartTime()
                            + " - "
                            + schedule.getEndTime()
                            + " | "
                            + schedule.getStatus();
        }

        String selected =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Select class to cancel:",
                        "Cancel Class",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

        if (selected == null) {
            return;
        }

        ClassSchedule schedule =
                schedules.get(
                        findSelectedIndex(
                                options,
                                selected
                        )
                );

        if (schedule.getStatus()
                .equalsIgnoreCase(
                        "Cancelled"
                )) {

            JOptionPane.showMessageDialog(
                    this,
                    "This class is already cancelled."
            );

            return;
        }

        String reason =
                JOptionPane.showInputDialog(
                        this,
                        "Enter cancellation reason:"
                );

        if (reason == null
                || reason.trim().isEmpty()) {
            return;
        }

        schedule.cancelClass(
                reason.trim()
        );

        JOptionPane.showMessageDialog(
                this,
                "Class cancelled successfully.\n\n"
                        + "Room "
                        + schedule.getRoomNumber()
                        + " is now AVAILABLE.\n"
                        + "Date: "
                        + schedule.getDate()
                        + "\nTime: "
                        + schedule.getStartTime()
                        + " - "
                        + schedule.getEndTime()
        );
    }

    // Delay my class
    private void delayMyClass(
            User user) {

        ArrayList<ClassSchedule> schedules =
                getMySchedules(user);

        if (schedules.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "You have no class schedules."
            );

            return;
        }

        String[] options =
                new String[
                        schedules.size()
                        ];

        for (int i = 0;
             i < schedules.size();
             i++) {

            ClassSchedule schedule =
                    schedules.get(i);

            options[i] =
                    schedule.getBatch()
                            + " | Room "
                            + schedule.getRoomNumber()
                            + " | "
                            + schedule.getDate()
                            + " | "
                            + schedule.getStartTime()
                            + " - "
                            + schedule.getEndTime()
                            + " | "
                            + schedule.getStatus();
        }

        String selected =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Select class to delay:",
                        "Delay Class",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

        if (selected == null) {
            return;
        }

        ClassSchedule schedule =
                schedules.get(
                        findSelectedIndex(
                                options,
                                selected
                        )
                );

        if (schedule.getStatus()
                .equalsIgnoreCase(
                        "Cancelled"
                )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cancelled class cannot be delayed."
            );

            return;
        }

        delaySchedule(schedule);
    }

    // Student Panel
    private void showStudentPanel() {

        mainPanel.removeAll();

        mainPanel.setLayout(
                new GridLayout(
                        4,
                        1,
                        10,
                        10
                )
        );

        JLabel title =
                new JLabel(
                        "Student Information",
                        SwingConstants.CENTER
                );

        JButton roomButton =
                new JButton(
                        "View Rooms"
                );

        JButton availabilityButton =
                new JButton(
                        "Check Room Availability"
                );

        JButton backButton =
                new JButton("Back");

        mainPanel.add(title);
        mainPanel.add(roomButton);
        mainPanel.add(availabilityButton);
        mainPanel.add(backButton);

        roomButton.addActionListener(
                e -> showRooms()
        );

        availabilityButton.addActionListener(
                e -> checkRoomAvailability()
        );

        backButton.addActionListener(
                e -> showWelcomePage()
        );

        refreshPanel();
    }

    // Check room availability
    private void checkRoomAvailability() {

        String roomNumber =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Room Number:"
                );

        if (roomNumber == null
                || roomNumber.trim().isEmpty()) {
            return;
        }

        String date =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Date (dd-MM-yyyy):",
                        LocalDate.now().format(
                                DateTimeFormatter.ofPattern(
                                        "dd-MM-yyyy"
                                )
                        )
                );

        if (date == null
                || date.trim().isEmpty()) {
            return;
        }

        String startTime =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Start Time (HH:mm):"
                );

        if (startTime == null
                || startTime.trim().isEmpty()) {
            return;
        }

        String endTime =
                JOptionPane.showInputDialog(
                        this,
                        "Enter End Time (HH:mm):"
                );

        if (endTime == null
                || endTime.trim().isEmpty()) {
            return;
        }

        try {

            validateDate(date);

            int start =
                    convertTime(startTime);

            int end =
                    convertTime(endTime);

            if (start >= end) {

                JOptionPane.showMessageDialog(
                        this,
                        "End time must be after start time."
                );

                return;
            }

            boolean available =
                    bookingManager.checkAvailability(
                            roomNumber.trim(),
                            date.trim(),
                            startTime.trim(),
                            endTime.trim()
                    );

            if (available) {

                JOptionPane.showMessageDialog(
                        this,
                        "Room "
                                + roomNumber
                                + " is AVAILABLE.\n\n"
                                + "Date: "
                                + date
                                + "\nTime: "
                                + startTime
                                + " - "
                                + endTime
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Room "
                                + roomNumber
                                + " is NOT AVAILABLE.\n\n"
                                + "Date: "
                                + date
                                + "\nTime: "
                                + startTime
                                + " - "
                                + endTime
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid date or time.\n"
                            + "Date: dd-MM-yyyy\n"
                            + "Time: HH:mm"
            );
        }
    }

    // Match room numbers
    private boolean roomNumbersMatch(
            String first,
            String second) {

        if (first == null || second == null) {
            return false;
        }

        String a = first.trim();
        String b = second.trim();

        if (a.equalsIgnoreCase(b)) {
            return true;
        }

        if (a.equalsIgnoreCase("201")
                && b.equalsIgnoreCase("Lab-201")) {
            return true;
        }

        if (a.equalsIgnoreCase("Lab-201")
                && b.equalsIgnoreCase("201")) {
            return true;
        }

        return false;
    }

    private String formatTime(int minutes) {

        int hour = minutes / 60;
        int minute = minutes % 60;

        return String.format(
                "%02d:%02d",
                hour,
                minute
        );
    }

    // Show Rooms
    private void showRooms() {

        StringBuilder text =
                new StringBuilder();

        String today =
                LocalDate.now().format(
                        DateTimeFormatter.ofPattern(
                                "dd-MM-yyyy"
                        )
                );

        text.append(
                "Today's Date: "
        ).append(
                today
        ).append(
                "\n\n"
        );

        for (Room room :
                bookingManager.getRooms()) {

            ArrayList<String> occupiedTimes =
                    new ArrayList<>();

            ArrayList<int[]> occupiedSlots =
                    new ArrayList<>();

            for (Booking booking :
                    bookingManager.getBookings()) {

                if (roomNumbersMatch(
                        booking.getRoomNumber(),
                        room.getRoomNumber()
                )
                        && booking.getDate().equals(today)
                        && booking.getStatus().equals("Approved")) {

                    occupiedTimes.add(
                            booking.getStartTime()
                                    + " - "
                                    + booking.getEndTime()
                    );

                    occupiedSlots.add(
                            new int[]{
                                    convertTime(booking.getStartTime()),
                                    convertTime(booking.getEndTime())
                            }
                    );
                }
            }

            for (ClassSchedule schedule :
                    bookingManager
                            .getScheduleManager()
                            .getSchedules()) {

                if (roomNumbersMatch(
                        schedule.getRoomNumber(),
                        room.getRoomNumber()
                )
                        && schedule.getDate().equals(today)
                        && !schedule.getStatus()
                        .equalsIgnoreCase("Cancelled")) {

                    occupiedTimes.add(
                            schedule.getStartTime()
                                    + " - "
                                    + schedule.getEndTime()
                    );

                    occupiedSlots.add(
                            new int[]{
                                    convertTime(schedule.getStartTime()),
                                    convertTime(schedule.getEndTime())
                            }
                    );
                }
            }

            String roomInfo = room.toString();

            roomInfo = roomInfo.replaceAll(
                    "(?m)^Availability:.*\\R?",
                    ""
            );

            text.append(roomInfo).append("\n");

            if (occupiedTimes.isEmpty()) {

                text.append(
                        "Availability: AVAILABLE ALL DAY\n"
                );

                text.append(
                        "Available Time Today: 00:00 - 24:00\n"
                );

            } else {

                text.append(
                        "Availability: BOOKED during listed time(s)\n"
                );

                text.append(
                        "Booked Time Today: "
                );

                for (int i = 0;
                     i < occupiedTimes.size();
                     i++) {

                    if (i > 0) {
                        text.append(", ");
                    }

                    text.append(
                            occupiedTimes.get(i)
                    );
                }

                text.append("\n");

                for (int i = 0;
                     i < occupiedSlots.size() - 1;
                     i++) {

                    for (int j = i + 1;
                         j < occupiedSlots.size();
                         j++) {

                        if (occupiedSlots.get(j)[0]
                                < occupiedSlots.get(i)[0]) {

                            int[] temp = occupiedSlots.get(i);
                            occupiedSlots.set(
                                    i,
                                    occupiedSlots.get(j)
                            );
                            occupiedSlots.set(j, temp);
                        }
                    }
                }

                StringBuilder availableTime =
                        new StringBuilder();

                int current = 0;

                for (int[] slot : occupiedSlots) {

                    if (slot[0] > current) {

                        if (availableTime.length() > 0) {
                            availableTime.append(", ");
                        }

                        availableTime.append(
                                formatTime(current)
                        ).append(" - ").append(
                                formatTime(slot[0])
                        );
                    }

                    if (slot[1] > current) {
                        current = slot[1];
                    }
                }

                if (current < 24 * 60) {

                    if (availableTime.length() > 0) {
                        availableTime.append(", ");
                    }

                    availableTime.append(
                            formatTime(current)
                    ).append(" - 24:00");
                }

                if (availableTime.length() == 0) {
                    availableTime.append("No free time today");
                }

                text.append(
                        "Available Time Today: "
                                + availableTime
                                + "\n"
                );
            }

            boolean hasBooking = false;

            for (Booking booking :
                    bookingManager.getBookings()) {

                if (roomNumbersMatch(
                        booking.getRoomNumber(),
                        room.getRoomNumber()
                )
                        && booking.getDate().equals(today)
                        && booking.getStatus().equals("Approved")) {

                    if (!hasBooking) {

                        text.append(
                                "Approved Bookings Today:\n"
                        );

                        hasBooking = true;
                    }

                    text.append("  ")
                            .append(booking.getStartTime())
                            .append(" - ")
                            .append(booking.getEndTime())
                            .append(" | ")
                            .append(booking.getTeacherName())
                            .append("\n");
                }
            }

            if (!hasBooking) {

                text.append(
                        "Approved Bookings Today: None\n"
                );
            }

            boolean hasClass = false;

            for (ClassSchedule schedule :
                    bookingManager
                            .getScheduleManager()
                            .getSchedules()) {

                if (roomNumbersMatch(
                        schedule.getRoomNumber(),
                        room.getRoomNumber()
                )
                        && schedule.getDate().equals(today)
                        && !schedule.getStatus()
                        .equalsIgnoreCase("Cancelled")) {

                    if (!hasClass) {

                        text.append(
                                "Class Schedule Today:\n"
                        );

                        hasClass = true;
                    }

                    text.append("  ")
                            .append(schedule.getStartTime())
                            .append(" - ")
                            .append(schedule.getEndTime())
                            .append(" | Batch: ")
                            .append(schedule.getBatch())
                            .append(" | Teacher: ")
                            .append(schedule.getTeacherName())
                            .append(" | ")
                            .append(schedule.getStatus())
                            .append("\n");
                }
            }

            if (!hasClass) {

                text.append(
                        "Class Schedule Today: None\n"
                );
            }

            if (occupiedTimes.isEmpty()) {

                text.append(
                        "Room Status: AVAILABLE ALL DAY\n"
                );

            } else {

                text.append(
                        "Room Status: OCCUPIED during listed time(s)\n"
                );
            }

            text.append(
                    "--------------------------------\n\n"
            );
        }

        JTextArea textArea =
                new JTextArea(
                        text.toString()
                );

        textArea.setEditable(false);

        textArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        13
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(textArea);

        scrollPane.setPreferredSize(
                new Dimension(
                        550,
                        500
                )
        );

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "Room Information",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Create booking
    private void createBooking(
            String userName) {

        String date =
                LocalDate.now().format(
                        DateTimeFormatter.ofPattern(
                                "dd-MM-yyyy"
                        )
                );

        String startTime =
                JOptionPane.showInputDialog(
                        this,
                        "Enter start time (HH:mm):"
                );

        if (startTime == null
                || startTime.trim().isEmpty()) {
            return;
        }

        String endTime =
                JOptionPane.showInputDialog(
                        this,
                        "Enter end time (HH:mm):"
                );

        if (endTime == null
                || endTime.trim().isEmpty()) {
            return;
        }

        int startMinutes;
        int endMinutes;

        try {

            startMinutes =
                    convertTime(startTime);

            endMinutes =
                    convertTime(endTime);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter time like 10:00 or 14:30."
            );

            return;
        }

        if (startMinutes >= endMinutes) {

            JOptionPane.showMessageDialog(
                    this,
                    "End time must be after start time."
            );

            return;
        }

        ArrayList<Room> availableRooms =
                new ArrayList<>();

        for (Room room :
                bookingManager.getRooms()) {

            boolean available =
                    bookingManager.checkAvailability(
                            room.getRoomNumber(),
                            date,
                            startTime.trim(),
                            endTime.trim()
                    );

            if (available) {

                availableRooms.add(room);
            }
        }

        if (availableRooms.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No room is available at "
                            + startTime
                            + " - "
                            + endTime
                            + "\nDate: "
                            + date
            );

            return;
        }

        String[] roomOptions =
                new String[
                        availableRooms.size()
                        ];

        for (int i = 0;
             i < availableRooms.size();
             i++) {

            Room room =
                    availableRooms.get(i);

            roomOptions[i] =
                    room.getRoomNumber()
                            + " - Capacity: "
                            + room.getCapacity();
        }

        String selected =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Today's Date: "
                                + date
                                + "\nTime: "
                                + startTime
                                + " - "
                                + endTime
                                + "\n\nSelect available room:",
                        "Available Rooms",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        roomOptions,
                        roomOptions[0]
                );

        if (selected == null) {
            return;
        }

        String roomNumber =
                selected.split(" -")[0];

        String bookingId =
                "B"
                        + (
                        bookingManager
                                .getBookings()
                                .size()
                                + 1
                );

        Booking booking =
                new Booking(
                        bookingId,
                        userName,
                        roomNumber,
                        date,
                        startTime.trim(),
                        endTime.trim()
                );

        try {

            bookingManager.addBooking(
                    booking
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Booking request sent.\n"
                            + "Room: "
                            + roomNumber
                            + "\nDate: "
                            + date
                            + "\nTime: "
                            + startTime
                            + " - "
                            + endTime
                            + "\nStatus: Pending"
            );

        } catch (
                BookingConflictException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Booking Conflict",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // Show my bookings
    private void showMyBookings(
            String userName) {

        ArrayList<Booking> myBookings =
                new ArrayList<>();

        for (Booking booking :
                bookingManager.getBookings()) {

            if (booking.getTeacherName()
                    .equals(userName)
                    && !booking.getStatus()
                    .equalsIgnoreCase("Cancelled")) {

                myBookings.add(booking);
            }
        }

        if (myBookings.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "You have no bookings."
            );

            return;
        }

        String[] options =
                new String[myBookings.size()];

        for (int i = 0;
             i < myBookings.size();
             i++) {

            Booking booking =
                    myBookings.get(i);

            options[i] =
                    booking.getBookingId()
                            + " - Room "
                            + booking.getRoomNumber()
                            + " - "
                            + booking.getDate()
                            + " - "
                            + booking.getStartTime()
                            + " to "
                            + booking.getEndTime()
                            + " - "
                            + booking.getStatus();
        }

        String selected =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Select a booking:",
                        "My Bookings",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

        if (selected == null) {
            return;
        }

        Booking booking =
                myBookings.get(
                        findSelectedIndex(
                                options,
                                selected
                        )
                );

        String details =
                "Booking ID: "
                        + booking.getBookingId()
                        + "\nRoom: "
                        + booking.getRoomNumber()
                        + "\nDate: "
                        + booking.getDate()
                        + "\nTime: "
                        + booking.getStartTime()
                        + " - "
                        + booking.getEndTime()
                        + "\nStatus: "
                        + booking.getStatus();

        String[] actions = {
                "View Details",
                "Cancel Booking",
                "Back"
        };

        int choice =
                JOptionPane.showOptionDialog(
                        this,
                        details,
                        "My Bookings",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        actions,
                        actions[0]
                );

        if (choice == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    details,
                    "Booking Details",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else if (choice == 1) {

            if (booking.getStatus()
                    .equalsIgnoreCase("Cancelled")) {

                JOptionPane.showMessageDialog(
                        this,
                        "This booking is already cancelled."
                );

                return;
            }

            if (booking.getStatus()
                    .equalsIgnoreCase("Rejected")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Rejected booking cannot be cancelled."
                );

                return;
            }

            int confirm =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Cancel booking "
                                    + booking.getBookingId()
                                    + "?\n"
                                    + "Room: "
                                    + booking.getRoomNumber()
                                    + "\nTime: "
                                    + booking.getStartTime()
                                    + " - "
                                    + booking.getEndTime(),
                            "Cancel Booking",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (confirm == JOptionPane.YES_OPTION) {

                booking.setStatus("Cancelled");

                JOptionPane.showMessageDialog(
                        this,
                        "Booking cancelled successfully.\n\n"
                                + "Room: "
                                + booking.getRoomNumber()
                                + "\nDate: "
                                + booking.getDate()
                                + "\nTime: "
                                + booking.getStartTime()
                                + " - "
                                + booking.getEndTime()
                                + "\nStatus: Cancelled"
                );
            }
        }
    }


    // Show user information
    private void showUserInfo(
            User user) {

        String text =
                "Name: "
                        + user.getName()
                        + "\nUser ID: "
                        + user.getUserId()
                        + "\nStatus: "
                        + user.getStatus();

        if (user instanceof Teacher) {

            Teacher teacher =
                    (Teacher) user;

            text +=
                    "\nDepartment: "
                            + teacher.getDepartment();

        } else if (user instanceof CR) {

            CR cr =
                    (CR) user;

            text +=
                    "\nDepartment: "
                            + cr.getDepartment()
                            + "\nBatch: "
                            + cr.getBatch()
                            + "\nAdvisor: "
                            + cr.getAdvisorName();
        }

        JOptionPane.showMessageDialog(
                this,
                text,
                "My Information",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Validate date
    private void validateDate(
            String date) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy"
                );

        LocalDate.parse(
                date.trim(),
                formatter
        );
    }

    // Convert time
    private int convertTime(
            String time) {

        time =
                time.trim()
                        .replace(
                                ".",
                                ":"
                        );

        String[] parts =
                time.split(":");

        if (parts.length != 2) {

            throw new IllegalArgumentException(
                    "Invalid time format."
            );
        }

        int hour =
                Integer.parseInt(
                        parts[0].trim()
                );

        int minute =
                Integer.parseInt(
                        parts[1].trim()
                );

        if (hour < 0
                || hour > 23
                || minute < 0
                || minute > 59) {

            throw new IllegalArgumentException(
                    "Invalid time."
            );
        }

        return hour * 60 + minute;
    }

    // Find selected item
    private int findSelectedIndex(
            String[] options,
            String selected) {

        for (int i = 0;
             i < options.length;
             i++) {

            if (options[i].equals(selected)) {

                return i;
            }
        }

        return 0;
    }

    // Refresh panel
    private void refreshPanel() {

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Main method
    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> new ProjectGUI()
        );
    }
}
