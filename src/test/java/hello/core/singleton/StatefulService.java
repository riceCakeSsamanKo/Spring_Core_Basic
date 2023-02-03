package hello.core.singleton;

public class StatefulService {

    public int order(String name, int price) {
        System.out.println("name = " + name + " price= " + price);
        return price;  // 값을 공유하는 필드를 없애버림
    }
}
