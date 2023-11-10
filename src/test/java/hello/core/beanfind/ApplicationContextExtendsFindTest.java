package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new
            AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입을 조회 시, 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate() {
//        DiscountPolicy bean = ac.getBean(DiscountPolicy.class); // NoUniqueBeanDefinitionException
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }


    @Test
    @DisplayName("부모 타입을 조회 시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.") // 그럼 지정해준 빈 이름이 있는지 확인해줌..
    void findBeanByParentTypeBeanName() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class); // rateDiscountPolicy는 RateDiscountPolicy.class의 인스턴스인가.
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회") // 구체적으로 지정해주는 건 좋은 방법이 아님..
    void findBeanBySubType() {
        // RateDiscountPolicy로 지정해줬음..
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);// RateDiscountPolicy 클래스에 있는 bean가져오기
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) { // 실제 테스트코드에서는 print를 남겨두지 않는게 좋음 -> 자동통과 시스템을 만드는데 눈으로 보고 있을 수는 없음..
            System.out.println("key = " + key + ", value = " + beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object") // 자바 객체는 모든 게 Object타입이기 때문에
    void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) { // 스프링 빈에 등록된 모든 객체가 나옴..
            System.out.println("key = " + key + ", value = " + beansOfType.get(key));
        }
    }


    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
