package hello.core.member;

public class MemberServiceImpl implements MemberService{
//    변경 전
//    ** DIP위반 : MemoryMemberRepository() 구현 클래스에도 의존하고 있다.
//    ** OCP위반 : 지금 코드는 기능을 확장해서 변경하면, 클라이언트 코드에도 영향을 준다.
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

//    변경 후
//    ** DIP : MemberRepository 인터페이스에만 의존한다.
//    MemberServiceImpl는 어떤 구현 객체가 들어올지 알 수 없다.
//      -> 생성자를 통ㅇ해서 어떤 구현 객체가 들어올지는 오직 위부(AppConfig)에서 결정된다.
//      -> 의존관계에 대한 고민은 외부에 맡기고 실행헤만 집중하면 된다.

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
