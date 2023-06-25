package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{



    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    public void setMemberRepository(MemberRepository)

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    @Override
     public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice= discountPolicy.discount(member, itemPrice);
        //orderSErvive입장에서는 discount결과만 받음으로써 discount를 고칠필요가 사라짐
        //멤버만 넘길기 grade를 넘길지는 상황에따라 결정하면 된다.

        return new Order(memberId,itemName,itemPrice,discountPrice);

    }
}
