package hello.advanced.trace.template.code;

import hello.advanced.trace.template.AbstractTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubClassLogic1 extends AbstractTemplate {
    @Override
    protected void call() {
        log.info("SubClassLogic1.call");
    }
}
