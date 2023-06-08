package hello.advanced.app.v2;


import hello.advanced.app.v1.OrderServiceV1;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {


    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderControllerV1.orderItem");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 반드시 예외를 던져야 한다.
        }

    }


}
