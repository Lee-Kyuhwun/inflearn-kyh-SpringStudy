package hello.proxy.concretetproxy.code;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConCreteLogic {

    public String operation(){
        log.info("ConcreteLogic 실행");
        return "data";
    }


}
