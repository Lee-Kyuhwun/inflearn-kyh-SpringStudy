package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();// 아이디가 하나씩 증가하는 시퀀스로 쓰인다.
    //static이라서 딱 하나만 생성된다.
    private static long sequence = 0L;//

    //싱글톤으로 만들 예정
    private static final MemberRepository instance = new MemberRepository();
    //싱글톤 만들때는 private로 생성자를 막아야한다.
    private MemberRepository(){}

    public static MemberRepository getInstance(){
        return instance;
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }//store에 있는 모든 값을 꺼내서 새로운 arraylist로 반환한다.


    public void clearStore(){
        store.clear();
        // store 다 날려버리는 함수
    }
}
// 회원 저장소