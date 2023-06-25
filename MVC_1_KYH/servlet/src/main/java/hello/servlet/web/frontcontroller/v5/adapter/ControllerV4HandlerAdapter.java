package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4); //V4지원하는지 확인
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;
        Map<String, String> paramMap = createParamMap(request);
        Map<String,Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);

        ModelView mv = new ModelView(viewName);
        mv.setModel(model); //view, model 둘다 세팅해준다.
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
