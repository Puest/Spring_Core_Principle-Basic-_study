package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

//설정 정보
@Configuration
// @Component가 붙은 클래스를 자동으로 스프링 빈으로 등록(@Configuration이 붙은 클래스는 제외)
@ComponentScan(
        //basePackages,basePackagesClasses 가 없는경우 @ComponentScan이 붙은 클래스의 패키지가 기본 시작위치(즉, hello.core)
//        basePackages = "hello.core.member",
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {

    // Bean 이름이 memoryMemberRepository인 수동 빈 등록 → 원래 있던 memoryMemberRepository 빈을 오버라이딩
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
