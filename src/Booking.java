public class Booking implements Trackable {

    private String bookingId;
    private String teacherName;
    private String roomNumber;
    private String date;
    private String startTime;
    private String endTime;
    private String status;

    // Default Constructor
    public Booking() {
        bookingId = "B001";
        teacherName = "Unknown";
        roomNumber = "Unknown";
        date = "Unknown";
        startTime = "Unknown";
        endTime = "Unknown";
        status = "Pending";
    }

    // Parameterized Constructor
    public Booking(
            String bookingId,
            String teacherName,
            String roomNumber,
            String date,
            String startTime,
            String endTime) {

        this.bookingId = bookingId;
        this.teacherName = teacherName;
        this.roomNumber = roomNumber;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = "Pending";
    }

    // Getters
    public String getBookingId() {
        return bookingId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Approve Booking
    public void approveBooking() {
        status = "Approved";
    }

    // Reject Booking
    public void rejectBooking() {
        status = "Rejected";
    }

    // Trackable Interface Method
    @Override
    public void trackStatus() {
        System.out.println(
                "Booking " + bookingId
                        + " Status: " + status
        );
    }

    // toString Method
    @Override
    public String toString() {
        return "Booking ID: " + bookingId
                + "\nTeacher: " + teacherName
                + "\nRoom: " + roomNumber
                + "\nDate: " + date
                + "\nTime: " + startTime
                + " - " + endTime
                + "\nStatus: " + status;
    }
}