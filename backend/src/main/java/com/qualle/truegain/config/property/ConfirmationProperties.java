package com.qualle.truegain.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Getter
@Setter
@Component
@ConfigurationProperties("confirmation")
public class ConfirmationProperties {

    private EmailProperties email;
    private Duration lifetime;

    @Getter
    @Setter
    public static class EmailProperties {

        private String login;
        private String password;
        private boolean enable;
    }

}
