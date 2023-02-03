package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
/*클래스 내부에 getter, setter를 만들지 않아도 사용 가능*/
@ToString
/*클래스 내부에 toString() 만들지 않아도 사용 가능*/
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("lombok");
        String name = helloLombok.getName();

        System.out.println("name = " + name);
        System.out.println("helloLombok = " + helloLombok);
    }
}
