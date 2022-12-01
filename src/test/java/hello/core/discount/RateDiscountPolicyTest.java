package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    @Test
    void vip_discount() {
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        int discount = discountPolicy.discount(member, 20000);
        assertThat(discount).isEqualTo(2000);
    }

    @Test
    @DisplayName("BASIC 유저는 할인이 적용되면 안된다.")
    void basic_discount() {
        Member member = new Member(2L, "memberBasic", Grade.BASIC);

        int discount = discountPolicy.discount(member, 20000);
        assertThat(discount).isEqualTo(0);
    }
}