package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    
    //순수 자바로 OrderServiceImpl 테스트 → NPE 발생(), 수정자 주입
    /*@Test
    void createOrder() {
        OrderServiceImpl orderService = new OrderServiceImpl();
        Order order = orderService.createOrder(1L,"itemA",10000);
    }*/

    //순수 자바로 OrderServiceImpl 테스트, 생성자 주입 → 컴파일 오류 해결
    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L,"name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L,"itemA",10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}