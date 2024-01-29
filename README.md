# Spring Security + OAuth2 

## Concept
- securityBuilder : 빌더 클래스로서 웹 보안을 구성하는 빈객체와 설정 클래스들을 생성하는 역할 (WebSecurity, httpSecurity).
- SecurityConfigurer : Http요청과 관련된 보안처리를 담당하는 필터들을 생성하고 초기화 설정에 관여.
- SecurityBuilder는 SecurityConfigurer를 포함하고 있고 인증, 인가 초기화 작업은 Configurer에 진행된다.