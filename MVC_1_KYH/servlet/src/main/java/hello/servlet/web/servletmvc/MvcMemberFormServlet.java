package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {



    //1. 먼저 컨트롤러걸쳐서 view로 들어가야해서 컨트롤러 거쳐야하나다. 그래서 jsp로 가게만드면 된다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";// WEB-INF에 들어간 파일들은 그냥 불러도 호출되지 않는다. 항상 컨트롤러를 거쳐서 가야지 호출된다.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);// 이걸로 서블릿에서 jsp를 호출할 수 있다.
    }
}
