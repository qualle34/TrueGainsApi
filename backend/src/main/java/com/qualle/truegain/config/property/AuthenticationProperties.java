package com.qualle.truegain.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Getter
@Setter
@Component
@ConfigurationProperties("authentication")
public class AuthenticationProperties {

    private TokenProperties token;
    private SessionProperties session;
    private TemporaryProperties temporary;

    @Getter
    @Setter
    public static class TokenProperties {
        private String issuedBy;
        private Duration lifetime;
        private String secret;
    }

    @Getter
    @Setter
    public static class SessionProperties {
        private Duration lifetime;
    }

    @Getter
    @Setter
    public static class TemporaryProperties {
        private Duration lifetime;
    }
}
