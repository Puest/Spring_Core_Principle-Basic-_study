package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient /*implements InitializingBean, DisposableBean*/ {
    //접속할 URL 서버
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    //외부에서 URL 설정
    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    //서버에 연결이 된 상태에서 서버에 메시지 던지기
    public void call(String message) {
        System.out.println("call: " + url + ", message: " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    // 초기화 메서드 - @Bean(initMethod="<메서드 이름>")으로 지정
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    // 소멸 메서드 - @Bean(destroyMethod="(inferred)")으로 지정 → (inferred)는 close,destroy 를 찾음, 사용 안 할 시 ""
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
/*
    // 초기화 메서드(의존관계 주입이 끝나면 호출) - InitializingBean 에서 상속
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    // 소멸 메서드(빈이 종료되기 전 호출) - DisposableBean 에서 상속
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
    */
}
