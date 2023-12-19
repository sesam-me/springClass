package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member", // 탐색 위치
//        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // 기존 AppConfig인 @Configuration을 AppConfig exclude하기 위해
)
public class AutoAppConfig {
    @Bean(name = "memberMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
