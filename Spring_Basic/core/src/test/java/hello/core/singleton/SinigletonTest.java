package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SinigletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();


        //2 조회 : 호출할때마다 객체르 생성
        MemberService memberService2 = appConfig.memberService();


        //참조값이 다른지 확인
        System.out.println("memberService2 = " + memberService2);
        System.out.println("memberService1 = " + memberService1);


    }

    @Test
    @DisplayName("싱글톤 패턴을 적용")
    void singletonServiceTest(){
        SingletonService singletonService = SingletonService.getInstance();

    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){

    }


}
