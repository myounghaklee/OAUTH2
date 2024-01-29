# Spring Security + OAuth2 

## Concept
- securityBuilder : 빌더 클래스로서 웹 보안을 구성하는 빈객체와 설정 클래스들을 생성하는 역할 (WebSecurity, httpSecurity).
- SecurityConfigurer : Http요청과 관련된 보안처리를 담당하는 필터들을 생성하고 초기화 설정에 관여.
- SecurityBuilder는 SecurityConfigurer를 포함하고 있고 인증, 인가 초기화 작업은 Configurer에 진행된다.

### AuthConfiguration Flow
SpringWebMvcImportSelector( WebMvcSecurityConfiguration)
-> SecurityFilterAuthConfiguration( DeligatingFilterProxyRegistrationBean - DelegatingFilterProxy emdfhr)
-> WebMbcSecurityConfiguration( AuthenticationPrincipalArgumentResolver (@AuthenticationPrincipal로 Principal 객체 바인딩, CurrentSecurityContextArgumentReslover, CsrfTokenArgumentResolver)
-> HttpSecurityConfiguration ( HttpSecurity : 공통 설정 클래스와 필터들을 생성하고 최종적으로 SecurityFilterchain 빈 반환)



## KeyCloak
start : keycloack directory하위의 bin 이동후 ```./kc.sh start-dev```



## Role
### Resource Owner(자원 소유자)
- 보호된 자원에 대한 접근 권한을 부여할 수 있는 주체, 사용자로서 계정의 일부에대한 접근 권한을 부여받는 사람
- 사용자를 대신하여 작동하려는 모든 클라이언트는 먼저 사용자의 허가를 받아야 한다.

### Resource Server
- 타사 어플리케이션에서 접근하는 사용자의 자원이 포함된 서버를 의미
- 액세스 토큰을 수락 및 검증할 수 있어야 하며 권한 체계에 따라 요청을 승인할 수 있어야 한다. 

### Authorizatino Server(인가서버)
- 클라이언트가 사용자 계정에 대한 동의 및 접근을 요청할때 상호 작용하는 서버, 클라이언트의 권한 부여 요청을 승인하거나 거부하는 서버
- 사용자가 클라이언트에게 권한 부여 요청을 승인한 후, access token
- 사용자가 클라이언트에게 권한 부여 요청을 승인한 후, access token을 ㅋ르라이언트에게 부여하는 역할
### Client
- 사용자를 대신하여 권한을 부여받아 사욪자ㅢ 리소스에 접근하려는 어플리케이션
- 사용자를 권한 부여 서버로 안내하거나 사용자의 상호작용 ㅓㅂㅅ이 권한 부여 서버로 부터 직접 권한을 얻을 수 있다.
- 
- 