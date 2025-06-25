package hello.proxy.advisor;

import hello.proxy.common.ServiceInterface;
import hello.proxy.common.advice.TImeAdvice;
import hello.proxy.proxyfactory.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Proxy;

@Slf4j
public class AdvisorTest {

    @Test
    void advisorTest1(){
        ServiceInterface target = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TImeAdvice());

        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        proxy.save();
        proxy.find();
    }

    @Test
    @DisplayName("직접 만드는 포인트컷")
    void advisorTest2(){
        ServiceImpl target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new
                MyPointcut(), new TImeAdvice());
        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        proxy.save();
        proxy.find();
    }


    @Test
    @DisplayName("직접 만드는 포인트컷")
    void advisorTest3(){
        ServiceImpl target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new
                MyPointcut(), new TImeAdvice());
        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        proxy.save();
        proxy.find();
    }



    static class MyPointcut implements Pointcut {
        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return MethodMatcher.TRUE;
        }
    }

    static class MyMethodMatcher implements MethodMatcher {
        @Override
        public boolean matches(java.lang.reflect.Method method, Class<?> targetClass) {

            boolean result = method.getName().equals("save");
            return result; // 모든 메소드에 대해 매칭
        }

        @Override
        public boolean isRuntime() {
            return false; // 런타임 체크가 필요하지 않음
        }

        @Override
        public boolean matches(java.lang.reflect.Method method, Class<?> targetClass, Object... args) {
            return true; // 모든 메소드에 대해 매칭
        }
    }

}
