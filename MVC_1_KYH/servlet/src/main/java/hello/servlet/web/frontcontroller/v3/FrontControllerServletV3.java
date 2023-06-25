package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "frontControllerServletV3",urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

    public FrontControllerServletV3() {
        //매핑정보를 미리 담아둠
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");
        String requestURI = request.getRequestURI();// 매핑에서 받아온 URI 꺼내옴


        ControllerV3 controller = controllerV3Map.get(requestURI);// 찾아돈다.
        /*
        * /front-controller/v1/members/new-form 이게 호출 되면 MemberFormControllerV1()이 반환되고
        * /front-controller/v1/members/save 이게 호출 되면 MemberSaveControllerV1()이 반환 된다.
        * */
        if (controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);// 없으면 404 반환
            return;
        }


        Map<String, String> paramMap = createParaMap(request);





        //잘될경우 process 호출하면 된다.
        ModelView mv = controller.process(paramMap);
        String viewName = mv.getViewName();// 호출되면 알아서 다 붙히고 view객체까지 만들어준다.

        //호출되면 /WEB-INF/views/new-form.jsp 이런 형식으로 만들어진다.
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request,response);
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParaMap(HttpServletRequest request) {
        //paramMap
        Map<String, String > paramMap = new HashMap<>(); // paramMap이라는 것을 만든다.
        request.getParameterNames().asIterator() //모든 파라미터를 가져오고 올리면서
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        // 네임은 paramName이고 키는 paramMap인데 request.getParameter를 이용해서 다 꺼내온다.
        return paramMap;
    }
}
