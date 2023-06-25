package hello.servlet.domain.member;


import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Member {

    private Long id; // 아이디는 회원 레포지토리에 저장해둘것 그러면 아이디가 발급된다.
    private String username;
    private int age;

    public Member() {
    }// 기본 생성자

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }


}
