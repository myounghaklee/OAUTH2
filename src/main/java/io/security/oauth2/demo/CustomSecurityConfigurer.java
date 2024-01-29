package io.security.oauth2.demo;

import io.micrometer.observation.annotation.Observed;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class CustomSecurityConfigurer extends AbstractHttpConfigurer<CustomSecurityConfigurer, HttpSecurity> {

    @Override
    public void init(HttpSecurity builder) throws  Exception{
        System.out.println("init test");
        super.init(builder);
        System.out.println("init test");
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        super.configure(builder);
    }
}
