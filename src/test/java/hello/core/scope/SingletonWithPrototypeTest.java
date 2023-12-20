package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest {
    @Test
    void prototypeFind() {
        // PrototypeBean 빈 등록
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        // 프로토타입 1 조회
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        // 프로토타입 2 조회
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        // ClientBean, PrototypeBean 빈 등록
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean {
       /* private final PrototypeBean prototypeBean;  //싱글톤에 의해 생성 시점에 주입 */

        @Autowired
        /*Provider 은 ObjectFactory, ObjectProvider 와 같음*/
        private Provider<PrototypeBean> prototypeBeansProvider;

        /*  @RequiredArgsConstructor 사용 시 생성자(의존관계) 따로 제작 필요 X
                @Autowired  //생략 O
                public ClientBean(PrototypeBean prototypeBean) {
                    this.prototypeBean = prototypeBean;
                }
        */
        public int logic() {
            // logic() 호출할 때마다 프로토타입 빈을 대신 조회해서 반환(DL)
            PrototypeBean prototypeBean = prototypeBeansProvider.get();
            prototypeBean.addCount();   //PrototypeBean 의 addCount() 호출
            int count = prototypeBean.getCount();
            return count;   //카운트 반환
        }
    }

    // 프로토타입 빈
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
