package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="helloServlet",urlPatterns = "/hello")// /hello오면 이게 실행이 되는 것이다.
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

       String username=  request.getParameter("username");
        System.out.println("username = " + username);

        // reponse에 값을 넣으면 응답 메세지가 데이터가 담겨져서 나간다.
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");//인코딩 정보
        // 이거 2개는 Header에 들어간다.
        response.getWriter().write("hello " + username);//write하면 http 메세지 body에 이 메세지가 들어간다.
    }//이렇게 하면 이 서블릿이 호출이 되어서 service 메서드가 호출된다.
}
