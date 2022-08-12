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


/**
 * Receives and validates user input, and run game using methods in
 * {@link com.teambaccrat.model.Game}, and updates {@link com.teambaccrat.view.View}.
 */

public class Controller {

  private static Game game;
  private static View view;

  public Controller(Game game, View view) {
    super();
    this.game = game;
    this.view = view;
  }

  /**
   * Receives user bet input, and set the valid bet using setBet method in
   * {@link com.teambaccrat.model.Game }
   */

  public static void setBet() {
    String bet = null;
    do {
      promptBet();
      try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        bet = reader.readLine();
        game.setBet(bet);
      } catch (IOException e) {
        throw new RuntimeException(e);
      } catch (IllegalBetException e) {
        System.out.println(e.getMessage());
        continue;
      }
    } while (!isValidBet(bet));
    presentBet(bet);
  }

  public static String getBet() {
    return game.getBet().toString();
  }


  /**
   * Checks if the user bet input is valid. Boolean result will be returned to setBet method.
   *
   * @param bet String
   * @return boolean
   */

  private static boolean isValidBet(String bet) {
    return bet.equals("1") || bet.equals("2") || bet.equals("3");
  }


  /**
   * Receives user bet input using, and run setAmount method in {@link com.teambaccrat.model.Game }
   * only if the bet input is valid. Confirms bet amounts to user by presenting print out.
   */
  public void setAmount() {
    int amount = 0;
    String userAmount;
    do {
      promptWager();
      try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        userAmount = reader.readLine();
        amount = Integer.parseInt(userAmount);
        game.setAmount(amount);
      } catch (IOException e) {
        throw new RuntimeException(e);
      } catch (NumberFormatException e) {
        continue;
      } catch (IllegalWagerAmountException | NoBalanceException e) {
        System.out.println(e.getMessage());
        continue;
      }
    } while (!isValidAmount(amount));

    presentAmount();
  }

  /**
   * Checks if the user amount input is valid.
   *
   * @param amount int
   * @return boolean
   */

  private static boolean isValidAmount(int amount) {
    return amount >= 20 && amount <= 100 && amount <= game.getBalance();
  }

  private static int getBalance() {
    return Balance.getBalance();
  }

  /**
   * Checks if user balance is more than minimum bet, 20.
   *
   * @return boolean
   */

  public Boolean isValidBalance() {
    int balance = getBalance();
    if (balance < 20) {
      presentNotEnoughBalance();
      return false;
    } else {
      return true;
    }
  }


  /**
   * Calls game methods in {@link com.teambaccrat.model.Game } to starts game, deals cards, and gets
   * results Each dealt card will be presented to the user with 1.5 seconds of time interval.
   * Presents game results to user.
   */

  public void startGame() {
    presentGameStart();
    game.start((hand, isPlayer) -> {
      presentCards(hand, isPlayer);
      try {
        Thread.sleep(1500);
      } catch (InterruptedException e) {
        // ignore exception
      }
    });
    presentFinalHandTally();
    presentGameResults();
  }


  /**
   * All methods below will present printouts to user by updating values in printout formats in
   * {@link com.teambaccrat.view.View}
   */

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

  public void presentGameStart() {
    System.out.printf(view.gameStartPresentation(), game.getAmount(), getBet(), game.getBalance());
  }

  public void presentCards(Hand hand, boolean isPlayer) {
    System.out.printf(view.cardPresentation(), (isPlayer ? "player" : "banker"), hand.getLastCard(),
        hand.getLastCard().getRank().getPoint(), hand, hand.pointValue());
  }

  public void presentGameResults() {
    System.out.printf(view.getGameResultPresentation(), game.getGameResult(),
        (game.getUserWon() ? "You Won!(+" : "You Loss(-"), game.getAmount(), game.getBalance());
  }

  public void presentFinalHandTally() {
    System.out.printf(view.getFinalHandValuePresentation(), game.getPlayerHand(),
        game.getPlayerPoints(), game.getBankerHand(), game.getBankerPoints());
  }

  public void presentNotEnoughBalance() {
    System.out.println(view.noBalancePresentation());
  }

}
