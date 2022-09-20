package com.tomspencerlondon;

public class SnackMachine extends Entity {
  Money moneyInside;
  Money moneyInTransaction;

  public void insertMoney(Money money) {
    moneyInTransaction = Money.plus(moneyInTransaction, money);
  }

  public void returnMoney() {
    // MoneyInTransaction = 0;
  }

  public void buySnack() {
    moneyInside = Money.plus(moneyInside, moneyInTransaction);
    // MoneyInTransaction = 0;
  }
}
