package net.andreaskluth.flightbooking;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Booking {

  private String flightId;
  private String mail;

  public Booking(String flightId, String mail) {
    this.flightId = flightId;
    this.mail = mail;
  }
  
  public String getFlightId() {
    return flightId;
  }

  public String getMail() {
    return mail;
  }
  
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
  
}
