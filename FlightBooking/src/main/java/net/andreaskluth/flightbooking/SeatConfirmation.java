package net.andreaskluth.flightbooking;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SeatConfirmation {

  private ChargedBooking chargedBooking;
  private Seat seat;

  public SeatConfirmation(ChargedBooking chargedBooking, Seat seat) {
    this.chargedBooking = chargedBooking;
    this.seat = seat;
  }

  public Seat getSeat() {
    return seat;
  }

  public ChargedBooking getChargedBooking() {
    return chargedBooking;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

}
