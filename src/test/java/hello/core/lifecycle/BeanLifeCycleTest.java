package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest() {
        //ConfigurableApplicationContext가 상위 인터페이스라 담기O
        //수동 빈 생성시 new로 컨테이너를 제작하면 close()로 종료를 해줘야함. 자동 빈은 close()까지 자동
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); //스프링 컨테이너 종료(ConfigurableApplicationContext가 필요)
    }

    @Configuration
    static class LifeCycleConfig {
        // NetworkClient 수동 빈 등록
        @Bean/*(initMethod = "init", destroyMethod = "close")*/
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();  // 아래 값을 입력받지 않고 생성자가 먼저 호출되면서 url값이 null이 나옴
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

}
