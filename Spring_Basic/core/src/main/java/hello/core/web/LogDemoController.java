package hello.core.web;


import hello.core.common.MyLoger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLoger myLogger;

    @RequestMapping("log-demo") //log-demo라는 요청이 오면
    @ResponseBody// view화면없이 바로 반환하고싶어서
    public String logDemo(HttpServletRequest request){
        MyLoger myLogger = myLogger.getObject();
        String requestURL = request.getRequestURI().toString();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
