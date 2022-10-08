package com.tomspencerlondon.snackmachine.adapter.in.web;

import com.tomspencerlondon.snackmachine.hexagon.domain.Slot;
import com.tomspencerlondon.snackmachine.hexagon.domain.SlotId;
import com.tomspencerlondon.snackmachine.hexagon.domain.Snack;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackPile;

public class SlotView {

  private final SlotId id;
  private final Snack snack;
  private final SnackPile snackPile;
  private final int position;

  private SlotView(SlotId id, Snack snack, SnackPile snackPile, int position) {
    this.id = id;
    this.snack = snack;
    this.snackPile = snackPile;
    this.position = position;
  }

  public static SlotView from(Slot slot) {
    return new SlotView(
        slot.getId(),
        slot.snack(),
        slot.snackPile(),
        slot.position());
  }

  public Long getId() {
    return id.getId();
  }

  public String getSnack() {
    return snack.name();
  }

  public int getPosition() {
    return position;
  }

  public double getPrice() {
    return snackPile.price();
  }

  public int getQuantity() {
    return snackPile.quantity();
  }
}
