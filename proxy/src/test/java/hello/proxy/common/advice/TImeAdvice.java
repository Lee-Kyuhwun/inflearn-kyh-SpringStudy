package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


@Slf4j
public class TImeAdvice implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeInvocationHandler 실행");

        long startTime = System.currentTimeMillis();

        Object result = invocation.proceed(); // 실제 메서드 호출


        long endTime = System.currentTimeMillis();
        log.info("resultTime = {}", endTime - startTime);


        return result;
    }
}
