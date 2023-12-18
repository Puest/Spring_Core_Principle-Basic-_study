package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy") → 문자를 잡을 수 없기 때문에 아래와 같이 사용
//@MainDiscountPolicy
//@Primary    //우선권 부여
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10; //1000원 할인

    @Override
    public int discount(Member member, int price) {
        // Enum타입은 ==(O), equals(X)
        if (member.getGrade() == Grade.VIP) {
            // 실제 실무에서 할인관련(10%)은 복잡하다.
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
