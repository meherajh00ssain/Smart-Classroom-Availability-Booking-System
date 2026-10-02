public class Admin extends User {
 public Admin() {

        super();

        setStatus("Approved");
    }

    // Parameterized Constructor
     public Admin(String name,
                 String userId,
                 String password) {

        super(name, userId, password);

        setStatus("Approved");
    }

    // Approve User Account

   public void approveAccount(User user) {

        user.setStatus("Approved");

        System.out.println(
                "Account approved."
        );
    }

    // Reject User Account
    public void rejectAccount(User user) {

        user.setStatus("Rejected");

        System.out.println(
                "Account rejected."
        );
    }

    // Approve Booking

    public boolean approveBooking(
            Booking booking,
            BookingManager bookingManager) {

        // Already approved
        if (booking.getStatus()
                .equalsIgnoreCase("Approved")) {

            System.out.println(
                    "This booking is already approved."
            );

            return false;
        }


        // Already rejected
        if (booking.getStatus()
                .equalsIgnoreCase("Rejected")) {

            System.out.println(
                    "This booking has already been rejected."
            );

            return false;
        }


        // Check booking conflict
        if (bookingManager.checkBookingConflict(
                booking)) {

            System.out.println(
                    "Room is already booked at this time."
            );

            return false;
        }


        // Check class schedule conflict
        if (bookingManager
                .checkClassScheduleConflict(
                        booking)) {

            System.out.println(
                    "A class is scheduled in this room at this time."
            );

            return false;
        }


        // Approve booking
        booking.approveBooking();

        System.out.println(
                "Booking approved successfully."
        );

        return true;
    }

    // Reject Booking

    public void rejectBooking(
            Booking booking) {

        if (booking.getStatus()
                .equalsIgnoreCase("Approved")) {

            System.out.println(
                    "Approved booking cannot be rejected here."
            );

            return;
        }

        booking.rejectBooking();

        System.out.println(
                "Booking rejected."
        );
    }

    // Update Lab Equipment

    public void updateEquipment(
            LaboratoryRoom lab,
            String equipment,
            int computerCount) {

        lab.updateEquipment(
                equipment,
                computerCount
        );

        System.out.println(
                "Equipment information updated."
        );
    }
    // Update Laboratory Equipment Status
     public void updateEquipmentStatus(
            LaboratoryRoom lab,
            String equipmentName,
            String status) {

        if (equipmentName
                .equalsIgnoreCase("Projector")) {

            lab.setProjectorStatus(status);

        } else if (equipmentName
                .equalsIgnoreCase("Computer")) {

            lab.setComputerStatus(status);

        } else {

            System.out.println(
                    "Invalid equipment name."
            );

            return;
        }

        System.out.println(
                equipmentName
                        + " status updated to "
                        + status
        );
    }
    // Update Lecture Room Equipment Status
    public void updateEquipmentStatus(
            LectureRoom room,
            String equipmentName,
            String status) {

        if (equipmentName
                .equalsIgnoreCase("Projector")) {

            room.setProjectorStatus(status);

        } else {

            System.out.println(
                    "Invalid equipment name."
            );

            return;
        }

        System.out.println(
                equipmentName
                        + " status updated to "
                        + status
        );
    }
    // toString()
   @Override
    public String toString() {

        return "Admin{" +
                "name='" + getName() + '\'' +
                ", userId='" + getUserId() + '\'' +
                ", status='" + getStatus() + '\'' +
                '}';
    }
}