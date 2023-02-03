package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( /*@Component 어노테이션이 붙은 클래스를 찾아서 자동으로 스프링 빈으로 등록*/
        //basePackages = "hello.core.member", /*member 패키지만 컴포넌트 스캔 대상*/
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        /* 컴포너트 스캔에서 제외할 대상을 지정: @Confiuration 붙은 클래스는 제외함(@Configuration 내부에 @Component 있음)
        -> AppConfig가 자동으로 등록되지 않도록하기 위해서
        */
)
public class AutoAppConfig {

    /*@Bean(name="memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }*/
}