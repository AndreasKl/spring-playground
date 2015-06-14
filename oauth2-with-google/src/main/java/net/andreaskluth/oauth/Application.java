package net.andreaskluth.oauth;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Objects;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.FileList;

@SpringBootApplication
public class Application {

    private static final String GOOGLE_OAUTH2_TOKEN_URI = "https://accounts.google.com/o/oauth2/token";
    private static final String GOOGLE_OAUTH2_AUTH_URI = "https://accounts.google.com/o/oauth2/auth";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    JsonFactory jsonFactory() {
        return new JacksonFactory();
    }

    @Bean
    HttpTransport httpTransport() {
        try {
            return GoogleNetHttpTransport.newTrustedTransport();
        }
        catch (GeneralSecurityException | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Bean
    DataStore<StoredCredential> credentialsStore() throws IOException {
        return StoredCredential
                .getDefaultDataStore(new FileDataStoreFactory(new File("database.db")));
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    AuthorizationCodeFlow authorizationCodeFlow(
            @Value("${google.clientId}") String clientId,
            @Value("${google.clientSecret}") String secret, JsonFactory jsonFactory,
            HttpTransport httpTransport, DataStore<StoredCredential> credentialsStore)
                    throws IOException {

        BasicAuthentication clientAuthentication = new BasicAuthentication(clientId,
                secret);
        return new AuthorizationCodeFlow.Builder(
                BearerToken.authorizationHeaderAccessMethod(), httpTransport, jsonFactory,
                new GenericUrl(GOOGLE_OAUTH2_TOKEN_URI), clientAuthentication, clientId,
                GOOGLE_OAUTH2_AUTH_URI).setCredentialDataStore(credentialsStore)
                        .setScopes(Collections.singletonList(DriveScopes.DRIVE)).build();
    }

    @Controller
    public static class LoginController {

        private static final String OAUTH2_CALLBACK = "http://localhost:8080/oauth2callback";
        private static final String APPLICATION_NAME = "acessing-documents-on-google";

        private String user;
        private JsonFactory jsonFactory;
        private HttpTransport httpTransport;
        private Provider<AuthorizationCodeFlow> authorizationCodeFlowProvider;

        @Autowired
        public LoginController(@Value("${static.user}") String user,
                JsonFactory jsonFactory, HttpTransport httpTransport,
                Provider<AuthorizationCodeFlow> authorizationCodeFlowProvider) {
            this.user = Objects.requireNonNull(user);
            this.jsonFactory = Objects.requireNonNull(jsonFactory);
            this.httpTransport = Objects.requireNonNull(httpTransport);
            this.authorizationCodeFlowProvider = Objects
                    .requireNonNull(authorizationCodeFlowProvider);
        }

        @RequestMapping("/")
        public ModelAndView list() throws IOException {
            Credential credential = getAuthorizationCodeFlow().loadCredential(this.user);

            if (isExpired(credential)) {
                return new ModelAndView("redirect:oauth2");
            }

            Drive drive = new Drive.Builder(this.httpTransport, this.jsonFactory,
                    credential).setApplicationName(APPLICATION_NAME).build();
            FileList result = drive.files().list().setMaxResults(10).execute();
            return new ModelAndView("files", "files", result.getItems());
        }

        @RequestMapping("oauth2")
        public ModelAndView authorize(HttpServletRequest request) throws IOException {

            String authorizationUrl = getAuthorizationCodeFlow().newAuthorizationUrl()
                    .setRedirectUri(OAUTH2_CALLBACK).build();
            return new ModelAndView("redirect:" + authorizationUrl);
        }

        @RequestMapping("oauth2callback")
        public ModelAndView authorized(@RequestParam String code) throws IOException {

            TokenResponse response = getAuthorizationCodeFlow().newTokenRequest(code)
                    .setRedirectUri(OAUTH2_CALLBACK).execute();
            getAuthorizationCodeFlow().createAndStoreCredential(response, this.user);
            return new ModelAndView("redirect:/");
        }

        private AuthorizationCodeFlow getAuthorizationCodeFlow() throws IOException {
            return this.authorizationCodeFlowProvider.get();

        }

        private boolean isExpired(Credential credential) {
            return credential == null || credential.getExpiresInSeconds() < 10;
        }
    }
}
