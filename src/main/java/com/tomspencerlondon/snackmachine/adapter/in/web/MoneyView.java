package com.tomspencerlondon.snackmachine.adapter.in.web;

import com.tomspencerlondon.snackmachine.hexagon.domain.Money;

public class MoneyView {

  public static Money from(String coin) {
    switch(coin) {
      case "1_C": return Money.ONE_CENT;
      case "5_C": return Money.FIVE_CENT;
      case "10_C": return Money.TEN_CENT;
      case "25_C": return Money.QUARTER_CENT;
      case "1_D": return Money.ONE_DOLLAR;
      case "5_D": return Money.FIVE_DOLLAR;
      case "20_D": return Money.TWENTY_DOLLAR;
      default: return Money.ZERO;
    }
  }
}
