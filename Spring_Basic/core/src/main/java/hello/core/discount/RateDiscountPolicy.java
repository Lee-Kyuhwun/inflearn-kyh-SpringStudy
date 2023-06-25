package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy{
    private  int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGarde() == Grade.VIP){
            return price* discountPercent / 100;
            //VIP이면 1000원 아니면 없다.
    }else {
            return 0;
        }
    }
}

