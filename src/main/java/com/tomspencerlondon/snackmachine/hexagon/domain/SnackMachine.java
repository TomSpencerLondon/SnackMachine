package com.tomspencerlondon.snackmachine.hexagon.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SnackMachine extends AggregateRoot {
  private SnackMachineId snackMachineId;
  private final List<Money> accepted = List.of(Money.ONE_CENT, Money.TEN_CENT, Money.QUARTER_CENT, Money.ONE_DOLLAR, Money.FIVE_DOLLAR, Money.TWENTY_DOLLAR);
  private List<Slot> slots;
  private Money moneyInside;
  private Money moneyInTransaction;

  public SnackMachine() {
    moneyInside = Money.ZERO;
    moneyInTransaction = Money.ZERO;
    slots = List.of(new Slot(new SnackPile(new Snack("snack"), 10, 0.0), this, 1), new Slot(new SnackPile(new Snack("snack"), 10, 0.0), this, 2), new Slot(new SnackPile(new Snack("snack"), 10, 0.0), this, 3));
  }

  public Optional<SnackPile> snackPile(int position) {
    return getSlot(position)
        .map(Slot::snackPile);
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
    Optional<Slot> slot = getSlot(position);

    if (slot.isEmpty()) {
      throw new IllegalArgumentException();
    }

    slot.ifPresent(s -> {
      if (s.snackPile().price() > moneyInTransaction.amount()) {
        throw new IllegalArgumentException();
      }
    });

    slot.map(this::reduceSnackQuantity);

    moneyInside = Money.plus(moneyInside, moneyInTransaction);
    moneyInTransaction = Money.ZERO;
  }

  private Optional<Slot> getSlot(int position) {
    return slots.stream().filter(x -> x.position() == position).findFirst();
  }

  public Money moneyInTransaction() {
    return moneyInTransaction;
  }

  public Money moneyInside() {
    return moneyInside;
  }

  public void loadSnacks(int position, Snack snack, int quantity, double price) {
    Optional<SnackPile> snackPile = getSlot(position)
        .map(s -> new SnackPile(snack, quantity, price));

    slots = slots.stream().map(s -> {
      if (s.position() == position && snackPile.isPresent()) {
        return new Slot(snackPile.get(), s.snackMachine(), s.position());
      } else {
        return s;
      }
    }).collect(Collectors.toList());

  }

  private Slot reduceSnackQuantity(Slot s) {
    if (s.snackPile().price() > moneyInTransaction.amount()) {
      throw new IllegalArgumentException();
    }
    s.reduceQuantity();
    return s;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SnackMachine that = (SnackMachine) o;

    return snackMachineId != null ? snackMachineId.equals(that.snackMachineId) : that.snackMachineId == null;
  }

  @Override
  public int hashCode() {
    return snackMachineId != null ? snackMachineId.hashCode() : 0;
  }

  public SnackMachineId getId() {
    return snackMachineId;
  }

  public void setId(SnackMachineId id) {
    this.snackMachineId = id;
  }
}
