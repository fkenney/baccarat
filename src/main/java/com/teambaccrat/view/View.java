package com.teambaccrat.view;

import com.teambaccrat.model.Game;

public class View {

  public void printBetInfo(Game game) {
    System.out.println("You made a bet on " + game);
  }

  public void printAmountInfo(Game game) {
    System.out.println("You bet $" + game);
  }

}
