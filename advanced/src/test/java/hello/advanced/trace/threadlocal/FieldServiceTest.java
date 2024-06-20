package hello.advanced.trace.threadlocal;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {


    private FieldService fieldService = new FieldService();


    @Test
    void field(){
        log.info("fieldService = {}", fieldService);
        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("userA");
        Thread threadB = new Thread(userB);
        threadB.setName("userB");

        threadA.start();
        sleep(100);
        threadB.start();


    }

    private void sleep(int mills){
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
