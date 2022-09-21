package com.tomspencerlondon.snackmachine.hexagon.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.tomspencerlondon.snackmachine.hexagon.domain.Money;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachine;
import org.junit.jupiter.api.Test;

public class SnackServiceTest {

  @Test
  void givenInsert5DollarsMoneyInserted() {
    SnackService snackService = new SnackService(new SnackMachine());

    snackService.insert("5_D");

    assertThat(snackService.moneyInserted())
        .isEqualTo(Money.FIVE_DOLLAR);
  }

  @Test
  void givenInsertAndBuyMoneyInside() {
    SnackService snackService = new SnackService(new SnackMachine());

    snackService.insert("5_D");
    snackService.buySnack();

    assertThat(snackService.moneyInserted())
        .isEqualTo(Money.ZERO);
    assertThat(snackService.moneyInside())
        .isEqualTo(Money.FIVE_DOLLAR);
  }
}
