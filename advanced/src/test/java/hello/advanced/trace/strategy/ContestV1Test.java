package hello.advanced.trace.strategy;

import org.junit.jupiter.api.Test;

public class ContestV1Test {


    @Test
    void stategyV1(){
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();

        ContextV1 contextV1 = new ContextV1(strategyLogic1);

        contextV1.execute();

    }

    @Test
    void stategyV2(){

        Strategy strategy = new Strategy() {
            @Override
            public void call() {
                System.out.println("비즈니스 로직1 실행");
            }
        };
        ContextV1 contextV1 = new ContextV1(strategy);
        contextV1.execute();

    }



    @Test
    void stategyV3(){

        ContextV1 contextV1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                System.out.println("비즈니스 로직1 실행");
            }
        });
        contextV1.execute();

    }

    @Test
    void stategyV4(){

            ContextV1 contextV1 = new ContextV1(() -> System.out.println("비즈니스 로직1 실행"));
            contextV1.execute();

    }
}
