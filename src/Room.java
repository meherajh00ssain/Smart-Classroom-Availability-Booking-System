public abstract class Room {


    private String roomNumber;
    private int floor;
    private int capacity;                    // Encapsulation
    private boolean available;


    public Room() {
        this.roomNumber = "Unknown";
        this.floor = 0;                               // Default Constructor
        this.capacity = 0;
        this.available = true;
    }

    // Parameterized Constructor
    public Room(String roomNumber, int floor, int capacity) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.capacity = capacity;
        this.available = true;
    }

    // Getters
    public String getRoomNumber() {
        return roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setters
    public void setRoomNumber(String roomNumber) {
        if (roomNumber != null && !roomNumber.isEmpty()) {
            this.roomNumber = roomNumber;
        }
    }

    public void setFloor(int floor) {
        if (floor >= 0) {
            this.floor = floor;
        }
    }

    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        }
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Normal Method
    public void showAvailability() {
        if (available) {
            System.out.println(roomNumber+ "is Available.");
        } else {
            System.out.println(roomNumber+"is Not Available.");
        }
    }


    public abstract void displayRoomInfo();              // Abstract Method


    @Override
    public String toString() {
        return "Room Number: "+roomNumber
                + ",Floor: " +floor                        // toString()
                + ",Capacity: "+capacity
                + ",Available: "+available;
    }
}