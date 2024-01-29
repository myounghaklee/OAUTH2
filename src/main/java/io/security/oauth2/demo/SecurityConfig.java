package io.security.oauth2.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        //Deprecated Method
//        http.authorizeRequests().anyRequest().authenticated();
//        http.formLogin();
//        http.apply(new CustomSecurityConfigurer());
//        return http.build();

        //Changed Method
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .apply(new CustomSecurityConfigurer());

        return http.build();

    }
}
