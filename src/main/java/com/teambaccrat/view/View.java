package com.teambaccrat.view;
import com.teambaccrat.model.Game;

public class View {

  public void printBetInfo(Game game) {
    System.out.println("You made a bet on " + game);
  }

  public void printAmountInfo(Game game) {
    System.out.println("You bet $" + game);
  }

  public void printGreeting() {
    System.out.println("Welcome to the Baccarat table!");
  }

  public void printIllegalBetError() {
    System.out.println("Welcome to the Baccarat table!");
  }

}
