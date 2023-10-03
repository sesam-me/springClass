package hello.core;

import hello.core.dicsount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
//    AppConfig는 애플리케이션의 실제 동작에 필요한 "구현 객체를 생성"한다.
//    AppConfig는 MemoryMemberRepository객체를 생성하고 그 참조값 MemberServiceImpl을 생성하면서 생성자로 전달한다.
//    클라이언트인 MemberServiceImpl입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI(Dependency Injection) = 의존관계 주입 또는 의존성 주입이라고 한다.

    public MemberService memberService() {
//    AppConfig는 생선한 객체 인스턴스의 참조(레퍼런스)를 "생성자를 통해서 주입(연결)"한다.
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}