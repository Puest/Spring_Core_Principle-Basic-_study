package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutoWiredTest {
    @Test
    void AutoWiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        //1. 자동 주입할 대상이 없으면, 수정자 메서드 호출 X
        @Autowired(required = false)
        //Member가 스프링 빈이 아니기 때문에 required가 true일 경우, 오류 발생
        public void setNoBean(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        //2. 자동 주입할 대상이 없으면, null 호출
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        //3. 자동 주입할 대상이 없으면, Optional.empty 호출
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
