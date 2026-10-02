public class LaboratoryRoom extends Room {

    private int computerCount;
    private String labType;
    private String equipment;

    // Individual equipment status
    private String computerStatus;
    private String projectorStatus;

    // Default Constructor
    public LaboratoryRoom() {
        super();

        computerCount = 0;
        labType = "Unknown";
        equipment = "Unknown";

        computerStatus = "Available";
        projectorStatus = "Available";
    }

    // Parameterized Constructor
    public LaboratoryRoom(String roomNumber,
                          int floor,
                          int capacity,
                          int computerCount,
                          String labType,
                          String equipment) {

        super(roomNumber, floor, capacity);

        this.computerCount = computerCount;
        this.labType = labType;
        this.equipment = equipment;

        computerStatus = "Available";
        projectorStatus = "Available";
    }

    // Get computer count
    public int getComputerCount() {
        return computerCount;
    }

    // Get lab type
    public String getLabType() {
        return labType;
    }

    // Get equipment
    public String getEquipment() {
        return equipment;
    }

    // Get computer status
    public String getComputerStatus() {
        return computerStatus;
    }

    // Get projector status
    public String getProjectorStatus() {
        return projectorStatus;
    }

    // Set computer count
    public void setComputerCount(int computerCount) {

        if (computerCount >= 0) {
            this.computerCount = computerCount;
        }
    }

    // Set lab type
    public void setLabType(String labType) {

        if (labType != null && !labType.isEmpty()) {
            this.labType = labType;
        }
    }

    // Set equipment
    public void setEquipment(String equipment) {

        if (equipment != null && !equipment.isEmpty()) {
            this.equipment = equipment;
        }
    }

    // Set computer status
    public void setComputerStatus(String computerStatus) {

        if (computerStatus != null
                && !computerStatus.isEmpty()) {

            this.computerStatus = computerStatus;
        }
    }

    // Set projector status
    public void setProjectorStatus(String projectorStatus) {

        if (projectorStatus != null
                && !projectorStatus.isEmpty()) {

            this.projectorStatus = projectorStatus;
        }
    }

    // Update equipment information
    public void updateEquipment(String equipment,
                                int computerCount) {

        setEquipment(equipment);
        setComputerCount(computerCount);
    }

    // Display laboratory information
    @Override
    public void displayRoomInfo() {

        System.out.println("Laboratory Room");
        System.out.println("Room Number: "
                + getRoomNumber());

        System.out.println("Floor: "
                + getFloor());

        System.out.println("Capacity: "
                + getCapacity());

        System.out.println("Computer Count: "
                + computerCount);

        System.out.println("Lab Type: "
                + labType);

        System.out.println("Equipment: "
                + equipment);

        System.out.println("Computer Status: "
                + computerStatus);

        System.out.println("Projector Status: "
                + projectorStatus);

        System.out.println("Availability: "
                + (isAvailable()
                ? "Available"
                : "Not Available"));
    }

    // toString method
    @Override
    public String toString() {

        return "Laboratory Room"
                + "\nRoom Number: " + getRoomNumber()
                + "\nFloor: " + getFloor()
                + "\nCapacity: " + getCapacity()
                + "\nComputer Count: " + computerCount
                + "\nLab Type: " + labType
                + "\nEquipment: " + equipment
                + "\nComputer Status: " + computerStatus
                + "\nProjector Status: " + projectorStatus
                + "\nAvailability: "
                + (isAvailable()
                ? "Available"
                : "Not Available");
    }
}