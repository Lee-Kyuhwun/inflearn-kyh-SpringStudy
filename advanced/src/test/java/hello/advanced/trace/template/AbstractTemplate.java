package hello.advanced.trace.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    public void execute(){
        long startTime = System.currentTimeMillis();
        // 로직 실행
        call(); // 상속 

        log.info("BEGIN logic1");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
        log.info("END logic1");
    }

    protected abstract void call();
}
