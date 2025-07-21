package hello.proxy.postprocessor;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.ObjectInputFilter;

public class BasicTest {

    @Test
    void basicConfig() {

        // Create an ApplicationContext using AnnotationConfigApplicationContext
        ApplicationContext context = new AnnotationConfigApplicationContext();
    }



    @Slf4j
    static class A{
        public A() {
            log.info("A 생성");
        }


    }
    @Slf4j
    static class B{
        public B() {
            log.info("A 생성");
        }


    }

}
