package com.teambaccrat.model;

public class Balance {

  private static int balance;


  public Balance(int balance) {
    Balance.balance = balance;
  }

  public void add(int bet) {
    balance = getBalance() + bet;
  }

  public void subtract(int bet) {
    balance = getBalance() - bet;
  }
  public static int getBalance() {
    return balance;
  }
}
