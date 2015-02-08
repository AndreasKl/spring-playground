package net.andreaskluth;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void withPlainString() throws Exception {

        Gateway.willRespondWith("42");

        SomeGateway gateway = context.getBean(SomeGateway.class);
        Response<String> response = gateway.execute(Message.of("Hello"), String.class);

        assertEquals("42", response.getPayload());
    }

    @Test
    public void withSerializableObject() throws Exception {

        Gateway.willRespondWith(URI.create("https://www.andreaskluth.net"));

        SomeGateway gateway = context.getBean(SomeGateway.class);
        Response<URI> response = gateway.execute(Message.of("Hello"), URI.class);

        assertEquals(URI.create("https://www.andreaskluth.net"), response.getPayload());
    }

}
