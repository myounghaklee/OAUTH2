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


## Grant Type
- 권한 부여란 클라이언트가 사용자를 대신해서 사용자의 승인하에 인가서버로 부터 권한을 부여받는것.
- Authorization code grant type : 권한코드 부여 타입, 서버 사이드 앱, 보안에 가장 안전한 유형
- client credentials grant type : 클라이언트 자격증명 권한 부여 타입, Ui or 화면이 없는 서버 어플리케이션
- refresh token grant type : 새로고침 토큰 부여타입, authorization code, resource ownder password type 에서 지원 
- PKCE-enhanced authorization code grant type : PKCE 권한 코드 부여타입, 서버사이드 어플리케이션, 공개 클라이언트 어플리케이션

## ID Token
- 사용자가 인증되었음을 증명하며 OIDC 요청시 access token과 함꼐 클라이언트에게 전달되는 토큰
- jwt로 표현되며 헤더, 페이로드, 서명으로 구성된다.
- 개인키로 발급자가 서명하는 것으로서 토큰의 출처를 보장하고 변조되지 않았음을 보장한다.ㅣ
- 어플리케이션은 공개키로 id토큰을 검증 및 유혀성을 검사하고 만료여부등 토큰의 클레임을 확인한다.
- 클라이언트는 클레임 정보에 포함되어 있는 사용자명, 이메일을 활용하여 인증관리 할 수 있따.

### ID Token vs Access Token
- ID Token은 API 요청에 사용해서는 안되며 사용자의 신원확인을 위해 사용되어져야 함
- Access Token은 인증을 위해 사용해서는 안되며 리소스에 접근하기 위해 사용되어져야 한다.

### OIDC 로그인 요청
OIDC 상호작용 행위자
- openid provider :  openid제공자로서 최종 사용자 인증하고 인증결과와 사용자에 대한 정보를 신뢰 당사자에게 제공할 수 있는 oauth서버를 의미
- relying party : 신뢰 당사자로서 인증 요청을 처리하기 위해 op에 의존하는 oauth 애플리케이션으 ㄹ의미

### 흐름
- op에 권한부여 요청을 보낸다.
- op는 최종 사용자를 인증하고 권한을 얻는다.
- op는 id 토큰과 액세스 토큰으로 응답한다.
- rp는 access token을 사용하여 userinfo 엔드포인트에 요청보낼 수 있다.
- userInfo 엔드포인트 최종 사용자에 대한 클레임을 반환한다.

### OIDC 로그인 요청
매개변수 요청 및 응답
- 요청시 openid범위를 scope 매개변수에 포함해야한다.
- response_type 매개변수는 id_token을 포함해야한다. (response_type이 해당 토큰을 지원해야 한다.)
- 요청은 nonce 매개변수를 포함해야한다. (Implicit flow인 경우 필수)
- 요청은 포함되는 값으로서 결과 id_token 값에 클레임으로 포함되며 이것은 토큰의 재생공격을 방지하고 요쳐으이 출처를 식별하는데 사용할 수 있는 임의의 고유 문자열이다.
- 해당 nonce 클레임에는 요청에서 전송된 것과 정확히 동일한 값이 포함되어야 합니ㅏㄷ. 그렇지 않은경우 애플리케이션에서 인증을 거부해야 한다.
 
## Client 권한부여 요청 시작 
1. 클라이언트가 인가서버로 구너한 부여 요청을 하거나 토큰 요청을 할 경우 클라이언트 정보 및 엔드포인트 정보를 참조해서 전달한다. 
2. application.yml 환경설정 파일에 클라이언트 설정과 인가서버 엔드포인트 설정을 한다. 
3. 초기화가 진행되면 application.yml에 있는 클라이언트 및 엔드포인트 정보가 OAuth2ClientProperties 의 각 속성에 바인딩 된다. 
4. OAuth2ClientProperties에 바인딩 되어 있는 속성의 값은 인가서버로 권한부여 요청을 하기 위한 ClienteRegistration 클래스의 필드에 저장된다.
5. OAuth2Client는 ClientRegistration를 참조해서 권한부여 요청을 위한 매개변수를 구성하고 인가서버와 통신한다.


## OAuth2LoginConfigurer 초기화 및 설정 
init 호출시 아래의 순서로 호출된다.
- OAuth2LoginAuthenticationFilter : access 토큰 교환 및 사용자 정보 엔드포인트 요청 필터
- OAuth2LoginAuthenticationProvider
- OidcAuthorizationCodeAuthenticationProvider 
- DefautlLoginPateGeneratingFilter

configure 호출시 
- OAuth2AuthorizationRequestRedirectFilter : 임시코드 발급 엔드 포인트 요청 필터


