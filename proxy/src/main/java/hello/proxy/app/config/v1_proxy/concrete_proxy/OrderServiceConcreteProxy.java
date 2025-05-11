package hello.proxy.app.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV2 {

    private final OrderServiceV2 target;

    private final LogTrace logTrace;

    public OrderServiceConcreteProxy( OrderServiceV2 orderServiceV2, LogTrace logTrace) {
        super(null);// 위의 클래스 안쓸거라 null로
        this.target = orderServiceV2;
        this.logTrace = logTrace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderSErvice.orderItem()");
            target.orderItem(itemId);
            logTrace.end(status);
        }catch (Exception e) {
            logTrace.exception(status, e);
        }    }
}
