package hello.itemservice.validation;


import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

public class MessageCodesResolverTest {

    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();


    @Test
    void messageCodeResolverObject(){
        String[] messageCodes = codesResolver.resolveMessageCodes("required","item");
        for(String messageCode : messageCodes){
            System.out.println("messageCode = " + messageCode);
        }
    }

    @Test
    void messageCodeResolverField(){
        String[] messageCodes = codesResolver.resolveMessageCodes("required","item","itemName",String.class);
        for(String messageCode : messageCodes){
            System.out.println("messageCode = " + messageCode);
        }
    }
}
