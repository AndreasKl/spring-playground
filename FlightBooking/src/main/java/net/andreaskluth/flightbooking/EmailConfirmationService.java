package net.andreaskluth.flightbooking;

import org.apache.log4j.Logger;

import net.andreaskluth.flightbooking.SeatConfirmation;

public class EmailConfirmationService {

  private final Logger mLogger = Logger.getLogger(getClass());

  public void sendMail(SeatConfirmation confirmation) {
    mLogger.info("Sending delayed mail confirmation : " + confirmation);
  }

}
