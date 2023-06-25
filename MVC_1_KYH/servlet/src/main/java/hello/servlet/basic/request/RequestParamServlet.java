package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/***
 * 1. 파라미터 전송기능
 * httpL//locathost:8080/request-param?username=hello&age=20
 *
 */
@WebServlet(name = "requestParamServlet",urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RequestParamServlet.service");

        System.out.println("[전체 파라미터 조회] - start");

        request.getParameterNames().asIterator()
                        .forEachRemaining(paraName -> System.out.println("request = " + request.getParameter(paraName)));
        //paraname은 이름을 꺼내는 것이고 키는 request.getParameter(paraName)이렇게 꺼낸다. username -> paraname , hello ->request.getParameter(paraName) 으로 꺼내는 식이다.

        System.out.println("[단일 파라미터 조회] - start");
       String username = request.getParameter("username");
        System.out.println("username = " + username);
       String age=   request.getParameter("age");
        System.out.println("age = " + age);
        System.out.println();

        System.out.println("이름이 같은 복수 파리미터 조회");

        String[] usernames = request.getParameterValues("username");
        for(String name : usernames){
            System.out.println("username = " + name);
        }

        response.getWriter().write("OK");
    }
}
