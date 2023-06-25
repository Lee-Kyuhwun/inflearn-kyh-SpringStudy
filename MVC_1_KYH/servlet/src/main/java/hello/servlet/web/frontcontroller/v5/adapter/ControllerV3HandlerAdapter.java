package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }
    /*
    * instanceof
- instanceof는 객체 타입을 확인하는 연산자이다.

- 형변환 가능 여부를 확인하며 true / false로 결과를 반환한다.

- 주로 상속 관계에서 부모객체인지 자식 객체인지 확인하는 데 사용된다.
    *
    * */
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response,Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler;
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);
        return mv;
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        //paramMap
        Map<String, String > paramMap = new HashMap<>(); // paramMap이라는 것을 만든다.
        request.getParameterNames().asIterator() //모든 파라미터를 가져오고 올리면서
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        // 네임은 paramName이고 키는 paramMap인데 request.getParameter를 이용해서 다 꺼내온다.
        return paramMap;
    }

}
