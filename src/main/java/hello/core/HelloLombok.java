package hello.core;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter   // setter() 자동생성
@Getter   // getter() 자동생성
@ToString // toString() 자동 생성
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
       HelloLombok helloLombok = new HelloLombok();
       helloLombok.setName("Hello");

        String name = helloLombok.getName();
        System.out.println("helloLombok = " + helloLombok);
    }
}
