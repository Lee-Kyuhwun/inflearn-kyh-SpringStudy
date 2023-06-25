package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import java.net.ProtocolException;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
      AnnotationConfigApplicationContext ac =    new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean protypeBean1  = ac.getBean(PrototypeBean.class);//bean을 찾는다.
        protypeBean1.addCount();
        assertThat(protypeBean1.getCount()).isEqualTo(1);

        PrototypeBean protypeBean2  = ac.getBean(PrototypeBean.class);//bean을 찾는다.
        protypeBean2.addCount();
        assertThat(protypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);


        ClientBean clientBean1 =   ac.getBean(ClientBean.class);
        int count1= clientBean1.logic();
        assertThat(count1).isEqualTo(1);
        ClientBean clientBean2 =   ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean{

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count =0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("prototypeBean.init = " +this);//this하면 나의 참조값을 볼 수 있다.
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
