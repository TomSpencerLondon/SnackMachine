package com.tomspencerlondon.snackmachine.hexagon.domain;

import java.util.List;

public class SnackMachine extends Entity {

  private final List<Money> accepted = List.of(
      Money.ONE_CENT, Money.TEN_CENT,
      Money.QUARTER_CENT, Money.ONE_DOLLAR,
      Money.FIVE_DOLLAR,
      Money.TWENTY_DOLLAR);
  private Money moneyInside;
  private Money moneyInTransaction;

  public SnackMachine() {
    moneyInside = Money.ZERO;
    moneyInTransaction = Money.ZERO;
  }

  public void insertMoney(Money money) {
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
    moneyInTransaction = Money.ZERO;
  }

  public Money moneyInTransaction() {
    return moneyInTransaction;
  }

  public Money moneyInside() {
    return moneyInside;
  }
}
