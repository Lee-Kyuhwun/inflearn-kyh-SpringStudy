package hello.itemservice.validation;

import hello.itemservice.domain.item.Item;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

public class BeanValidationTest {

    @Test
    void beanValidatoin(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Item item = new Item();
        item.setItemName(" ");
        item.setPrice(0);
        item.setQuantity(10000);

        Set<ConstraintViolation<Item>> violatoins =  validator.validate(item);
        for(ConstraintViolation<Item> violation: violatoins){
            System.out.println("violation = " + violation);
            System.out.println("violation.getMessage() = " + violation.getMessage());

        }

    }
}
