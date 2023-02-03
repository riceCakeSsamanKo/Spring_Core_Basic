package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {
    ApplicationContext ac                            /* 구성정보 클래스를 한번에 여러개 등록 가능 */
            = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
    // AutoAppConfig로 부터 fixDiscountPolicy, rateDiscountPolicy 빈을
    // 읽어와서 DiscountService의 PolicyMap과 Policies에 넣어준다.

    @Test
    void findAllBean(){
        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.VIP);

        //fixDiscountPolicy를 적용했을 때 discountPrice
        int fixDiscountPrice = discountService.discount(member, 20000, "fixDiscountPolicy");
        //rateDiscountPrice를 적용했을 때 discountPrice
        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(fixDiscountPrice).isEqualTo(1000);
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    //@Service
    static class DiscountService{
        // Map과 List를 사용함으로써 동적으로 빈을 선택할 수 있다.
        private final Map<String, DiscountPolicy> policyMap;  // Map에 DiscountPolicy 빈들을 넣어놓음.
        private final List<DiscountPolicy> policies;

        //@Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            // Map에 넣어놓은 빈을 꺼내서 사용함
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            return discountPolicy.discount(member,price);
        }
    }
}
