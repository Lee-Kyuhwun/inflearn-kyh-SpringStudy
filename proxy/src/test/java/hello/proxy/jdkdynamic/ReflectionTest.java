package hello.proxy.jdkdynamic;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ReflectionTest {


    @Test
    void reflection() throws Exception {
        Hello target = new Hello();

        // 공통로직시작
        log.info("Start1");
        String result1 = target.callA();
        log.info("result1={}", result1);
        // 공통로직종료

        // 공통로직시작2
        log.info("Start2");
        String result2 = target.callB();
        log.info("result2={}", result2);



    }

    @Test
    void reflectionV2() throws Exception {
        Hello target = new Hello();

        // callA 메서드 호출
        log.info("Start1");
        Object callA = Hello.class.getMethod("callA").invoke(target);
        String result1 = (String) callA;
        log.info("result1={}", result1);



        // callB 메서드 호출
        log.info("Start2");
        String result2 = (String) Hello.class.getMethod("callB").invoke(target);
        log.info("result2={}", result2);
    }

    private void dynamicCall(String methodName) throws Exception {
        Hello target = new Hello();
        log.info("Start {}", methodName);
        Object result = Hello.class.getMethod(methodName).invoke(target);
        log.info("result={}", result);
    }


    @Slf4j
    public static class Hello {

        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }

}
