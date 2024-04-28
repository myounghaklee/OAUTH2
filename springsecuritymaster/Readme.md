# Spring Security 6.x

SecurityProperties : tomcat 구동시 제공되는 password 설정 클래스
UserDetailsServiceAutoConfigration : inMemoryUserDetailsManager 라는 bean에서 SecurityProperties의 유저 생성을 호출

## HttpSecurity
- HttpSecurityConfiguration에서 생성하고 초기화 진행
- 보안에 필요한 각 설정 클래스와 필터들을 생성하고 SecurityFilterChain 빈 생성
- 