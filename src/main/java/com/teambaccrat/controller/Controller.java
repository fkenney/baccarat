package com.teambaccrat.controller;


import com.teambaccrat.model.Balance;
import com.teambaccrat.model.Game;
import com.teambaccrat.model.Hand;
import com.teambaccrat.model.exception.IllegalBetException;
import com.teambaccrat.model.exception.IllegalWagerAmountException;
import com.teambaccrat.model.exception.NoBalanceException;
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
    String bet = null;
    do {
      promptBet();
      try{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        bet = reader.readLine();
        game.setBet(bet);
      }
      catch (IOException e){
          throw new RuntimeException(e);
      }
      catch(IllegalBetException e){
        System.out.println(e.getMessage());
        continue;
      }
    }while (!isValidBet(bet));
    presentBet(bet);
  }

  public static String getBet() {
    return game.getBet().toString();
  }

  private static boolean isValidBet(String bet) {
    if (bet.equals("1") || bet.equals("2") || bet.equals("3")) {
      return true;
    } else {
      return false;
    }
  }

  public void setAmount() throws IOException {
    int amount = 0;
    String userAmount = null;
    do {
      promptWager();
      try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        userAmount = reader.readLine();
        amount = Integer.parseInt(userAmount);
        game.setAmount(amount);
      } catch (IOException e) {
        throw new RuntimeException(e);
      } catch (NumberFormatException e){
        continue;
      } catch (IllegalWagerAmountException | NoBalanceException e) {
        System.out.println(e.getMessage());
        continue;
      }
    } while (!isValidAmount(amount));

    presentAmount();
  }

  private static boolean isValidAmount(int amount) {
    if (amount < 20 || amount > 100 || amount > game.getBalance()) {
      return false;
    } else {
      return true;
    }
  }

  private static int getBalance() {
    return Balance.getBalance();
  }
  public Boolean isValidBalance() {
    int balance = getBalance();
    if (balance < 20) {
      System.out.printf("You don't have enough money to play, Current Balance $ %d%n", balance );
      return false;
    }else{
      return true;
    }
  }

  public void startGame() {
    presentGameStart();
    game.start((hand, isPlayer) -> {
      presentCards(hand, isPlayer );
      try {
        Thread.sleep(1500);
      } catch (InterruptedException e) {
        // ignore exception
      }
    });
    presentFinalHandTally();
    presentGameResults();
  }

  public static void promptBet() {
    System.out.println(view.betPrompt());
  }

  public static void presentBet(String bet) {
    System.out.printf(view.betInfoPresentation(), getBet());

  }

  public void presentAmount() {
    System.out.printf(view.wagerAmountPresentation(), game.getAmount());
  }

  public void presentGreeting() {
    System.out.println(view.greeting());
  }

  public void promptWager() {
    System.out.printf(view.wagerPrompt(), getBet(), game.getBalance(), game.MIN_BET, game.MAX_BET);
  }

  public void presentGameStart(){
    System.out.printf(view.gameStartPresentation(),game.getAmount(), getBet(), game.getBalance());
  }
  public void presentCards(Hand hand, boolean isPlayer){
    System.out.printf(view.cardPresentation(), (isPlayer ? "player":"banker"), hand.getLastCard(), hand.getLastCard().getRank().getPoint(), hand, hand.pointValue());
  }
  public void presentGameResults(){
    System.out.printf(view.getGameResultPresentation(), game.getGameResult(), (game.getUserWon()? "You Won!(+": "You Loss(-"),game.getAmount(), game.getBalance());
  }
  public void presentFinalHandTally(){
    System.out.printf(view.getFinalHandValuePresentation(), game.getPlayerHand(), game.getPlayerPoints(), game.getBankerHand(), game.getBankerPoints());
  }

}
