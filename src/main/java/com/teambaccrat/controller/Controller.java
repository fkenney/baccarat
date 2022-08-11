package com.teambaccrat.controller;

import com.teambaccrat.model.Balance;
import com.teambaccrat.model.Bet;
import com.teambaccrat.model.Game;
import com.teambaccrat.model.exception.IllegalBetException;
import com.teambaccrat.model.exception.IllegalWagerAmountException;
import com.teambaccrat.view.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Controller {

  private static Game game;
  private static View view;

  public Controller(Game game, View view) {
    super();
    this.game = game;
    this.view = view;
  }

  public static void setBet() throws IOException {
    String bet;
    do{
      System.out.println(
          "\n Who do you want to put the bet on? 1. Banker 2. Player 3. Tie. \n ");
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      bet = reader.readLine();
      System.out.println(bet);
    } while (!isValidBet(bet));
    game.setBet(bet);
    Bet userBet = Bet.getName(bet);
    presentBet(userBet.toString());
  }

  private static boolean isValidBet(String bet){
    if (bet.equals("1") || bet.equals("2") || bet.equals("3")){
      return true;
    } else {
      System.out.println("Please, choose the valid option");
      return false;
    }
  }

  public static String getBet() {
    return game.getBet().toString();
  }

  public void setAmount () throws IOException {

    int amount = 0;
    String userAmount = null;
    do{
      System.out.println("How much do you want to bet on " + getBet() + "? \n Your current Balance is : " + game.getBalance()
        + "Min: 20, Max: 100 " );
      try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        userAmount = reader.readLine();
        amount = Integer.parseInt(userAmount);
      } catch (IOException e) {
        throw new RuntimeException(e);
      } catch (NumberFormatException e) {
        continue;
      }
    } while (!isValidAmount(amount));
    game.setAmount(amount);
    presentAmount(userAmount);
  }

  private static boolean isValidAmount(int amount){
    if (amount < 20 || amount > 100 || amount > game.getBalance()){
      System.out.println("Please, put the valid amount.");
      return false;
    }else{
      return true;
    }
  }

  public void startGame() {
    game.start((hand,isPlayer) -> {
      System.out.println("Dealt to " + (isPlayer? "player" : "banker") + ": " + hand.getLastCard() + "\n" + hand);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // ignore exception
      }
    });
  }

  public static void presentBet(String bet) {
    view.printBetInfo(String.valueOf(bet));

  }

  public void presentAmount(String amount) {
    view.printAmountInfo(amount);
  }

  public void presentGreeting() {
    view.printGreeting();
  }

  public void presentIllegalBetError() {
    view.printIllegalBetError();

  }


}
