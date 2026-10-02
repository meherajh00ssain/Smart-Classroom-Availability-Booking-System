public class LectureRoom extends Room implements Trackable {

    private boolean hasProjector;

    // Projector status
    private String projectorStatus;

    // Default Constructor
    public LectureRoom() {
        super();

        hasProjector = false;
        projectorStatus = "Not Available";
    }

    // Parameterized Constructor
    public LectureRoom(String roomNumber,
                       int floor,
                       int capacity,
                       boolean hasProjector) {

        super(roomNumber, floor, capacity);

        this.hasProjector = hasProjector;

        if (hasProjector) {
            projectorStatus = "Available";
        } else {
            projectorStatus = "Not Available";
        }
    }

    // Check projector
    public boolean hasProjector() {
        return hasProjector;
    }

    // Get projector status
    public String getProjectorStatus() {
        return projectorStatus;
    }

    // Set projector availability
    public void setHasProjector(boolean hasProjector) {

        this.hasProjector = hasProjector;

        if (!hasProjector) {
            projectorStatus = "Not Available";
        } else {
            projectorStatus = "Available";
        }
    }

    // Set projector status
    public void setProjectorStatus(String projectorStatus) {

        if (projectorStatus != null
                && !projectorStatus.isEmpty()) {

            this.projectorStatus = projectorStatus;

            // If projector is broken/not available,
            // hasProjector becomes false
            if (projectorStatus.equalsIgnoreCase("Broken")
                    || projectorStatus.equalsIgnoreCase("Not Available")) {

                hasProjector = false;

            } else if (projectorStatus.equalsIgnoreCase("Available")) {

                hasProjector = true;
            }
        }
    }

    // Start lecture
    public void startLecture() {

        System.out.println(
                "Lecture started in room "
                        + getRoomNumber()
        );
    }

    // Display room information
    @Override
    public void displayRoomInfo() {

        System.out.println("Lecture Room");

        System.out.println(
                "Room Number: "
                        + getRoomNumber()
        );

        System.out.println(
                "Floor: "
                        + getFloor()
        );

        System.out.println(
                "Capacity: "
                        + getCapacity()
        );

        System.out.println(
                "Projector: "
                        + projectorStatus
        );

        System.out.println(
                "Availability: "
                        + (isAvailable()
                        ? "Available"
                        : "Not Available")
        );
    }

    // Track room status
    @Override
    public void trackStatus() {

        System.out.println(
                "Tracking Lecture Room: "
                        + getRoomNumber()
        );

        System.out.println(
                "Projector Status: "
                        + projectorStatus
        );
    }

    // toString method
    @Override
    public String toString() {

        return "Lecture Room"
                + "\nRoom Number: "
                + getRoomNumber()
                + "\nFloor: "
                + getFloor()
                + "\nCapacity: "
                + getCapacity()
                + "\nProjector: "
                + projectorStatus
                + "\nAvailability: "
                + (isAvailable()
                ? "Available"
                : "Not Available");
    }
}