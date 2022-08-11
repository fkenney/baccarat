package com.teambaccrat.view;
import com.teambaccrat.model.Game;

public class View {

  public void printBetInfo(String bet) {
    System.out.println("You made a bet on " + bet);
  }

  public void printAmountInfo(String amount) {
    System.out.println("You bet $" + amount);
  }

  public void printGreeting() {
    System.out.println("Welcome to the Baccarat table!");
  }

  public void printIllegalBetError() {
    System.out.println("Please, made a valid bet");
  }

}
