package net.andreaskluth.flightbooking;

public interface BookingConfirmationService {

  SeatConfirmation confirm(Booking booking);

}
