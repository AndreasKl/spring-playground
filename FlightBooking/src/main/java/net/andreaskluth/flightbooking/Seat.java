package net.andreaskluth.flightbooking;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Seat {

  private String seatId;

  public Seat(String seatId) {
    this.seatId = seatId;
  }

  public String getSeatId() {
    return seatId;
  }
  
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
  
}
