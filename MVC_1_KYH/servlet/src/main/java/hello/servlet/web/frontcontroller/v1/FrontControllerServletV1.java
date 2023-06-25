package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "frontControllerServletV1",urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerV1Map = new HashMap<>();

    public FrontControllerServletV1() {
        //매핑정보를 미리 담아둠
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");
        String requestURI = request.getRequestURI();// 매핑에서 받아온 URI 꺼내옴


        ControllerV1 controller = controllerV1Map.get(requestURI);// 찾아돈다.
        /*
        * /front-controller/v1/members/new-form 이게 호출 되면 MemberFormControllerV1()이 반환되고
        * /front-controller/v1/members/save 이게 호출 되면 MemberSaveControllerV1()이 반환 된다.
        * */
        if (controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);// 없으면 404 반환
            return;
        }
        //잘될경우 process 호출하면 된다.
        controller.process(request,response);

    }
}
