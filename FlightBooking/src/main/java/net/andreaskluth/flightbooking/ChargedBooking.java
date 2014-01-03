package net.andreaskluth.flightbooking;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ChargedBooking {

  private Booking booking;
  private int charge;

  public ChargedBooking(Booking booking, int charge) {
    this.booking = booking;
    this.charge = charge;
  }

  public Booking getBooking() {
    return booking;
  }

  public int getCharge() {
    return charge;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
  
}
