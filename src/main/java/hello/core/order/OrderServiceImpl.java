package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;

    /**
     * private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
     * 위 코드는 추상 인터페이스 뿐만 아니라 구현 클래스에도 의존하므로 DIP를 위반한다.
     * 이 문제를 해결하기 위해선?
     * OrderServiceImpl 클래스에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해야 한다.
     */

    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
