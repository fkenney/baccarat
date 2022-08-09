package com.teambaccrat.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Dealer {

  public String getBet(String[] args) throws IOException {
    System.out.println(
        " Who do you want to put the bet on? 1. Banker 2. Player 3. Tie. Press the number, please");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String bet = reader.readLine();

    if (bet.equals("1")) {
      System.out.println("You made a bet on Banker");
    } else if (bet.equals("2")) {
      System.out.println("You made a bet on Player");

    } else if (bet.equals("3")) {
      System.out.println("You made a bet on Tie");

    } else {
      throw new IOException("please, choose number between 1-3 (1. Banker 2.Player, 3.Tie");
    }
    return bet;
  }

//  public String getAmount(String bet) throws IOException{
//    System.out.println(
//        " How much do you want to bet on " + bet + "?");
//    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    String amount = reader.readLine();
//    int betAmount = Integer.valueOf(bet);
//    int balance =
//
//
//    if (betAmount > )
//
//  }
}
