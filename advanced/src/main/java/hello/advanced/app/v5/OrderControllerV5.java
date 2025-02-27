package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceCallBack;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class OrderControllerV5 {
    private final OrderServiceV5 orderService;
    private final TraceTemplate template;
    public OrderControllerV5(OrderServiceV5 orderService, @Qualifier("logTrace") LogTrace trace) {
        this.orderService = orderService;
        this.template = new TraceTemplate(trace);
    }
    @GetMapping("/v5/request")
    public String request(String itemId) {
        return template.execute("OrderController.request()", new
                TraceCallBack<>() {
                    @Override
                    public String call() {
                        orderService.orderItem(itemId);
                        return "ok";
                    }
                });
    }
}