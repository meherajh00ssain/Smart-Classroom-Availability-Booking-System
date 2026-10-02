public class ClassSchedule {

    // ==============================
    // Private Data Members
    // ==============================

    private String batch;
    private String teacherName;
    private String roomNumber;
    private String date;
    private String startTime;
    private String endTime;
    private String status;
    private String updateReason;


    // ==============================
    // Default Constructor
    // ==============================

    public ClassSchedule() {
        this.status = "Scheduled";
        this.updateReason = "";
    }


    // ==============================
    // Parameterized Constructor
    // ==============================

    public ClassSchedule(String batch, String teacherName,
                         String roomNumber, String date,
                         String startTime, String endTime) {

        this.batch = batch;
        this.teacherName = teacherName;
        this.roomNumber = roomNumber;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;

        this.status = "Scheduled";
        this.updateReason = "";
    }


    // ==============================
    // Full Constructor
    // ==============================

    public ClassSchedule(String batch, String teacherName,
                         String roomNumber, String date,
                         String startTime, String endTime,
                         String status, String updateReason) {

        this.batch = batch;
        this.teacherName = teacherName;
        this.roomNumber = roomNumber;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.updateReason = updateReason;
    }


    // ==============================
    // Cancel Class
    // ==============================

    public void cancelClass(String reason) {

        this.status = "Cancelled";
        this.updateReason = reason;

        System.out.println(
                "Class cancelled for Batch " + batch +
                        " in Room " + roomNumber
        );
    }


    // ==============================
    // Delay Class
    // ==============================

    public void delayClass(String newStartTime,
                           String newEndTime,
                           String reason) {

        this.startTime = newStartTime;
        this.endTime = newEndTime;

        this.status = "Delayed";
        this.updateReason = reason;

        System.out.println(
                "Class delayed for Batch " + batch +
                        ". New Time: " +
                        newStartTime + " - " + newEndTime
        );
    }


    // ==============================
    // Complete Class
    // ==============================

    public void completeClass() {

        this.status = "Completed";

        System.out.println(
                "Class completed for Batch " + batch
        );
    }


    // ==============================
    // Restore / Reschedule Class
    // ==============================

    public void rescheduleClass(String newStartTime,
                                String newEndTime) {

        this.startTime = newStartTime;
        this.endTime = newEndTime;

        this.status = "Scheduled";
        this.updateReason = "";

        System.out.println(
                "Class rescheduled successfully."
        );
    }


    // ==============================
    // Check Room Availability
    // ==============================

    public boolean isRoomAvailable() {

        if (status.equalsIgnoreCase("Cancelled")) {
            return true;
        }

        return false;
    }


    // ==============================
    // Getters
    // ==============================

    public String getBatch() {
        return batch;
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

    public String getUpdateReason() {
        return updateReason;
    }


    // ==============================
    // Setters
    // ==============================

    public void setBatch(String batch) {
        this.batch = batch;
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

    public void setUpdateReason(String updateReason) {
        this.updateReason = updateReason;
    }


    // ==============================
    // Display Schedule
    // ==============================

    public void displaySchedule() {

        System.out.println("=================================");
        System.out.println("        CLASS SCHEDULE");
        System.out.println("=================================");

        System.out.println("Batch       : " + batch);
        System.out.println("Teacher     : " + teacherName);
        System.out.println("Room        : " + roomNumber);
        System.out.println("Date        : " + date);
        System.out.println("Time        : " + startTime +
                " - " + endTime);
        System.out.println("Status      : " + status);

        if (!updateReason.isEmpty()) {
            System.out.println("Reason      : " + updateReason);
        }

        System.out.println("Room Status : " +
                (isRoomAvailable() ? "Available" : "Occupied"));

        System.out.println("=================================");
    }


    // ==============================
    // toString()
    // ==============================

    @Override
    public String toString() {

        return "ClassSchedule{" +
                "batch='" + batch + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status='" + status + '\'' +
                ", updateReason='" + updateReason + '\'' +
                '}';
    }
}
