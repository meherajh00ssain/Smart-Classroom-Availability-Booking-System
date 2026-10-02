import java.util.ArrayList;

public class ScheduleManager {

    // All class schedules will be stored here
    private ArrayList<ClassSchedule> schedules;


    // Constructor
    public ScheduleManager() {
        schedules = new ArrayList<>();
    }


    // Add a new class schedule
    public void addSchedule(ClassSchedule schedule) {
        schedules.add(schedule);
    }


    // Show all class schedules
    public void showAllSchedules() {

        if (schedules.isEmpty()) {
            System.out.println("No class schedule found.");
            return;
        }

        for (ClassSchedule schedule : schedules) {
            schedule.displaySchedule();
            System.out.println();
        }
    }


    // Find a schedule by room number
    public ClassSchedule findScheduleByRoom(String roomNumber) {

        for (ClassSchedule schedule : schedules) {

            if (schedule.getRoomNumber().equals(roomNumber)) {
                return schedule;
            }
        }

        return null;
    }


    // Cancel a class
    public boolean cancelClass(String roomNumber, String reason) {

        ClassSchedule schedule = findScheduleByRoom(roomNumber);

        if (schedule != null) {

            schedule.cancelClass(reason);
            return true;
        }

        return false;
    }


    // Delay a class
    public boolean delayClass(String roomNumber,
                              String newStartTime,
                              String newEndTime,
                              String reason) {

        ClassSchedule schedule = findScheduleByRoom(roomNumber);

        if (schedule != null) {

            schedule.delayClass(
                    newStartTime,
                    newEndTime,
                    reason
            );

            return true;
        }

        return false;
    }


    // Check whether a room is available
    public boolean isRoomAvailable(String roomNumber) {

        for (ClassSchedule schedule : schedules) {

            if (schedule.getRoomNumber().equals(roomNumber)) {

                if (schedule.getStatus().equalsIgnoreCase("Cancelled")) {
                    return true;
                }

                return false;
            }
        }

        // If there is no class in the room,
        // the room is available
        return true;
    }


    // Show available rooms
    public void showAvailableRooms() {

        System.out.println("Available Rooms:");

        for (ClassSchedule schedule : schedules) {

            if (schedule.getStatus().equalsIgnoreCase("Cancelled")) {

                System.out.println(
                        "Room " + schedule.getRoomNumber()
                                + " is available from "
                                + schedule.getStartTime()
                                + " to "
                                + schedule.getEndTime()
                );
            }
        }
    }


    // Get all schedules
    public ArrayList<ClassSchedule> getSchedules() {
        return schedules;
    }
}

