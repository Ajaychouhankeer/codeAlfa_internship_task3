package HotelReservationSystem;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Hotel  {
private List<Room> rooms;
private List<Reservation> reservations;

public Hotel() {
   rooms = new ArrayList<>();
   reservations = new ArrayList<>();
   initializeRooms();
}

private void initializeRooms() {
   rooms.add(new Room(101, "AC", 100.0));
   rooms.add(new Room(102, "AC", 100.0));
   rooms.add(new Room(201, "Non-AC", 80.0));
   rooms.add(new Room(202, "Non-AC", 80.0));
}

public List<Room> searchAvailableRooms(String category, LocalDate checkInDate, LocalDate checkOutDate) {
   List<Room> availableRooms = new ArrayList<>();
   for (Room room : rooms) {
       if (room.getCategory().equalsIgnoreCase(category) && isRoomAvailable(room, checkInDate, checkOutDate)) {
           availableRooms.add(room);
       }
   }
   return availableRooms;
}

private boolean isRoomAvailable(Room room, LocalDate checkInDate, LocalDate checkOutDate) {
   for (Reservation reservation : reservations) {
       if (reservation.getRoom().getRoomNumber() == room.getRoomNumber()) {
           if (!(checkOutDate.isBefore(reservation.getCheckInDate()) || checkInDate.isAfter(reservation.getCheckOutDate()))) {
               return false;
           }
       }
   }
   return true;
}

public Reservation makeReservation(Room room, String guestName, LocalDate checkInDate, LocalDate checkOutDate) {
   if (isRoomAvailable(room, checkInDate, checkOutDate)) {
       Reservation reservation = new Reservation(room, guestName, checkInDate, checkOutDate);
       reservations.add(reservation);
       return reservation;
   }
   return null;
}

public boolean processPayment(Reservation reservation) {
   // Simulating payment processing
   reservation.setPaid(true);
   return true;
}

public Reservation getReservation(int reservationId) {
   for (Reservation reservation : reservations) {
       if (reservation.getReservationId() == reservationId) {
           return reservation;
       }
   }
   return null;
}
}


