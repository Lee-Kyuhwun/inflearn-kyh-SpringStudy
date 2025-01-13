package hello.advanced.trace.callback;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

import static com.sun.jndi.ldap.LdapPoolManager.trace;

public class TraceTemplate {

    private final LogTrace logTrace;

    public TraceTemplate(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    public <T> T execute(String message, TraceCallBack<T> callback) {
        TraceStatus status = null;
        try {
            status = logTrace.begin(message);
            //로직 호출
            T result = callback.call();
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }

    }
}
