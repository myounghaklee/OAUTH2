# Spring Security 6.x

SecurityProperties : tomcat 구동시 제공되는 password 설정 클래스
UserDetailsServiceAutoConfigration : inMemoryUserDetailsManager 라는 bean에서 SecurityProperties의 유저 생성을 호출

## HttpSecurity
- HttpSecurityConfiguration에서 생성하고 초기화 진행
- 보안에 필요한 각 설정 클래스와 필터들을 생성하고 SecurityFilterChain 빈 생성


## Filter
- 서블릿필터는 클라이언트 요청과 서의 응답을 가공하거나 검사하는데 사용되는 요소이다. 
- 클라이언트 요청이 서블릿에 도달하기 전이나 서블릿이 응답을 클리아언트에게 보내기 전에 특정 작업을 수행할 수 있다.
- 서블릿 컨테이너(WAS)에서 생성되고 실행되고 종료된다.
```java
public class FilterA implements Filter {

    @Override
    public void init(){
        // 필터 초기화시 필요한 작업 수행
    }
    @Override
    public void doFilter(ServlceRequest request, ServletResponse response, FilterChain chain) throws  IOException, ServlceException{
        //pre-processing //요청 처리 전에 수행할 작업, ServletRequest 수정
        chain.doFilter(request, response);//다음 필터로 요청과 응답 전달
        //post-processing // 응답 처리 후에 수행할 작업, ServleResponse수정
    }

    @Override
    public void destory(){
        //필터가 제거돌때 필요한 정리 작업 수행
    }
}


```