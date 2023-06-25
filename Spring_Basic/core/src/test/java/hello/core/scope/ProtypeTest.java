package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

import static org.assertj.core.api.Assertions.*;

public class ProtypeTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find protypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find protypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
    }
    /*
    find protypeBean1
    PrototypeBean.init
    find protypeBean2
    PrototypeBean.init
    prototypeBean1 = hello.core.scope.ProtypeTest$PrototypeBean@117159c0
    prototypeBean2 = hello.core.scope.ProtypeTest$PrototypeBean@3e27ba32
    */
    //서로 주소가 다르다.
    // init이 만들어진걸보니 바로 생성이 되고 호출이 된것이다.

    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }


    }
}
