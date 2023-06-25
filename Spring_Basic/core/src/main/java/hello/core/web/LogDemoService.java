package hello.core.web;


import hello.core.common.MyLoger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor//의존관계주입받기
public class LogDemoService {

    private final ObjectProvider<MyLoger> myLoggerProvider;
    public void logic(String id) {
        MyLoger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = "+ id);
    }
}
