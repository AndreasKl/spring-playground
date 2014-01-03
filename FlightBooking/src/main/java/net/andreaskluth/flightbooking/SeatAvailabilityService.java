package net.andreaskluth.flightbooking;

public class SeatAvailabilityService {

  public SeatConfirmation confirmSeat(ChargedBooking chargedBooking) {
    return new SeatConfirmation(chargedBooking, new Seat("1A"));
  }

}
