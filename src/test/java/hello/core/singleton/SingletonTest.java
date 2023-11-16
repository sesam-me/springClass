package hello.core.singleton;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

//    @Test
//    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
//    void singletonServiceTest() {
//        new SingletonService();
//    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // 호출할 때마다 객체를 생성하는지 확인해보면 됨
        System.out.println("singletonService1 =" + singletonService1);
        System.out.println("singletonService2 =" + singletonService2);

        // 같은 객체인지 확인
        // import org.assertj.core.api.Assertions;
        Assertions.assertThat(singletonService1).is(singletonService2);
    }
}
