package hello.itemservice.message;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class messageSourceTest {

    @Autowired
    MessageSource messageSource;


    @Test
    void helloMessage() {
        String result = messageSource.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");

    }

    @Test
    void NotFoundMessageCode() {
        assertThatThrownBy(()-> messageSource.getMessage("no_code",null,null))
                .isInstanceOf(NoSuchMessageException.class);

    }

    @Test
    void NotFoundDefaultMessageCode() {
        String result = messageSource.getMessage("no_code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");

    }//메세지 못찾으면 디폴트 줌


    @Test
    void argumentMessage() {
        String result = messageSource.getMessage("hello.name",new Object[]{"Spring"},null);
        assertThat(result).isEqualTo("안녕 Spring");

    }
}
