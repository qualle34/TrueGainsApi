package com.qualle.truegain.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Getter
@Setter
@Component
@ConfigurationProperties("email")
public class EmailProperties {

    private String login;
    private String password;
    private boolean enable;
}
