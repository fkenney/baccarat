package com.teambaccrat.model;


public enum Bet {

  BANKER("1"),
  PLAYER("2"),
  TIE("3");

  private final String symbol;

  Bet(String symbol) {
    this.symbol = symbol;
  }
}
