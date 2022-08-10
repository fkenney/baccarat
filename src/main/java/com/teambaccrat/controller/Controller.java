package com.teambaccrat.controller;

import com.teambaccrat.model.Balance;
import com.teambaccrat.model.Game;
import com.teambaccrat.model.exception.IllegalBetException;
import com.teambaccrat.view.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Controller {

  private static Game game;
  private View view;

  public Controller(Game game, View view) {
    super();
    this.game = game;
    this.view = view;
  }

  public static void setBet() throws IOException {
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
      throw new IllegalBetException(
          "Please, place a valid bet of '1' for Banker, '2' for Player, or '3' for Tie");
    }
    game.setBet(bet);
  }

  public void setAmount() throws IOException {
    Balance balance = new Balance(Balance.getBalance());
    System.out.println("How much do you want to bet?");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String userAmount = reader.readLine();
    System.out.println(userAmount);
    System.out.println("You put $" + userAmount);
    int amount = Integer.parseInt(userAmount);
    game.setAmount(amount);
  }

  public void startGame() {
    game.start();
  }


  public String setResultOutput() {
    return view.setResultOutput(String.format(game.getGameResult()));
  }

  public String setPlayerHandOutPut() {
    return view.setHandOutPut(
        String.format("The player has %s, %s ", game.getPlayerHand(), game.getPlayerPoints()));
  }

  public String setBankerHandOutPut() {
    return view.setHandOutput(
        String.format("The player has %s, %s", game.getPlayerHand(), game.getBankerPoints()));
  }

  public static boolean continueGame() throws IOException {
    System.out.println("Do you want to play again? 1.Yes 2.No");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String userInput = reader.readLine();
    boolean gameContinue;
    if (userInput.equals("1")) {
      gameContinue = true;
    } else if (userInput.equals("2")) {
      gameContinue = false;
    } else {
      throw new IllegalArgumentException("please, put the valid number. 1.Yes 2.No");
    }
    return gameContinue;
  }
}
