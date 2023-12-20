package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {
    @Test
    void findAllBean() {
        // AutoAppConfig, DiscountService 스프링 빈 등록
        // AutoAppConfig에서 컴포넌트 스캔을 통해 @Component가 붙은 것들 자동 빈 등록됨
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);

        //discount의 discountCode가 "fixDiscountPolicy"인 경우 1000원할인이 맞는지 확인
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
        Assertions.assertThat(discountPrice).isEqualTo(1000);

        //discount의 discountCode가 "rateDiscountPolicy"인 경우 2000원할인이 맞는지 확인
        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        Assertions.assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        // 모든 DiscountPolicy를 주입
        /* @Autowired  // 생성자 1개는 생략 O */
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            //get(Key=discountCode)를 통해 discountCode의 맞는 할인 정책을 조회
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);

            //해당 할인 정책에 대한 계산(할인) 금액을 return
            return discountPolicy.discount(member, price);
        }
    }
}
