package hello.typeconverter.converter;


import hello.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;

public class ConversionServiceTest {


    @Test
    void conversionService(){
        //등록
        DefaultConversionService conversionService = new DefaultConversionService();

        conversionService.addConverter(new StringToIntergerConverter());
        conversionService.addConverter(new IpPortToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        
        
        //사용
        assertThat( conversionService.convert("10", Integer.class )).isEqualTo(10);
        assertThat( conversionService.convert(10, String.class )).isEqualTo("10");
        assertThat(  conversionService.convert("127.0.0.1:8080", IpPort.class)).isEqualTo("127.0.0.1");
    }

}
