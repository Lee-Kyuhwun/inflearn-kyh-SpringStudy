package hello.advanced.trace.strategy.template;

import org.junit.jupiter.api.Test;

public class TemplateCallBackTest {
    // 탬플릿 콜백 패턴
    @Test
    void callbackV1(){
        TimeLogTemplate templateCallBack = new TimeLogTemplate();
        templateCallBack.excute(new CallBack() {
            @Override
            public void call() {
                System.out.println("비즈니스 로직1 실행");
            }
        });


        templateCallBack.excute(new CallBack() {
            @Override
            public void call() {
                System.out.println("비즈니스 로직2 실행");
            }
        });

    }

    @Test
    void callbackV2(){
        TimeLogTemplate templateCallBack = new TimeLogTemplate();
        templateCallBack.excute(() -> System.out.println("비즈니스 로직1 실행"));
        templateCallBack.excute(() -> System.out.println("비즈니스 로직 2 실행"));
    }


}
