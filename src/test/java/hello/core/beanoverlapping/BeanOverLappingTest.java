package hello.core.beanoverlapping;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.*;

public class BeanOverLappingTest {

    AnnotationConfigApplicationContext ac =
            new AnnotationConfigApplicationContext(BeanOverLappingConfig.class);

    @Test
    @DisplayName("@Primary로 Robot 받기")
    void primaryRobot(){
        PrimaryRobot bean = ac.getBean(PrimaryRobot.class);
        Toy toy = bean.getToy();
        System.out.println("toy = " + toy);

        assertThat(toy).isInstanceOf(Robot.class);
        assertThat(toy).isNotInstanceOf(Car.class);
    }

    @Test
    @DisplayName("파라미터 명으로 Robot 받기")
    void parameterRobot(){
        ParameterRobot bean = ac.getBean(ParameterRobot.class);
        Toy toy = bean.getToy();
        System.out.println("toy = " + toy);

        assertThat(toy).isInstanceOf(Robot.class);
        assertThat(toy).isNotInstanceOf(Car.class);
    }
//    @Test
//    @DisplayName("파라미터 명으로 Car 받기")
//    void parameterCar(){
//        ParameterCar bean = ac.getBean(ParameterCar.class);
//        Toy toy = bean.getToy();
//        System.out.println("toy = " + toy);
//
//        assertThat(toy).isInstanceOf(Car.class);
//        //assertThat(toy).isNotInstanceOf(Robot.class);
//    }
    @Test
    @DisplayName("@Qualfier로 Robot 받기")
    void qualifierRobot(){
        QualifierRobot bean = ac.getBean(QualifierRobot.class);
        Toy toy = bean.getToy();
        System.out.println("toy = " + toy);

        assertThat(toy).isInstanceOf(Robot.class);
        assertThat(toy).isNotInstanceOf(Car.class);
    }
    @Test
    @DisplayName("@Qualifier로 Car 받기")
    void qualifierCar(){
        QualifierCar bean = ac.getBean(QualifierCar.class);
        Toy toy = bean.getToy();
        System.out.println("toy = " + toy);

        assertThat(toy).isInstanceOf(Car.class);
        assertThat(toy).isNotInstanceOf(Robot.class);
    }

    @Component
    static class PrimaryRobot{
        private final Toy toy;

        //@Primary로 Robot 받기
        @Autowired
        public PrimaryRobot(Toy toy){
            this.toy = toy;
        }

        public Toy getToy() {
            return toy;
        }
    }
    @Component
    static class ParameterRobot{
        private final Toy toy;

        //파라미터 명으로 Robot 받기
        @Autowired
        public ParameterRobot(Toy robot){
            this.toy = robot;
        }

        public Toy getToy() {
            return toy;
        }
    }
    @Component
    static class ParameterCar{
        private final Toy toy;

        //파라미터 명으로 car 받기
        @Autowired
        public ParameterCar(Toy car){
            this.toy = car;
        }

        public Toy getToy() {
            return toy;
        }
    }
    @Component
    static class QualifierRobot{
        private final Toy toy;

        //@Qualifier("robotToy")로 Robot 받기
        @Autowired
        public QualifierRobot(@Qualifier("robotToy")Toy car){
            this.toy = car;
        }

        public Toy getToy() {
            return toy;
        }
    }
    @Component
    static class QualifierCar{
        private final Toy toy;

        //@Qualifier("carToyon")로 car 받기
        @Autowired
        public QualifierCar(@Qualifier("carToy")Toy car){
            this.toy = car;
        }

        public Toy getToy() {
            return toy;
        }
    }

    @Configuration
    @ComponentScan(basePackages = "hello.core.beanoverlapping")
    public static class BeanOverLappingConfig{

    }
}
