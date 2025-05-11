package hello.proxy.code;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheProxy implements Subject {

    private Subject target; // 실제 객체
    private String cacheValue; // 캐시 데이터

    public CacheProxy(Subject target) {
        this.target = target;
    }

    @Override
    public String operation(String param) {
        return "";
    }

    @Override
    public String operation() {

        log.info("프록시 호출");

        if (cacheValue == null) {
            log.info("캐시 데이터 없음, 타겟 호출");
            cacheValue = target.operation();
            log.info("캐시 데이터 저장 {}", cacheValue);
        } else {
            log.info("캐시 데이터 반환 {}", cacheValue);
        }
        return cacheValue;
    }
}
