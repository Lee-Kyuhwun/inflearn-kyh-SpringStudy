package hello.proxy.code;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealSubject implements Subject {

    @Override
    public String operation(String param) {
        return "";
    }

    @Override
    public String operation() {
        log.info("operation 실제 객체 호출");
        this.sleep(1000);
        return "data";
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
