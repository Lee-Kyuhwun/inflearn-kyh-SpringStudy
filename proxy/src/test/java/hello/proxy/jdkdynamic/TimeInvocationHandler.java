package hello.proxy.jdkdynamic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


@RequiredArgsConstructor
@Slf4j
public class TimeInvocationHandler implements InvocationHandler {
    private  final Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        log.info("TimeInvocationHandler 실행");

        long startTime = System.currentTimeMillis();

        Object result = method.invoke(target, args); // 실제 메서드 호출


        long endTime = System.currentTimeMillis();
        log.info("resultTime = {}", endTime - startTime);


        return null;

    }
}
