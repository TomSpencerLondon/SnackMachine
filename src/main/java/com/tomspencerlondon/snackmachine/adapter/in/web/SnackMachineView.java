package com.tomspencerlondon.snackmachine.adapter.in.web;

import com.tomspencerlondon.snackmachine.hexagon.domain.Money;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachine;
import java.text.NumberFormat;
import java.util.Locale;

public class SnackMachineView {

  private final String moneyInserted;
  private final String moneyInside;

  private SnackMachineView(Money moneyInserted, Money moneyInside) {
    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
    this.moneyInserted = formatter.format(moneyInserted.amount());
    this.moneyInside = formatter.format(moneyInside.amount());
  }

  public static SnackMachineView from(SnackMachine snackMachine) {
    return new SnackMachineView(snackMachine.moneyInTransaction(), Money.plus(snackMachine.moneyInside(), snackMachine.moneyInTransaction()));
  }

  public String getMoneyInserted() {
    return moneyInserted;
  }

  public String getMoneyInside() {
    return moneyInside;
  }
}
