package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new
            AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름 조회")
    void findBeanName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // isInstanceOf : MemberServiceImpl.class가 memberService의 인스턴스인가.
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // isInstanceOf : MemberServiceImpl.class가 memberService의 인스턴스인가.
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        // 이건 구현에 의지한거라 좋지않음.. 역할에 의존하는게 좋음 => 유연성이 떨어지기 때문에
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // isInstanceOf : MemberServiceImpl.class가 memberService의 인스턴스인가.
    }


    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByTypeDuplicate() {
//        MemberService xxxx = ac.getBean("xxxx", MemberService.class); // 이걸 실행하면 "xxxx"라는 빈은 없기 때문에 NoSuchBeanDefinitionException라는 오류가 뜸

//        () -> ac.getBean("xxxx", MemberService.class)를 실행했을 때, NoSuchBeanDefinitionException 에러가 뜨면 테스트 성공~!
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxx", MemberService.class));
    }

}
