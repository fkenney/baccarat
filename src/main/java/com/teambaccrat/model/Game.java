package com.teambaccrat.model;

import com.teambaccrat.model.exception.IllegalBetException;
import com.teambaccrat.model.exception.IllegalWagerAmountException;
import com.teambaccrat.model.exception.NoBalanceException;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;
import java.util.function.BiConsumer;


public class Game {
  private static final double MARKER_MIN = 0.6667;
  private static final double MARKER_MAX = 0.8;
  public static final int MAX_BET = 100;
  public static final int MIN_BET = 20;
  private Hand player;
  private Hand banker;
  private final Shoe shoe;
  private Balance balance;
  private Bet bet;
  private int amount;
  private Result finalResult;
  private boolean userWon = false;

  ///-------------Constructor -----------------------//

  public Game(int initialBalance) {
    balance = new Balance(initialBalance);
    Random rnd = new SecureRandom();
    int numDecks = 8;
    double markerPoint = rnd.nextDouble() * (MARKER_MAX - MARKER_MIN) + MARKER_MIN;
    shoe = new Shoe(numDecks, rnd, markerPoint);
  }

  ///-------------Setter/Getters -----------------------//
  public void setBet(String userBet) {
    if (!(userBet.equals("1")) && !(userBet.equals("2")) && (!userBet.equals("3"))) {
      throw new IllegalBetException(
          "Please place a valid bet of : 1, 2, or 3");
    } else {
      // Sets bet value to matching Enum Value
      for (Bet b : Bet.values()) {
        if (Objects.equals(b.getSymbol(), userBet)) {
          this.bet = b;
        }
      }
    }
  }

  public Bet getBet() {
    return bet;
  }

  public void setAmount(int amount) {
    int balance = getBalance();
    if (amount < MIN_BET || amount > MAX_BET) {
      throw new IllegalWagerAmountException(
          String.format("Wager amount must be in between a minimum of %d and a maximum of %d%n",
              MIN_BET,
              MAX_BET));
    }
    if (amount > balance) {
      throw new NoBalanceException(
          String.format("You don't have enough money to make that bet, Current Balance $ %d%n",
              balance));
    }
    if (balance < MIN_BET) {
      throw new NoBalanceException(
          String.format("You don't have enough money to make bet, Current Balance = $ %d%n",
              balance));
    }
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }

  public int getBalance() {
    return Balance.getBalance();
  }

  private void setGameResult(Result result) {
    finalResult = result;
  }

  private void setUserWon(Result result, Bet bet) {
    userWon = result.toString().equals(bet.toString());
  }

  public boolean getUserWon() {
    return userWon;
  }
  public String getGameResult() {
    return finalResult.getValue();
  }

  public String getPlayerHand() {
    return player.toString();
  }

  public String getBankerHand() {
    return banker.toString();
  }

  public int getPlayerPoints() {
    return player.pointValue();
  }

  public int getBankerPoints() {
    return banker.pointValue();
  }
  ///------------- Methods  -----------------------//
  private void updateBalance(Result result, Bet bet) {
    if (userWon) {
      balance.add(amount);
    } else {
      balance.subtract(amount);
    }
  }

  private boolean playerGetsThirdCard(Hand player) {
    return player.pointValue() < 6;
  }

  private boolean bankerGetsThirdCard(Hand player, Hand banker) {
    boolean getThirdCard = false;
    int valueOfLastPlayerCard = player.getLastCard().getRank().getPoint();
    int bankerPoints = banker.pointValue();

    if (player.size() == 2 && bankerPoints < 6) {
      getThirdCard = true;
    } else if (player.size() == 3) {
      // If player has 3 Cards the value of the 3rd Card determines whether Banker gets 3rd Card.
      switch (valueOfLastPlayerCard) {
        case 2:
        case 3:
          getThirdCard = bankerPoints < 5;
          break;
        case 4:
        case 5:
          getThirdCard = bankerPoints < 6;
          break;
        case 6:
        case 7:
          getThirdCard = bankerPoints < 7;
          break;
        case 8:
          getThirdCard = bankerPoints < 3;
          break;
        case 0:
        case 1:
        case 9:
          getThirdCard = bankerPoints < 4;
      }
    }
    return getThirdCard;
  }

  public Result whoWon(Hand player, Hand banker) {
    int playerPoints = player.pointValue();
    int bankerPoints = banker.pointValue();
    Result winResult = null;
    if (player.size() ==2 && (playerPoints == 8 || playerPoints == 9) && playerPoints != bankerPoints) {
      winResult = Result.PLAYER;
    } else if ((banker.size() ==2 && bankerPoints == 8 || bankerPoints == 9) && playerPoints != bankerPoints) {
      winResult = Result.BANKER;
    } else if (playerPoints == bankerPoints) {
      winResult = Result.TIE;
    } else if (bankerPoints > playerPoints) {
      winResult = Result.BANKER;
    } else {
      winResult = Result.PLAYER;
    }
    return winResult;
  }

  public void start(BiConsumer<Hand, Boolean> consumer) {
    player = new Hand();
    banker = new Hand();

    shoe.startGame();
    player.add(shoe.draw());
    consumer.accept(player, true);
    banker.add(shoe.draw());
    consumer.accept(banker, false);
    player.add(shoe.draw());
    consumer.accept(player, true);
    banker.add(shoe.draw());
    consumer.accept(banker, false);

    if (playerGetsThirdCard(player)) {
      player.add(shoe.draw());
      consumer.accept(player, true);
    }
    if (bankerGetsThirdCard(player, banker)) {
      banker.add(shoe.draw());
      consumer.accept(banker, false);
    }
    setGameResult(whoWon(player, banker));
    setUserWon(finalResult, bet);
    updateBalance(finalResult, bet);
  }

  public enum Result {
    PLAYER("Player Wins!"),
    BANKER("Banker Wins!"),
    TIE("Tie!");
    private final String value;

    Result(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }
}