package com.tomspencerlondon.adapter.in;

import com.tomspencerlondon.hexagon.domain.Money;
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

  public static SnackMachineView from(Money moneyInserted, Money moneyInside) {
    return new SnackMachineView(moneyInserted, moneyInside);
  }

  public String getMoneyInserted() {
    return moneyInserted;
  }

  public String getMoneyInside() {
    return moneyInside;
  }
}
