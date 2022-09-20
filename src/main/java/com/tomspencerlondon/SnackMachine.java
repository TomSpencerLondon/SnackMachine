package com.tomspencerlondon;

public class SnackMachine extends Entity {
  Money moneyInside;
  Money moneyInTransaction;

  public SnackMachine() {
    moneyInside = Money.ZERO;
    moneyInTransaction = Money.ZERO;
  }

  public void insertMoney(Money money) {
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
