//package hello.core.autowired;
//
//import hello.core.AutoAppConfig;
//import hello.core.discount.DiscountPolicy;
//import org.junit.jupiter.api.Test;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import java.util.List;
//import java.util.Map;
//
//public class AllBeanTest {
//
//
//    @Test
//    void findAllBean(){
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
//    }
//
//    static class DiscountService{
//        private final Map<String, DiscountPolicy> policyMap;
//        private final List<DiscountPolicy> policies;
//
//        public DiscountPolicy(Map<String ,DiscountPolicy> policyMap){
//
//        }
//    }
//}
