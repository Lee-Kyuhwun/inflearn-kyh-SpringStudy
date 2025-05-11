package hello.proxy.concretetproxy.code;


import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {


    @Test
    void noProxy() {
        ConCreteLogic concreteLogic = new ConCreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);
        client.execute();
    }

}
