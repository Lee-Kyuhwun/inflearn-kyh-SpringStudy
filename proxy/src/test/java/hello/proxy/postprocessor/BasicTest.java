package hello.proxy.postprocessor;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ObjectInputFilter;

public class BasicTest {

    @Test
    void basicConfig() {

        // Create an ApplicationContext using AnnotationConfigApplicationContext
        ApplicationContext context = new AnnotationConfigApplicationContext();

        // bean A 이름으로 B 객체가 빈으로 등록된다.
        A a = context.getBean(A.class);



    }


    @Configuration
    static class BeanPostProcessorConfig {
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }
        @Bean
        public AToBPostProcessor helloPostProcessor() {
            return new AToBPostProcessor();
        }
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

    @Slf4j
    static class AToBPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(Object bean, String
                beanName) throws BeansException {
            log.info("beanName={} bean={}", beanName, bean);
            if (bean instanceof A) {
                return new B();
            }
            return bean;
        }
    }


}
