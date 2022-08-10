package com.teambaccrat.model;


public enum Bet {

  BANKER("1"),
  PLAYER("2"),
  TIE("3");

  private final String symbol;

  Bet(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }

  public static Bet getName(String value) {
    for(Bet e: Bet.values()){
      if(e.symbol.equals(value)){
        return e;
      }
    }return null;
  }


}
