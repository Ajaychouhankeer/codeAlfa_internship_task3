package HotelReservationSystem;

import java.time.LocalDate;

public class Reservation  {
private static int nextId = 1;
private int reservationId;
private Room room;
private String guestName;
private LocalDate checkInDate;
private LocalDate checkOutDate;
private boolean isPaid;

public Reservation(Room room, String guestName, LocalDate checkInDate, LocalDate checkOutDate) {
   this.reservationId = nextId++;
   this.room = room;
   this.guestName = guestName;
   this.checkInDate = checkInDate;
   this.checkOutDate = checkOutDate;
   this.isPaid = false;
}

// Getters and setters
public int getReservationId() { return reservationId; }
public Room getRoom() { return room; }
public String getGuestName() { return guestName; }
public LocalDate getCheckInDate() { return checkInDate; }
public LocalDate getCheckOutDate() { return checkOutDate; }
public boolean isPaid() { return isPaid; }
public void setPaid(boolean paid) { isPaid = paid; }
}
