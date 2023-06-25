package hello.core.member;


import org.springframework.stereotype.Component;

@Component
public interface MemberRepository { //저장소라는 뜻
    void save(Member member);

    Member findById (Long memberId);


}
