package com.tomspencerlondon;

import java.util.ArrayList;
import java.util.List;

public class SnackMachine extends Entity {
  Money moneyInside;
  Money moneyInTransaction;

  public SnackMachine() {
    moneyInside = Money.ZERO;
    moneyInTransaction = Money.ZERO;
  }

  public void insertMoney(Money money) {
    List<Money> accepted = List.of(Money.ONE_CENT, Money.TEN_CENT, Money.QUARTER_CENT, Money.ONE_DOLLAR, Money.ONE_DOLLAR, Money.FIVE_DOLLAR, Money.TWENTY_DOLLAR);
    if (!accepted.contains(money)) {
      throw new IllegalArgumentException();
    }
    moneyInTransaction = Money.plus(moneyInTransaction, money);
  }

  public void returnMoney() {
    moneyInTransaction = Money.ZERO;
  }

  public void buySnack() {
    moneyInside = Money.plus(moneyInside, moneyInTransaction);
    // MoneyInTransaction = 0;
  }

  public Money moneyInTransaction() {
    return moneyInTransaction;
  }
}
