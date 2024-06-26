package hello.advanced.trace.threadlocal;


import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ThreadLocalService {

    private ThreadLocal<String> nameStore;

    public  String logic(String name) {
        log.info("저장 name={} -> nameStore= {}", name,nameStore);
        nameStore.set(name);
        sleep(1000);
        log.info("조회 nameStore={}", nameStore);
        return nameStore.get();
    }

    private void sleep(int mills){
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
