package com.tomspencerlondon.snackmachine.adapter.in.web;

import com.tomspencerlondon.snackmachine.hexagon.domain.Money;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachine;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class SnackMachineView {

  private final String moneyInserted;
  private final String moneyInside;
  private final String id;
  private final List<SlotView> slotViews;

  private SnackMachineView(String id, List<SlotView> slotViews, Money moneyInserted, Money moneyInside) {
    this.id = id;
    this.slotViews = slotViews;
    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
    this.moneyInserted = formatter.format(moneyInserted.amount());
    this.moneyInside = formatter.format(moneyInside.amount());
  }

  public static SnackMachineView from(SnackMachine snackMachine) {
    List<SlotView> slotViews = snackMachine.slots().stream().map(SlotView::from).toList();
    return new SnackMachineView(
        snackMachine.id().toString(),
        slotViews,
        snackMachine.moneyInTransaction(),
        Money.plus(snackMachine.moneyInside(), snackMachine.moneyInTransaction()));
  }

  public String getMoneyInserted() {
    return moneyInserted;
  }

  public String getMoneyInside() {
    return moneyInside;
  }

  public String getId() {
    return id;
  }

  public List<SlotView> getSlotViews() {
    return slotViews;
  }
}
