//package hello.core.order;
//
//import hello.core.AppConfig;
//import hello.core.member.*;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class OrderServiceTest {
////    MemberService memberService = new MemberServiceImpl(memberRepository);
////    OrderService orderService = new OrderServiceImpl(memberRepository, discountPolicy);
//
//
//    MemberService memberService;
//    OrderService orderService;
//
//    @BeforeEach // @BeforeEach : Test 전에 꼭 실행되는 것.. Test가 2개 있으면 2번 돌아감..
//    public void beforeEach() {
//        AppConfig appConfig = new AppConfig();
//        memberService = appConfig.memberService();
//        orderService = appConfig.orderService();
//    }
//
//
//    @Test
//    void createOrder() {
//        Long memberId = 1L;
//        Member member = new Member(memberId, "memberA", Grade.VIP);
//        memberService.join(member);
//
//        Order order = orderService.createOrder(memberId, "itemA",10000);
//
//        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
//    }
//   }
