package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    private SingletonService(){} // private 생성자로 객체가 하나만 생성되도록 함 (외부에서 new 방지)

    public static SingletonService getInstance(){  // instance를 꺼내기 위한 유일한 방법
        return instance;
    }
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
