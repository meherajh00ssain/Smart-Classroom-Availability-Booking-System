import java.util.ArrayList;

public class BookingManager {

    private ArrayList<Room> rooms;
    private ArrayList<Booking> bookings;

    // Manage class schedules
    private ScheduleManager scheduleManager;

    // Constructor
    public BookingManager() {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
        scheduleManager = new ScheduleManager();
    }

    // Add Room
    public void addRoom(Room room) {
        if (room != null) {
            rooms.add(room);
        }
    }

    // Add Booking
    public void addBooking(Booking booking)
            throws BookingConflictException {
        if (booking == null) {
            throw new IllegalArgumentException(
                    "Booking cannot be null."
            );
        }
        if (checkBookingConflict(booking)) {
            throw new BookingConflictException(
                    "This room is already booked at this time."
            );
        }
        if (checkClassScheduleConflict(booking)) {
            throw new BookingConflictException(
                    "A class is scheduled in this room at this time."
            );
        }
        bookings.add(booking);
    }

    // Check Booking Conflict
    // Only Approved bookings will block a new booking
    public boolean checkBookingConflict(
            Booking newBooking) {
        if (newBooking == null) {
            return false;
        }
        int newStart =
                convertTime(newBooking.getStartTime());
        int newEnd =
                convertTime(newBooking.getEndTime());
        if (newStart >= newEnd) {
            return true;
        }
        for (Booking booking : bookings) {
            if (!booking.getStatus()
                    .equalsIgnoreCase("Approved")) {
                continue;
            }
            if (booking.getRoomNumber()
                    .equalsIgnoreCase(
                            newBooking.getRoomNumber())
                    && booking.getDate()
                    .equals(newBooking.getDate())) {
                int oldStart =
                        convertTime(
                                booking.getStartTime()
                        );
                int oldEnd =
                        convertTime(
                                booking.getEndTime()
                        );
                if (newStart < oldEnd
                        && newEnd > oldStart) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check conflict with class schedule
    public boolean checkClassScheduleConflict(
            Booking newBooking) {
        if (newBooking == null) {
            return false;
        }
        int bookingStart =
                convertTime(newBooking.getStartTime());
        int bookingEnd =
                convertTime(newBooking.getEndTime());
        for (ClassSchedule schedule :
                scheduleManager.getSchedules()) {
            if (schedule.getStatus()
                    .equalsIgnoreCase("Cancelled")) {
                continue;
            }
            if (schedule.getRoomNumber()
                    .equalsIgnoreCase(
                            newBooking.getRoomNumber())
                    && schedule.getDate()
                    .equals(newBooking.getDate())) {
                int classStart =
                        convertTime(
                                schedule.getStartTime()
                        );
                int classEnd =
                        convertTime(
                                schedule.getEndTime()
                        );
                if (bookingStart < classEnd
                        && bookingEnd > classStart) {
                    return true;
                }
            }
        }
        return false;
    }

    // Convert Time to Minutes
    public int convertTime(String time) {
        if (time == null || time.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Time cannot be empty."
            );
        }
        time = time.trim().replace(".", ":");
        String[] parts = time.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException(
                    "Invalid time format. Use HH:mm."
            );
        }
        int hour =
                Integer.parseInt(parts[0].trim());
        int minute =
                Integer.parseInt(parts[1].trim());
        if (hour < 0 || hour > 23
                || minute < 0 || minute > 59) {
            throw new IllegalArgumentException(
                    "Invalid time."
            );
        }
        return hour * 60 + minute;
    }

    // Check Availability by Room, Date and Time
    public boolean checkAvailability(
            String roomNumber,
            String date,
            String startTime,
            String endTime) {
        Room room = searchRoom(roomNumber);
        if (room == null) {
            return false;
        }
        int newStart;
        int newEnd;
        try {
            newStart = convertTime(startTime);
            newEnd = convertTime(endTime);
        } catch (Exception e) {
            return false;
        }
        if (newStart >= newEnd) {
            return false;
        }

        // Check Approved bookings
        for (Booking booking : bookings) {
            if (!booking.getStatus()
                    .equalsIgnoreCase("Approved")) {
                continue;
            }
            if (booking.getRoomNumber()
                    .equalsIgnoreCase(roomNumber)
                    && booking.getDate()
                    .equals(date)) {
                int oldStart =
                        convertTime(
                                booking.getStartTime()
                        );
                int oldEnd =
                        convertTime(
                                booking.getEndTime()
                        );
                if (newStart < oldEnd
                        && newEnd > oldStart) {
                    return false;
                }
            }
        }

        // Check class schedule conflict
        for (ClassSchedule schedule :
                scheduleManager.getSchedules()) {
            if (schedule.getStatus()
                    .equalsIgnoreCase("Cancelled")) {
                continue;
            }
            if (schedule.getRoomNumber()
                    .equalsIgnoreCase(roomNumber)
                    && schedule.getDate()
                    .equals(date)) {
                int classStart =
                        convertTime(
                                schedule.getStartTime()
                        );
                int classEnd =
                        convertTime(
                                schedule.getEndTime()
                        );
                if (newStart < classEnd
                        && newEnd > classStart) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check Availability by Room Number
    public boolean checkAvailability(
            String roomNumber) {
        Room room = searchRoom(roomNumber);
        return room != null && room.isAvailable();
    }

    // Check Whether a Room Has Any Approved Booking on a Date
    public boolean isRoomBooked(
            String roomNumber,
            String date) {
        for (Booking booking : bookings) {
            if (booking.getRoomNumber()
                    .equalsIgnoreCase(roomNumber)
                    && booking.getDate()
                    .equals(date)
                    && booking.getStatus()
                    .equalsIgnoreCase("Approved")) {
                return true;
            }
        }
        return false;
    }

    // Show Rooms
    public void showRooms() {
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    // Show Bookings
    public void showBookings() {
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    // Get Rooms
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    // Get Bookings
    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    // Get Schedule Manager
    public ScheduleManager getScheduleManager() {
        return scheduleManager;
    }

    // Search Room by Room Number
    public Room searchRoom(String roomNumber) {
        if (roomNumber == null) {
            return null;
        }

        String input = roomNumber.trim();

        for (Room room : rooms) {
            String actualRoomNumber =
                    room.getRoomNumber().trim();

            if (actualRoomNumber.equalsIgnoreCase(input)) {
                return room;
            }

            // Allow 201 as Lab-201
            if (input.equals("201")
                    && actualRoomNumber.equalsIgnoreCase("Lab-201")) {
                return room;
            }
        }

        return null;
    }

    // Search Room by Floor
    public void searchRoom(int floor) {
        for (Room room : rooms) {
            if (room.getFloor() == floor) {
                System.out.println(room);
            }
        }
    }
}
