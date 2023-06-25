package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;

import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "frontControllerServletV4",urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerV4Map = new HashMap<>();

    public FrontControllerServletV4() {
        //매핑정보를 미리 담아둠
        controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");
        String requestURI = request.getRequestURI();// 매핑에서 받아온 URI 꺼내옴


        ControllerV4 controller = controllerV4Map.get(requestURI);// 찾아돈다.
        /*
        * /front-controller/v1/members/new-form 이게 호출 되면 MemberFormControllerV1()이 반환되고
        * /front-controller/v1/members/save 이게 호출 되면 MemberSaveControllerV1()이 반환 된다.
        * */
        if (controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);// 없으면 404 반환
            return;
        }


        Map<String, String> paramMap = createParaMap(request);
        Map<String,Object> model = new HashMap<>();




        //잘될경우 process 호출하면 된다.
        String viewName = controller.process(paramMap, model);


        //호출되면 /WEB-INF/views/new-form.jsp 이런 형식으로 만들어진다.
        MyView view = viewResolver(viewName);
        view.render(model, request,response);
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
