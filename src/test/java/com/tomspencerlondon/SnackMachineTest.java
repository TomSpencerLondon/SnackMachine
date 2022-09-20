package com.tomspencerlondon;

import static org.assertj.core.api.Assertions.assertThat;

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
}
