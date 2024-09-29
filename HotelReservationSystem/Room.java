package HotelReservationSystem;

public class Room {
 private int roomNumber;
 private String category;
 private boolean isAvailable;
 private double price;

 public Room(int roomNumber, String category, double price) {
     this.roomNumber = roomNumber;
     this.category = category;
     this.isAvailable = true;
     this.price = price;
 }

 // Getters and setters
 public int getRoomNumber() { return roomNumber; }
 public String getCategory() { return category; }
 public boolean isAvailable() { return isAvailable; }
 public void setAvailable(boolean available) { isAvailable = available; }
 public double getPrice() { return price; }
}




