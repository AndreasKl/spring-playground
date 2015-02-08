package net.andreaskluth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.SimpleCommandLinePropertySource;

@SpringBootApplication
public class Application {

    public static void main(String... args) throws Exception {

        SpringApplication app = new SpringApplication(Application.class);
        app.setShowBanner(false);

        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
        addDefaultProfile(app, source);

        try (ConfigurableApplicationContext ctx = app.run(args)) {
            SomeGateway gateway = ctx.getBean(SomeGateway.class);
            Response<String> response = gateway.execute(Message.of("Hello"), String.class);

            System.out.println("Got a response: " + response.getPayload());
        }
    }

    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
        if (!source.containsProperty("spring.profiles.active")) {
            app.setAdditionalProfiles("production");
        }
    }
}
