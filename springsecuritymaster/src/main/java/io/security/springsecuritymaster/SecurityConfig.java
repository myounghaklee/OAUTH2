package io.security.springsecuritymaster;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .rememberMe(rememberMe -> rememberMe
                        .alwaysRemember(true) // 매개변수 설정되지 않았을때도 쿠키가 항상 생성되어야 하는지에 대한 여부를 나타냄
                        .tokenValiditySeconds(3600) //토큰이 유효한 시간(초단위)을 지정
                        .userDetailsService(userDetailsService()) //userDetails를 조회하기 위해 사용되는 UserDetailservice를 지정
                        .rememberMeParameter("remember")// 로그인시 사용자를 기억하기위해 사용되는 HTTP매개변수, 기본값은 'remember-me'
                        .rememberMeCookieName("remember")//기억하기(remember-me) 인증을 위한 토큰을 저장하는 쿠키이름이며 기본값은 'remember-me'
                        .key("security"));//기억하기(remember-me)인증을 위해 생성된 토큰을 식별하는 키 설정
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withUsername("user1")
                .password("{noop}1111")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
