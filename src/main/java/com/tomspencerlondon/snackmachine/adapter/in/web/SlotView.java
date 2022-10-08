package com.tomspencerlondon.snackmachine.adapter.in.web;

import com.tomspencerlondon.snackmachine.hexagon.domain.Slot;
import com.tomspencerlondon.snackmachine.hexagon.domain.SlotId;
import com.tomspencerlondon.snackmachine.hexagon.domain.Snack;

public class SlotView {

  private final SlotId id;
  private final String snack;
  private final int position;

  public SlotView(SlotId id, String snack, int position) {
    this.id = id;
    this.snack = snack;
    this.position = position;
  }

  public static SlotView from(Slot slot) {
    return new SlotView(
        slot.getId(),
        slot.snackPile().snack().name(),
        slot.position());
  }

  public Long getId() {
    return id.getId();
  }

  public String getSnack() {
    return snack;
  }

  public int getPosition() {
    return position;
  }
}
