package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {
//    변경 전
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();


//    변경 후
//    MemberServiceIpml와 동일하게 의존관계 주입했음..
//    설계 변경으로 OrderServiceImpl은 FixDiscountPolicy를 의존하지 않는다.
//    단지 DiscountPolicy 인터페이스만 의존한다.
//    OrderServiceImpl입장에서는 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다.
//    OrderServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정한다.
//    OrderServiceImpl은 이제부터 실행에만 집중하면 된다.
//    정리 : OrderServiceImpl에는 AppConfig를 통해서 MemoryMemberRepository, FixDiscountPolicy 객체의 외존관계가 주입된다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }




    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
