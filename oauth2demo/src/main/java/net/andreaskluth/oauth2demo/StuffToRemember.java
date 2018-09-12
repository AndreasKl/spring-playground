package net.andreaskluth.oauth2demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class StuffToRemember {

  public static class SomeOauthStuff {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TOKEN_TYPE = "Bearer";

    public void apply() {
      SecurityContext securityContext = SecurityContextHolder.getContext();
      Authentication authentication = securityContext.getAuthentication();

      if (authentication != null
          && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
        OAuth2AuthenticationDetails details =
            (OAuth2AuthenticationDetails) authentication.getDetails();
        String header =
            AUTHORIZATION_HEADER
                + "="
                + String.format("%s %s", BEARER_TOKEN_TYPE, details.getTokenValue());
      }
    }
  }
}
