package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    //인터페이스에만 의존 (DIP 원칙 준수)
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //동일 타입의 스프링 빈이 두 개 이상이라서 중첩이 되는 경우
    // 1. 매개변수 명을 주입하고자 하는 스프링 빈 명으로 지정
    // 2. @Qualifier로 각 클래스 별로 별명(?) 지정 후 주입시에 @Qualifier로 주입할 빈 지정
    // 3. @primary 클래스에 붙이면 그 클래스 빈이 가장 높은 우선순위를 가짐

    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
    // 테스트 용도
    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }


    /**
     @Autowired  // 수정자 주입
     public void setMemberRepository(MemberRepository memberRepository) {
     System.out.println("memberRepository = " + memberRepository);
     this.memberRepository = memberRepository;
     }

     @Autowired  // 수정자 주입
     public void setDiscountPolicy(DiscountPolicy discountPolicy) {
     System.out.println("discountPolicy = " + discountPolicy);
     this.discountPolicy = discountPolicy;
     }
     */
}
