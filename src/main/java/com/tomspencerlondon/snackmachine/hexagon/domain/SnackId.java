package com.tomspencerlondon.snackmachine.hexagon.domain;

import org.jmolecules.ddd.types.Identifier;

public class SnackId implements Identifier {
  private final Long id;

  private SnackId(Long id) {
    this.id = id;
  }

  public static SnackId of(Long id) {
    return new SnackId(id);
  }

  public Long getId() {
    return id;
  }
}
