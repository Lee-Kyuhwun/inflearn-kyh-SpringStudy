package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository ;


    //의존관계주입을 위해 Autowired가 필요함
    @Autowired //ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }//생성자를 통해서 memoryRepository에 뭐가 들어갈지를 결정한다.
    // this.memberRepository에 할당된다.
    // 여기서 MemberServiceImpl에는 MemoryMemberRepository와 관련된 것이 없다
    //오직 MemberRepository인터페이스만 있다.
    //이렇게 되면 추상화를 지키게 된다.
    //즉 생성자를 통해서 생성자 주입이 된다.
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }


}
