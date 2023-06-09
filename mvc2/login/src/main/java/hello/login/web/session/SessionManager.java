package hello.login.web.session;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "my_session_id";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

        /*
        * 세선 완성
        * 세선 저장소에 sessionId와 보관할 값 저장
        * sessionId로 응답 쿠키를 생성해서 클라이언트에 전달
        *
        * */
        public  void createSession(Object value, HttpServletResponse response){
            // 세선 id를 생성하고, 값을 세션에 저장
            String sessionId = UUID.randomUUID().toString();
            sessionStore.put(sessionId, value);
            //쿠키 생성
            Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
            response.addCookie(mySessionCookie);

        }

        /*
        * 세선 조회
        * */

        public Object getSession(HttpServletRequest request){
            Cookie sessionCookie = findCookie(request,SESSION_COOKIE_NAME);
            if(sessionCookie ==null){
                return null;
            }
            return sessionStore.get(sessionCookie.getValue());


           /* Cookie[] cookies = request.getCookies();
            if(cookies ==null){
                return null;
            }
            for(Cookie cookie: cookies){
                if (cookie.getName().equals(SESSION_COOKIE_NAME)){
                    return sessionStore.get(cookie.getValue());
                }
            }
            return null;
*/
        }
        public Cookie findCookie(HttpServletRequest request, String cookieName){
            Cookie[] cookies = request.getCookies();
            if(cookies ==null){
                return null;
            }
            return Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals(cookieName))
                    .findAny()
                    .orElse(null);
        }

        /*
        * 세션 만료
        * */
        public void expire(HttpServletRequest request){
            Cookie sessionCookie = findCookie(request,SESSION_COOKIE_NAME);
            if(sessionCookie!=null){
                sessionStore.remove(sessionCookie.getValue());
            }
        }

}
