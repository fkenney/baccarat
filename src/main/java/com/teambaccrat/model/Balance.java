package com.teambaccrat.model;

public class Balance {

  private static int balance;

  public Balance(int balance) {
    Balance.balance = balance;
  }

  public void add(int wager) {
    balance = getBalance() + wager;
  }

  public void subtract(int wager) {
    balance = getBalance() - wager;
  }

  public static int getBalance() {
    return balance;
  }
}
