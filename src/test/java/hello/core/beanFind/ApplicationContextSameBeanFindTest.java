package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Same;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac =
            new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시에 같은 타입이 둘 이상 있으면, 중복오류가 발생한다")
    void findBeanByTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class,
                ()->ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByName(){
        MemberRepository memberRepository =
                ac.getBean("memberRepository1",MemberRepository.class);

        assertThat(memberRepository).isInstanceOf(MemoryMemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);

        /*
        key == 메소드명
        val == 메소드 참조 값
         */
        for (String key : beansOfType.keySet()) {
            System.out.println("key = "+ key + " val = " + beansOfType.get(key));
        }
        System.out.println("beansOfType= " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }
    @Configuration
    static class SameBeanConfig{  // ApplicationContextSameBeanFindTest에서만 사용하는 static 클래스

        @Bean  // MemberRepository 타입
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }
        @Bean  // MemberRepository 타입 (타입 중복)
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
