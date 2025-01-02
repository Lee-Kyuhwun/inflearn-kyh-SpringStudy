package hello.advanced.trace.strategy;

import org.junit.jupiter.api.Test;

public class ContestV2Test {


    // 전략패턴 적용
    @Test
    void stategyV1(){
        ContextV2 contextV2 = new ContextV2();

        contextV2.execute(new StrategyLogic1());
        contextV2.execute(new StrategyLogic2());


    }

    @Test
    void stategyV2(){

        Strategy strategy = new Strategy() {
            @Override
            public void call() {
                System.out.println("비즈니스 로직1 실행");
            }
        };
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(strategy);

        contextV2.execute(new Strategy() {
            @Override
            public void call() {
                System.out.println("비즈니스 로직2 실행");
            }
        });
    }



    @Test
    void stategyV3(){

        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(() -> System.out.println("비즈니스 로직1 실행"));


    }

    @Test
    void stategyV4(){

            ContextV1 contextV1 = new ContextV1(() -> System.out.println("비즈니스 로직1 실행"));
            contextV1.execute();

    }
}
