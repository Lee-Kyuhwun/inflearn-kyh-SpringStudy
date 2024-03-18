package hello.advanced.app.v0;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV0 {
    private final OrderRepositoryV0 orderRepository;


    public void orderItem(String itemId){
        System.out.println("주문 시작 = " + itemId);
        orderRepository.save(itemId);
        System.out.println("주문 완료 = " + itemId);
    }

}
