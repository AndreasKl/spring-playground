package net.andreaskluth.flightbooking;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextTest {

  @Test
  public void testStartupOfSpringInegrationContext() throws Exception {
    try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
        "/META-INF/spring/integration/spring-integration-context.xml", ApplicationContextTest.class)) {
      // NOOP
    }
  }
}
