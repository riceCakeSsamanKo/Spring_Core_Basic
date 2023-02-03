package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Dip 원칙의 준수를 위해서 따로 생성한 AppConfig 클래스
 *
 * 생성자 주입을 통해서 service 클래스들(OrderServiceImpl, MemberServiceImpl)은
 * 의존관계에 대한 고민을 더 이상 하지 않아도 되게 되었다.
 *
 * 더 이상 의존 관계 변경시에 클라이언트 코드를 변경하지 않아도 되고
 * AppConfig 내부 메소드의 인자만 변경해주면 되게 되었다.
 */
@Configuration // @Configuration : 설정 정보
public class AppConfig {
    /**
     *AppConfig는 어플리케이션의 실제 동작에 필요한 구현 객체의 생성을 담당한다.
     * 생성한 객체의 인스턴스의 참조를 "생성자 주입"해준다.
     */

    @Bean  // @Bean : 스프링 컨테이너 등록
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
