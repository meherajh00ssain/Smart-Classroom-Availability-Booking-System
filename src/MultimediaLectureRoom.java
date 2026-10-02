public class MultimediaLectureRoom extends LectureRoom {

    private boolean hasSmartBoard;

    // Default Constructor
    public MultimediaLectureRoom() {
        super();
        hasSmartBoard = false;
    }

    // Parameterized Constructor
    public MultimediaLectureRoom(String roomNumber, int floor, int capacity,
                                 boolean hasProjector, boolean hasSmartBoard) {
        super(roomNumber, floor, capacity, hasProjector);
        this.hasSmartBoard = hasSmartBoard;
    }

    // Getter
    public boolean hasSmartBoard() {
        return hasSmartBoard;
    }

    // Setter
    public void setHasSmartBoard(boolean hasSmartBoard) {
        this.hasSmartBoard = hasSmartBoard;
    }

    // Multimedia room method
    public void startPresentation() {
        System.out.println("Presentation started in " + getRoomNumber());
    }

    // Method Overriding
    @Override
    public void displayRoomInfo() {
        super.displayRoomInfo();

        System.out.println("Smart Board: "
                + (hasSmartBoard ? "Available" : "Not Available"));
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Smart Board: " + hasSmartBoard;
    }
}