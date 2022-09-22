package com.tomspencerlondon.snackmachine.hexagon.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class SnackMachine extends AggregateRoot {

  private final List<Money> accepted = List.of(Money.ONE_CENT, Money.TEN_CENT, Money.QUARTER_CENT, Money.ONE_DOLLAR, Money.FIVE_DOLLAR, Money.TWENTY_DOLLAR);

  private List<Slot> slots;

  private Money moneyInside;
  private Money moneyInTransaction;

  public SnackMachine() {
    moneyInside = Money.ZERO;
    moneyInTransaction = Money.ZERO;
    slots = List.of(new Slot(new SnackPile(new Snack("snack"), 1, 0.0), this, 1), new Slot(new SnackPile(new Snack("snack"), 1, 0.0), this, 2), new Slot(new SnackPile(new Snack("snack"), 1, 0.0), this, 3));
  }

  public Optional<SnackPile> snackPile(int position) {
    return getSlot(position)
        .map(Slot::snackPile).findFirst();
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

  public void buySnack(int position) {
    getSlot(position)
        .forEach(Slot::reduceQuantity);
    moneyInside = Money.plus(moneyInside, moneyInTransaction);
    moneyInTransaction = Money.ZERO;
  }

  private Stream<Slot> getSlot(int position) {
    return slots.stream().filter(x -> x.position() == position);
  }

  public Money moneyInTransaction() {
    return moneyInTransaction;
  }

  public Money moneyInside() {
    return moneyInside;
  }

  public void loadSnacks(int position, Snack snack, int quantity, double price) {
    slots = getSlot(position)
        .map(s -> new Slot(
            new SnackPile(snack, quantity, price),
            this, position))
        .toList();
  }

  public List<Slot> slots() {
    return slots;
  }
}
