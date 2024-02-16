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
## LoginForm 인증
```
UsernamePasswordAuthenticationFilter
ㄴ AntPathRequestMatcher(/login) -> 실패시 chain.doFilter
ㄴ Authentication
ㄴ AuthenticationManager
ㄴ AuthenticationProvider(인증성공)
ㄴ Authentication
ㄴ SecurityContext 저장
```
request /logout 시 server는 세션 무효화, 인증토큰 삭제, 쿠키정보삭제, 로그인페이지 리다이렉트 시키는것을 default로 한다.

### Remember-me
- 세션이 만려되고 웹 브라우저가 종료된 후에도 어플리케이션이 사용자를 기어갛느 ㄴ기능
- Remember-me 쿠키에 대한 http 요청을 확인한 후 토큰 기반 인증을 사용해 유효성을 검사하고 토큰이 검증되면 사용자는 로그인된다. 
- 사용자 life-cycle
  - 인증 성공 (Remember-Me 쿠키 설정)
  - 인증 실패 (쿠키가 존재하면 쿠키 무효화)
  - 로그아웃(쿠키가 존재하면 쿠키 무효화)