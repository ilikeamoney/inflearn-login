package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // static 사용
    private static long sequence = 0L;

    // 저장
    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save : member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    // 로그인 아이디로 회원 찾기
    public Optional<Member> findByLoginId(String loginId) {
//        List<Member> members = findAll();
//        for (Member member : members) {
//            if (member.getLoginId().equals(loginId)) {
//                return Optional.of(member);
//            }
//        }
//        return Optional.empty();

        return findAll().stream() // 배열을 루프를 돌면서
                .filter(member -> member.getLoginId().equals(loginId)) // 필터
                .findFirst(); // 필터로 찾은 첮번째를 반환
    }

    // ID로 찾기
    public Member findById(Long id) {
        return store.get(id);
    }

    // 모든 멤버 찾기
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }




}
