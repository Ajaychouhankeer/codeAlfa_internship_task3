package HotelReservationSystem;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main1 {
private static Hotel hotel = new Hotel();
private static Scanner scanner = new Scanner(System.in);

public static void  main(String[] args) {
   while (true) {
       System.out.println("\n1. Search for rooms");
       System.out.println("2. Make a reservation");
       System.out.println("3. View reservation details");
       System.out.println("4. Process payment");
       System.out.println("5. Exit");
       System.out.print("Enter your choice: ");

       int choice = scanner.nextInt();
       scanner.nextLine(); 

       switch (choice) {
           case 1:
               searchRooms();
               break;
           case 2:
               makeReservation();
               break;
           case 3:
               viewReservation();
               break;
           case 4:
               processPayment();
               break;
           case 5:
               System.out.println("Thank you for using our Hotel Reservation System!");
               return;
           default:
               System.out.println("Invalid choice. Please try again.");
       }
   }
}

private static void searchRooms() {
   System.out.print("Enter room category (AC/Non-AC): ");
   String category = scanner.nextLine();
   System.out.print("Enter check-in date (YYYY-MM-DD): ");
   LocalDate checkInDate = LocalDate.parse(scanner.nextLine());
   System.out.print("Enter check-out date (YYYY-MM-DD): ");
   LocalDate checkOutDate = LocalDate.parse(scanner.nextLine());

   List<Room> availableRooms = hotel.searchAvailableRooms(category, checkInDate, checkOutDate);
   if (availableRooms.isEmpty()) {
       System.out.println("No available rooms found for the given criteria.");
   } else {
       System.out.println("Available rooms:");
       for (Room room : availableRooms) {
           System.out.println("Room " + room.getRoomNumber() + " - " + room.getCategory() + " - $" + room.getPrice() + "/night");
       }
   }
}

private static void makeReservation() {
   System.out.print("Enter room number: ");
   int roomNumber = scanner.nextInt();
   scanner.nextLine(); // Consume newline
   System.out.print("Enter guest name: ");
   String guestName = scanner.nextLine();
   System.out.print("Enter check-in date (YYYY-MM-DD): ");
   LocalDate checkInDate = LocalDate.parse(scanner.nextLine());
   System.out.print("Enter check-out date (YYYY-MM-DD): ");
   LocalDate checkOutDate = LocalDate.parse(scanner.nextLine());

   Room selectedRoom = null;
   for (Room room : hotel.searchAvailableRooms("", checkInDate, checkOutDate)) {
       if (room.getRoomNumber() == roomNumber) {
           selectedRoom = room;
          
           break;
       }
   }
   
   

   if  (selectedRoom == null) {
       System.out.println("Room not available for the selected dates.");
       return;
   }
   

   Reservation reservation = hotel.makeReservation(selectedRoom, guestName, checkInDate, checkOutDate);
   if (reservation != null) {
       System.out.println("Reservation successful. Your reservation ID is: " + reservation.getReservationId());
   } else {
       System.out.println("Failed to make reservation.");
   }
}

private static void viewReservation() {
   System.out.print("Enter reservation ID: ");
   int reservationId = scanner.nextInt();
   scanner.nextLine(); // Consume newline

   Reservation reservation = hotel.getReservation(reservationId);
   if (reservation != null) {
       System.out.println("Reservation details:");
       System.out.println("Guest: " + reservation.getGuestName());
       System.out.println("Room: " + reservation.getRoom().getRoomNumber() + " (" + reservation.getRoom().getCategory() + ")");
       System.out.println("Check-in: " + reservation.getCheckInDate());
       System.out.println("Check-out: " + reservation.getCheckOutDate());
       System.out.println("Total price: $" + (reservation.getRoom().getPrice() * (reservation.getCheckOutDate().toEpochDay() - reservation.getCheckInDate().toEpochDay())));
       System.out.println("Payment status: " + (reservation.isPaid() ? "Paid" : "Unpaid"));
   } else {
       System.out.println("Reservation not found.");
   }
}

private static void processPayment() {
   System.out.print("Enter reservation ID: ");
   int reservationId = scanner.nextInt();
   scanner.nextLine(); // Consume newline

   Reservation reservation = hotel.getReservation(reservationId);
   if (reservation != null) {
       if (!reservation.isPaid()) {
           if (hotel.processPayment(reservation)) {
               System.out.println("Payment processed successfully.");
           } else {
               System.out.println("Payment processing failed.");
           }
       } else {
           System.out.println("This reservation has already been paid.");
       }
   } else {
       System.out.println("Reservation not found.");
   }
}
}