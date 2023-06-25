 package hello.core;
/**
 * [치타(aws,코프링,kafka)] [오후 1:40] 뭐 사람마다 다르긴한데
 * [치타(aws,코프링,kafka)] [오후 1:40] 영한님의 기초 강의 듣고 저런 평이 많기는 하더라구용
 * [치타(aws,코프링,kafka)] [오후 1:41] 그런데 MVC 패턴에 의거해서 코드 짜는거는 진짜 금방 배울수는 있어서 큰 고민은 안하셔도 되구
 * [치타(aws,코프링,kafka)] [오후 1:41] 진짜 중요한건 spring core가 어떻게 구성되어있는가를 이해하는게 제일 중요해용
 * [치타(aws,코프링,kafka)] [오후 1:44] 1. JSP의 서블릿 컨테이너 패턴과 Spring의 Front controller 패턴은 무슨 차이가 있을까
 * 2. RestController 어노테이션이 달린 클래스 내부의 메소드는 스프링 코어에서 어떻게 동작할까
 * 3. Spring의 IoC/DI의 동작 원리
 * 4. Spring이 지향하는 POJO의 진정한 의미
 * 5. Spring의 AOP는 어떤 원리로 동작하는가? (Feat. Spring Reflection vs Java Reflection, and Factory Bean, Dynamic Proxy)
 * [치타(aws,코프링,kafka)] [오후 1:46] 이것만 잘 이해하면 코딩은 따라와요
 * [코린이] [오후 1:46] 이건 어떻게 학습하면 될까요?
 * [치타(aws,코프링,kafka)] [오후 1:46] 어차피 개발이란건 코딩은 15-20%고 그 외에는 개발지식과 cs입니당
 * [치타(aws,코프링,kafka)] [오후 1:46] 영한님 강의를 그대로 쭉 들으셔도 되고
 * [치타(aws,코프링,kafka)] [오후 1:47] 기초적인 디자인 패턴 학습 후 토비의 스프링 정독
 * [치타(aws,코프링,kafka)] [오후 1:47] 하셔도 됩니다
 * [치타(aws,코프링,kafka)] [오후 1:47] 저는 이렇게 공부했었어요
 * [치타(aws,코프링,kafka)] [오후 1:47] (영한님 강의를 모두 사기엔 돈이 없어서..ㅎㅎ)
 *
 * **/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
