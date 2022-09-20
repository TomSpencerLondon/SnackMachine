package com.tomspencerlondon;

import static com.tomspencerlondon.Money.plus;
import static com.tomspencerlondon.Money.subtract;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

  @Test
  void subtraction_of_two_moneys_produces_correct_result() {
    Money money1 = new Money(10, 10, 10, 10, 10, 10, 10);
    Money money2 = new Money(1, 2, 3, 4, 5, 6, 7);

    Money result = subtract(money1, money2);

    assertThat(result)
        .isEqualTo(new Money(9, 8, 7, 6, 5, 4, 3));
  }

  @ParameterizedTest
  @MethodSource("numbers")
  void cannot_create_money_with_negative_value(int[] input) {
    assertThatThrownBy(() -> {
      new Money(input[0],input[1], input[2], input[3], input[4], input[5], input[6]);
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @ParameterizedTest
  @MethodSource("decimalNumbers")
  void amountIsCreatedCorrectly(int[] input, double expected) {
    Money money = new Money(input[0], input[1], input[2], input[3], input[4], input[5], input[6]);

    assertThat(money.amount())
        .isEqualTo(expected);
  }

  public static Stream<Arguments> decimalNumbers() {
    return Stream.of(Arguments.of(new int[]{0, 0, 0, 0, 0, 0, 0}, 0.0),
        Arguments.of(new int[]{1, 0, 0, 0, 0, 0, 0}, 0.01),
        Arguments.of(new int[]{1, 2, 0, 0, 0, 0, 0}, 0.11),
        Arguments.of(new int[]{1, 2, 3, 0, 0, 0, 0}, 0.41),
        Arguments.of(new int[]{1, 2, 3, 4, 0, 0, 0}, 1.41),
        Arguments.of(new int[]{1, 2, 3, 4, 5, 0, 0}, 6.41),
        Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 0}, 36.41),
        Arguments.of(new int[]{11, 0, 0, 0, 0, 0, 0}, 0.11),
        Arguments.of(new int[]{110, 0, 0, 0, 100, 0, 0}, 101.10));
  }

  public static Stream<Arguments> numbers() {
    return Stream.of(
        Arguments.of(new int[]{-1, 0, 0, 0, 0, 0, 0}),
        Arguments.of(new int[]{0, -2, 0, 0, 0, 0, 0}),
        Arguments.of(new int[]{0, 0, -3, 0, 0, 0, 0}),
        Arguments.of(new int[]{0, 0, 0, -4, 0, 0, 0}),
        Arguments.of(new int[]{0, 0, 0, 0, -5, 0, 0}),
        Arguments.of(new int[]{0, 0, 0, 0, 0, -6, 0}),
        Arguments.of(new int[]{0, 0, 0, 0, 0, 0, -7})
    );
  }
}
