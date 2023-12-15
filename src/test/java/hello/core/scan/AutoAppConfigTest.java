package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        // 설정 정보를 AutoAppConfig로 넘겨서 스프링 컨테이너 생성
        // AutoAppConfig는 스프링 빈으로 직접 등록됨
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        // MemberService 조회
        MemberService memberService = ac.getBean(MemberService.class);
        // memberService 인스턴스가 MemberService 타입인지 검증
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

    }
}
