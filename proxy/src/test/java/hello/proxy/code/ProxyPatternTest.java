package hello.proxy.code;

import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxy() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
    }
}
