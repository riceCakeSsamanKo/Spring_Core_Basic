package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
//@Qualifier("mainDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercentage = 10;

    @Override
    public int discount(Member member, int itemPrice) {
        if (member.getGrade() == Grade.VIP)
            return itemPrice * discountPercentage / 100;
        else return 0;
    }
}


