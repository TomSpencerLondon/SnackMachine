package com.tomspencerlondon;

import static com.tomspencerlondon.Money.plus;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class MoneyTest {

  @Test
  void sum_of_two_moneys_produces_correct_result() {
    Money money1 = new Money(1, 2, 3, 4, 5, 6, 7);
    Money money2 = new Money(1, 2, 3, 4, 5, 6, 7);

    Money result = plus(money1, money2);

    assertThat(result)
        .isEqualTo(new Money(2, 4, 6, 8, 10, 12, 14));
  }

  @Test
  void two_money_instances_do_not_equal_if_contain_different_money_amounts() {
    Money dollar = new Money(0, 0, 0, 1, 0, 0, 0);
    Money hundredCents = new Money(100, 0, 0, 0, 0, 0, 0);

    assertThat(dollar)
        .isNotEqualTo(hundredCents);
    assertThat(dollar.hashCode())
        .isNotEqualTo(hundredCents.hashCode());
  }
}
