package hello.core.singleton;

import org.springframework.context.annotation.Bean;


public class StatefulService {
    private  int price;

    public int order(String name, int price){
        System.out.println("name = " + name +"price = " + price);
  // this.price = price; // 여기가 문제!
        /*
        *
        *
        * */
        return price;
    }


//    public int getPrice(){
//        return price;
//    }


}
