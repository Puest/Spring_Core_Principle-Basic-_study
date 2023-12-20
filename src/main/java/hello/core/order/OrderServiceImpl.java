package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
//@RequiredArgsConstructor    //final이 붙은 필드를 모아서 생성자를 자동 생성
public class OrderServiceImpl implements OrderService {

    // final이 붙었다면 값이 무조건 존재해야한다.(setter, 일반 메서드로 수정자 주입시 final 제거)
    // 생성자 주입을 쓰면 final 키워드를 꼭 사용해 생성자에서 초기화 누락을 방지, 불변하도록 설계 O
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


/*
//    2. 수정자 주입
    @Autowired(required = false)   //주입 대상이 없어도 동작하려면(required = false)
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
//    2. 수정자 주입
    @Autowired    //주입 대상이 없어도 동작하려면(required = false)
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
*/

/*
    일반 메서드 주입(쓸 일이 손에 꼽을 정도로 없음 → 생성자, 수정자(setter)에서 끝남)
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
*/

    //1. 생성자 주입(생성자가 1개일 경우 @Autowired 생략 가능)
//  @RequiredArgsConstructor에 의해 입력 필요 X(가끔 생성자 직접 필요시에만 사용)
//    @Autowired private DiscountPolicy rateDiscountPolicy;//필드명으로 지정
    /*@Autowired 생성자 1개는 생략 O*/
    //파라미터명으로 지정 discountPolicy → rateDiscountPolicy
    //@Qualifier는 @Qualifier를 찾는용도로만 사용하자.
    public OrderServiceImpl(MemberRepository memberRepository, /*@Qualifier("fixDiscountPolicy")*/DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }
}
