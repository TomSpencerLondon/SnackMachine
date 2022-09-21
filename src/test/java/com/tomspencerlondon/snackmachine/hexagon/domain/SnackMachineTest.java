package com.tomspencerlondon.snackmachine.hexagon.domain;

import static com.tomspencerlondon.snackmachine.hexagon.domain.Money.multiply;
import static com.tomspencerlondon.snackmachine.hexagon.domain.Money.plus;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class SnackMachineTest {

  @Test
  void return_money_empties_money_in_transaction() {
    SnackMachine snackMachine = new SnackMachine();
    snackMachine.insertMoney(new Money(0, 0, 0, 1, 0, 0, 0));

    snackMachine.returnMoney();

    assertThat(snackMachine.moneyInTransaction())
        .isEqualTo(Money.ZERO);
  }

  @Test
  void inserted_money_goes_to_money_in_transaction() {
    SnackMachine snackMachine = new SnackMachine();

    snackMachine.insertMoney(Money.ONE_CENT);
    snackMachine.insertMoney(Money.ONE_DOLLAR);

    assertThat(snackMachine.moneyInTransaction())
        .isEqualTo(new Money(1, 0, 0, 0, 1, 0, 0));
  }

  @Test
  void cannot_insert_more_than_one_coin_or_note_at_a_time() {
    SnackMachine snackMachine = new SnackMachine();
    Money twoCent = plus(Money.ONE_CENT, Money.ONE_CENT);

    assertThatThrownBy(() -> snackMachine.insertMoney(twoCent))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void money_in_transaction_goes_to_money_inside_after_purchase() {
    SnackMachine snackMachine = new SnackMachine();
    snackMachine.insertMoney(Money.ONE_DOLLAR);
    snackMachine.insertMoney(Money.ONE_DOLLAR);

    snackMachine.buySnack();

    assertThat(snackMachine.moneyInTransaction())
        .isEqualTo(Money.ZERO);

    assertThat(snackMachine.moneyInside())
        .isEqualTo(multiply(Money.ONE_DOLLAR, 2));
  }
}