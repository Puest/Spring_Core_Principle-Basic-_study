package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPlicyTest {
    RateDiscountPlicy rateDiscountPlicy = new RateDiscountPlicy();

    @Test   // 성공 테스트
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        int discount = rateDiscountPlicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test   // 실패 테스트
    @DisplayName("VIP는 10% 할인이 적용되지 않아야 한다.")
    void vip_x() {
        //given
        Member member = new Member(2L, "memberB", Grade.BASIC);

        //when
        int discount = rateDiscountPlicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(1000);
    }
}