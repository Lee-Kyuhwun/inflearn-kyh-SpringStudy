package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void singletonBeanFind(){
        AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext(SingletonBean.class);
        //이렇게 하면 직접 등록하면 component scan이 되어서 자동으로 등록이 된다.
      SingletonBean singletonBean1 =   ac.getBean(SingletonBean.class);
      SingletonBean singletonBean2 =   ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);
        //singletonBean1 과 singtonBean2가 같은지 확인하는과정
       /*
        singletonBean1 = hello.core.scope.SingletonTest$SingletonBean@3e27ba32
        singletonBean2 = hello.core.scope.SingletonTest$SingletonBean@3e27ba32
        */
        // 이렇게 동일한 것을 확인 할 수 있다.
        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean{
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("singletonBean.destroy");
        }
    }
}
