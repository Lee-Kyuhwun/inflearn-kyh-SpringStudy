package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "respontHtmlServlet",urlPatterns = "/response-html")
public class ResponstHtmlServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-type: text/html;charset=utf-8

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");


        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");

        writer.println("<div> 러디아트 마렵다.</div>");

        writer.println("</body>");
        writer.println("</html>");

    }
}
