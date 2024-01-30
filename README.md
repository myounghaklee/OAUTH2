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


-----
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

----
## Client Type
- 인증서버에 클라이언트 등록할때 클라이언트 자격 증명인 아이디와 암호를 받는다 
- 클라이언트 암호는 비밀이고 그대로 유지되어야하는 반면 클라이언트 아이디는 공개다 
- 이 자격증명은 인증서버에 대한 클라이언트 ID를 증명한다. 
### Confidential Clients 
- 기밀클라이언트는 client_secret의 기밀성을 유지할 수 있는 클라이언트를 의미
- 사용자가 소스코드에 액세스할 수 없는 서버에서 실행되는 응용프로그램
- 웹서버에서 실행되기 때문에 대부분 웹앱이라고 한다. 
### Public Clients
- 공개 클라이언트는 client_secret의 기밀을 유지할 수 없어 secret이 사용되지 않음
- SPA브라우저에서 실ㅇ행되는 js애플리케이션, 앱 등을 말한다. 
- chrome 개발자 콘솔과 같은 디버깅도구를 사용해 바이너리/실행코드에서 기밀정보를 추출할 수 있기 때문에 공개로 간주된다. 
- 서버측이 아닌 리소스 소유자가 사용하는 장치에서 실행되는 모든 칼리언트는 공개 클라이언트로 간주

## Token Types
### Access Token 
- 클라이언트에서 사용자의 `보호된 리소스에 접근하기 위해 사용하는 자격증명` 으로서 역할을 하며 리소스 소유자가 클라이언트에게 부여한 권한 부여의 표현이다. 
- 액세스기간, 범위 및 서버에 필요한 기타 정보가 있다. 
- 타입에는 식별자 타입과 자체 포함타입이 있다. 
### Refresh Token
- 액세스 토큰이 만료된 후 새 액세스 토큰을 더기 위해 클라이언트 응용 프로그램에서 사용하는 자격증명
- 액세스 토큰 만료될 경우 사용
- refresh token은 액세스 토큰과 달리 권한 서버 토큰 엔드포인트에만 보내지고 리소스 서버에는 보내지 않는다. 
### Authorization Code
- 권한부여 코드 흐름에서 사용되고  클라이언트가 액세스 토큰과 교환할 임시 코드
- 사용자가 클라리언트가 요청하는 정보를 확ㅇ니하고 인가 서버로부터 리다이렉트되어 받아온다.
