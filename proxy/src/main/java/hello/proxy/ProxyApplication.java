package hello.proxy;

import hello.proxy.app.config.AppV1Config;
import hello.proxy.app.config.AppV2Config;
import hello.proxy.app.config.v1_proxy.InterfaceProxyConfig;
import hello.proxy.app.config.v1_proxy.concrete_proxy.ConcreteProxyConfig;
import hello.proxy.app.config.v1_proxy.v2_proxy.handler.DynamicProxyBasicConfig;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;


//@Import({InterfaceProxyConfig.class})
//@Import({ConcreteProxyConfig.class, AppV2Config.class}) //주의
//@SpringBootApplication(scanBasePackages = "hello.proxy.app.v2") //주의
@Import(DynamicProxyBasicConfig.class)
@SpringBootApplication(scanBasePackages = "hello.proxy.app")
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}


	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}

}
