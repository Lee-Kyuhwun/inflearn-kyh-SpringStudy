package hello.proxy.decorator.code;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component {


    private Component component;

    public MessageDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("MessageDecorator 실행");

        //data가 반환되었는데 그걸 꾸며줌
        String operation = component.operation();
        String decoratedResult = "*****" + operation + "*****";

        log.info("decoratedResult 꾸미기 적용 전 = {} , 적용 후 =  {}",operation, decoratedResult);
        return decoratedResult;
    }
}
