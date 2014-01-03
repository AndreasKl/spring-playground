package net.andreaskluth.flightbooking;

public class BillForBookingService {

  public ChargedBooking pay(Booking booking) {
      return new ChargedBooking(booking, 1);
  }

}
