package net.andreaskluth.oauth2demo;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Client
public class Oauth2DemoApplication {

  public static void main(String[] args) {
    System.setProperty("file.encoding", UTF_8.name());
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    SpringApplication.run(Oauth2DemoApplication.class, args);
  }

  @RestController
  public static class SomeController {
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Tomato tomato() {
      return new Tomato("dark-red");
    }
  }

  public static class Tomato {
    final String kind;

    Tomato(String kind) {
      this.kind = kind;
    }

    public String getKind() {
      return kind;
    }
  }
}
