package com.tomspencerlondon.hexagon.application;

import com.tomspencerlondon.hexagon.domain.Money;
import com.tomspencerlondon.hexagon.domain.SnackMachine;

public class SnackService {

  private final SnackMachine snackMachine;

  public SnackService(SnackMachine snackMachine) {
    this.snackMachine = snackMachine;
  }

  public Money moneyInside() {
    return snackMachine.moneyInside();
  }

  public Money moneyInserted() {
    return snackMachine.moneyInTransaction();
  }

  public void insert(String amount) {
    snackMachine.insertMoney(money(amount));
  }

  private Money money(String coin) {
    switch(coin) {
      case "1_C": return Money.ONE_CENT;
      case "5_C": return Money.FIVE_CENT;
      case "10_C": return Money.TEN_CENT;
      case "25_C": return Money.QUARTER_CENT;
      case "1_D": return Money.ONE_DOLLAR;
      case "5_D": return Money.FIVE_DOLLAR;
      case "20_D": return Money.TWENTY_DOLLAR;
      default: return Money.ZERO;
    }
  }

  public void returnMoney() {
    snackMachine.returnMoney();
  }

  public void buySnack() {
    snackMachine.buySnack();
  }
}
