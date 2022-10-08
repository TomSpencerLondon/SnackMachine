package com.tomspencerlondon.snackmachine.hexagon.domain;

import java.util.List;
import java.util.Optional;
import org.jmolecules.ddd.types.Identifier;

public class SnackMachine implements org.jmolecules.ddd.types.AggregateRoot<SnackMachine, Identifier> {
  private SnackMachineId snackMachineId;
  private final List<Money> accepted = List.of(Money.ONE_CENT, Money.TEN_CENT, Money.QUARTER_CENT, Money.ONE_DOLLAR, Money.FIVE_DOLLAR, Money.TWENTY_DOLLAR);
  private List<Slot> slots;
  private Money moneyInside;
  private Money moneyInTransaction;

  public SnackMachine(List<Slot> slots) {
    moneyInside = Money.ZERO;
    moneyInTransaction = Money.ZERO;
    this.slots = slots;
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
    return slots.stream()
        .filter(slot -> slot.position() == position)
        .findFirst();
  }

  public Money moneyInTransaction() {
    return moneyInTransaction;
  }

  public Money moneyInside() {
    return moneyInside;
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

  @Override
  public Identifier getId() {
    return this.snackMachineId;
  }

  public void setId(SnackMachineId id) {
    this.snackMachineId = id;
  }
}
