package com.teambaccrat.model;

public class Balance {

  private static int balance;


  public Balance(int bet) {
    this.balance = balance;
  }

  public void add(int bet) {
    this.balance = getBalance() + bet;
  }

  public void subtract(int bet) {
    this.balance = getBalance() - bet;
  }
  public static int getBalance() {
    return balance;
  }
}
