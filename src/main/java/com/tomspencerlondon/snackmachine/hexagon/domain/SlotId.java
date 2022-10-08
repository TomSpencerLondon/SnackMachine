package com.tomspencerlondon.snackmachine.hexagon.domain;

import org.jmolecules.ddd.types.Identifier;

public class SlotId implements Identifier {
  private final Long id;

  private SlotId(Long id) {
    this.id = id;
  }

  public static SlotId of(Long id) {
    return new SlotId(id);
  }

  public Long getId() {
    return id;
  }
}
