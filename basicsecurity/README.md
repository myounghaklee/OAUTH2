# Spring Security

SpringSecurity 5.4 이전 버전에서는 WebSecurityConfigurerAdapter를 상송받아서 configuration
SpringSecurity 5.4 이전 버전에서는 WebSecurityConfigurerAdapter를 상송받아서 configuratio file을 생성하지만
5.7버전 이후부터는 filterChain 을 사용하는 방식으로 변경되었다. 

```
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        return http.build();
    }
}

``` 