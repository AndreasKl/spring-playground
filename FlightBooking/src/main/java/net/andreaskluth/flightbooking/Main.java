package net.andreaskluth.flightbooking;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  
  private static final Logger LOGGER = Logger.getLogger(Main.class);

  public static void main(final String... args) {
    LOGGER.info("========================================================="
        + "\n                                                         "
        + "\n          Welcome to Spring Integration 3.x!            "
        + "\n                                                         "
        + "\n=========================================================");
    LOGGER.info("========================================================="
        + "\n                                                         "
        + "\n    Please press 'q + Enter' to quit the application.    "
        + "\n                                                         "
        + "\n=========================================================");

    AbstractApplicationContext context = new ClassPathXmlApplicationContext(
        "classpath:META-INF/spring/integration/*-context.xml");
    context.registerShutdownHook();

    BookingConfirmationService service = context.getBean(BookingConfirmationService.class);

    try (Scanner scanner = new Scanner(System.in)) {
      while (true) {
        LOGGER.info("Please enter a booking request and press <enter>:");
        String input = scanner.nextLine();
        if ("q".equals(input.trim())) {
          break;
        }
        LOGGER.info("Booking request: " + service.confirm(new Booking(input, "andreaskluth@example.com")));
      }      
    }
    
    LOGGER.info("Bye bye.");
    context.close();
  }
  
}
