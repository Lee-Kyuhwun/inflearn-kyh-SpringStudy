package hello.proxy.jdkdynamic;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {


    @Test
    void dynamicA(){
        AInterface ainterface = new AImpl();

        TimeInvocationHandler timeInvocationHandler = new TimeInvocationHandler(ainterface);

        AInterface proxy = (AInterface)Proxy.newProxyInstance(AInterface.class.getClassLoader(),
                new Class[]{AInterface.class}, timeInvocationHandler);

        proxy.call();
        log.info("proxy class={}", proxy.getClass());
        log.info("proxy interface={}", ainterface.getClass());

    }


}
