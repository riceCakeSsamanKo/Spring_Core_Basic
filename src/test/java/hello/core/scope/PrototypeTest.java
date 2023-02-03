package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 싱글톤은 빈을 등록함과 동시에 객체가 생성됨. 그 후 DI 및 초기화가 진행된다.
 * 하나의 객체를 여러 클라이언트가 공유해 사용하고, 관리 책임은 스프링이 가진다.
 * 프로토타입 스코프 빈은 클라이언트가 요청시 마다 스프링 컨테이너가 새로운 프로토타입 빈을 생성하고 DI 및 초기화가 진행됨
 * 그 후 생성된 빈은 클라이언트에게 반환되고 반환된 빈은 스프링이 아닌 클라이언트가 관리함
 * 즉 프로토타입 스코프 빈의 관리 책임은 클라이언트가 가진다.
 */

public class PrototypeTest {

    @Test
    void prototypeTest(){
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);  // 객체 생성 후 반환
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);  // 객체 생성 후 반환

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        // 프로토타입은 클라이언트에서 요청시마다 새로운 객체 생성 후 반환

        ac.close();
        // 프로토타입 스코프는 객체 생성과 의존관계 주입, 초기화만 스프링에서 관리하고
        // 그 후에는 스프링 대신 클라이언트가 관리함.
        // 프로토타입 스코프는 스프링에서 관리하는 것이 아니라서 destroy 메소드 호출 안 됨

        prototypeBean1.destroy();
        prototypeBean2.destroy();
    }

    @Scope("prototype")  // prototype 스코프 등록
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destroy "+this);
        }
    }
}


