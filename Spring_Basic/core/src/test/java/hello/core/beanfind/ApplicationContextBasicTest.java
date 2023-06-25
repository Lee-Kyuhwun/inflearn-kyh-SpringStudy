package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;

import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);



    @Test
    @DisplayName("빈 이름 조회")
    void findByName(){
       MemberService memberService= ac.getBean("memberService", MemberService.class);
    //검증은 Assertions
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름없이 타입으로만 조회")
    void findByType(){
        MemberService memberService= ac.getBean(MemberService.class);
        //검증은 Assertions
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름없이 타입으로만 구체적으로 조회")
    void findByType2(){
        MemberService memberService= ac.getBean(MemberServiceImpl.class);
        //검증은 Assertions
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    //이것은 역할에만 의존해야하는 코딩에 어긋나지만 가끔 안풀릴때 사용하면 유용하다.

    @Test
    @DisplayName("빈 이름 조회 실패")
    void findBeanByType(){
        //ac.getBean("xxxx", MemberService.class);

        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxxx", MemberService.class));
        //() -> ac.getBean("xxxxxx", MemberService.class) 이게 실행하면
        //NoSuchBeanDefinitionException.class이 터져야한다는 뜻이다.
    }
    //실패 테스트도 꼭 해줘야 한다!.
}
