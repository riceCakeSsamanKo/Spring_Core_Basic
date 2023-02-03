package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        /*
         * AppConfig 클래스를 인자로 넣게되면
         * 해당 클래스의 @Bean이 붙은 메소드를 스프링 컨테이너에 등록함
         */
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);


        /*스프링 컨테이너에 저장된 memberService 함수 실행*/
        MemberService memberService =
                applicationContext.getBean("memberService", MemberService.class);//꺼낼 메소드명, 리턴 타입


        Member member1 = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member1);
        Member member2 = memberService.findMember(1L);

        System.out.println("member1= " + member1.getName());
        System.out.println("member2= " + member2.getName());
    }
}
