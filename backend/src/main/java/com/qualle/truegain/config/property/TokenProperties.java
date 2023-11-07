package com.qualle.truegain.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Getter
@Setter
@Component
@ConfigurationProperties("token")
public class TokenProperties {

    private String issued;
    private Duration lifetime;
    private String secret;
}
