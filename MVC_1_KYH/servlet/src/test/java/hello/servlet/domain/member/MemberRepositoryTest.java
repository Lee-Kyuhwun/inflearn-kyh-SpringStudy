package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {
// 싱글톤이라 new로 하면 안된다. 스프링을 쓰면 사실 걱정할 필요가 없긴하다. 왜냐하면 스프링에서 알아서 싱글톤을 해주기때문이다.
//    MemberRepository memberRepository = new MemberRepository();
MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Member member = new Member("hello",20);

        //when
        Member savedMember= memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(savedMember.getId());

        assertThat(findMember).isEqualTo(savedMember);//찾아온 멤버와 저장된 멤버가 같은지 확인한다.
    }
    //모든거 조회하는거 테스트
    @Test
    void findAll(){
        //given
        Member member1 = new Member("member1",20);
        Member member2 = new Member("member2",30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
       assertThat(result.size()).isEqualTo(2);
       assertThat(result).contains(member1,member2);//result안에 member1,2 있는지 확인한다.
    }
}
