package com.teambaccrat.model;

public enum Rank {

  ACE("A", 1),
  TWO("2", 2),
  THREE("3", 3),
  FOUR("4", 4),
  FIVE("5", 5),
  SIX("6", 6),
  SEVEN("7", 7),
  EIGHT("8", 8),
  NINE("9", 9),
  TEN("10", 0),
  JACK("J", 0),
  QUEEN("Q", 0),
  KING("K", 0);

  private final String symbol;
  private final int point;

  Rank(String symbol, int point) {
    this.symbol = symbol;
    this.point = point;
  }

  public String getSymbol() {
    return symbol;
  }
  public int getPoint() {
    return point;
  }
}

