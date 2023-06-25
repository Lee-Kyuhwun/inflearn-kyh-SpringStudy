package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
    @Test
    void statefulSerivcieSingleton(){
        ApplicationContext ac= new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService",StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService",StatefulService.class);



        //ThreadA : A사용자 10000원 주문
        int userA =statefulService1.order("userA",10000);
        //ThreadA : B사용자 20000원 주문
        int userB = statefulService2.order("userB",20000);


        //ThreadA : 사용자 a 주문금액 조회
        System.out.println("userA = " + userA);

    }


    static class TestConfig{
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}