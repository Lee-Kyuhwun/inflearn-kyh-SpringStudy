package hello.proxy.app.config.v1_proxy.v2_proxy;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


@RequiredArgsConstructor
@Slf4j
public class LogTraceBasicHandler implements InvocationHandler {
    private final Object target;
    private final LogTrace logTrace;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TraceStatus status = null;
        try {
            String message = method.getDeclaringClass().getSimpleName() + "."
                    + method.getName() + "()";
            status = logTrace.begin(message);
//로직 호출

            Object result = method.invoke(target, args);
            logTrace.end(status);
            return result;
        } catch (
                Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}

