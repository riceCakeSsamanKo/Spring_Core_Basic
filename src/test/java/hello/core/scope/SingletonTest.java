package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    void singletonBeanFind(){
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(SingletonBean.class);  // 빈 객체 생성

        SingletonBean bean1 = ac.getBean(SingletonBean.class);
        SingletonBean bean2 = ac.getBean(SingletonBean.class);

        System.out.println("singletonBean1 = " + bean1);
        System.out.println("singletonBean2 = " + bean2);
        assertThat(bean1).isSameAs(bean2);

        ac.close();  //destroy 메소드 호출후 빈 소멸
    }

    @Scope("singleton")  // 원래는 singleton이 디폴트라서 굳이 입력 안 해줘도 된다.
    static class SingletonBean{
        @PostConstruct
        public void itit(){
            System.out.println("SingletonBean.itit");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destroy");
        }
    }
}
