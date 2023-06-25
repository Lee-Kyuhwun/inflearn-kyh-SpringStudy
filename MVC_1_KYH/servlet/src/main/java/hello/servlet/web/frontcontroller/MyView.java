package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;

    public MyView(String viewPath){
        this.viewPath = viewPath;
    }


    // 기본에 있던 Myview로직에 넣어서 렌더링하도록 포워딩한다. 뷰를 만드는 행위자체를
    public void render(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        * 기존에 있던 값을 jsp에
        * */
        modelToReqeustAttribute(model, request);
        // request의 값들ㅇ르 key, value에 담아둔다.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }

    private static void modelToReqeustAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value)-> request.setAttribute(key,value));// 모델에 있는 값들을 모두 꺼낸다.
    }
    /*
     * RequestDispatcher란 RequestDispatcher는 클라이언트로부터 최초에 들어온 요청을 JSP/Servlet 내에서 원하는 자원으로 요청을 넘기는(보내는) 역할을 수행하거나,
     * 특정 자원에 처리를 요청하고 처리 결과를 얻어오는 기능을 수행하는 클래스입니다. 즉
     * /a.jsp 로 들어온 요청을 /a.jsp 내에서 RequestDispatcher를 사용하여
     *  b.jsp로 요청을 보낼 수 있습니다. 또는 a.jsp에서 b.jsp로 처리를 요청하고 b.jsp에서 처리한 결과 내용을 a.jsp의 결과에 포함시킬 수 있습니다.
     * */


}
