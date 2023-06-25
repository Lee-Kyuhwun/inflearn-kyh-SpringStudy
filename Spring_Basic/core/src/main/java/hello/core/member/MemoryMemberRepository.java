package hello.core.member;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long,Member> store = new HashMap<>();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }//저장하고 넣어줌 put 단어 그자체로 생각

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
