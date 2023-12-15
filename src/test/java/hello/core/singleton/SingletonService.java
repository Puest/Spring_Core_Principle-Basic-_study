package hello.core.singleton;

public class SingletonService {

    // static 영역을 통해 객체를 딱 1개만 생성한다.
    private static final SingletonService instance = new SingletonService();

    // 객체 필요시 조회가능하도록 생성
    public static SingletonService getInstance(){
        return instance;
    }
    
    //생성자
    private SingletonService(){
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
