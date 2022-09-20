package com.tomspencerlondon;

import static com.tomspencerlondon.Money.plus;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class MoneyTest {

  @Test
  void sum_of_tow_moneys_produces_correct_result() {
    Money money1 = new Money(1, 2, 3, 4, 5, 6, 7);
    Money money2 = new Money(1, 2, 3, 4, 5, 6, 7);

    Money result = plus(money1, money2);

    assertThat(result)
        .isEqualTo(new Money(2, 4, 6, 8, 10, 12, 14));
  }
}
