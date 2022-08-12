package com.teambaccrat.model;

import com.teambaccrat.model.exception.IllegalBetException;
import com.teambaccrat.model.exception.IllegalWagerAmountException;
import com.teambaccrat.model.exception.NoBalanceException;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;
import java.util.function.BiConsumer;

/**
 * Calculates outcome of a game of Baccarat given a bet and wager from
 * {@link com.teambaccrat.controller.Controller}.
 */
public class Game {

  private static final double MARKER_MIN = 0.6667;
  private static final double MARKER_MAX = 0.8;
  public static final int MAX_BET = 100;
  public static final int MIN_BET = 20;

  public static final int NUM_DECKS = 8;
  private Hand player;
  private Hand banker;
  private final Shoe shoe;
  private final Balance balance;
  private Bet bet;
  private int amount;
  private Result finalResult;
  private boolean userWon = false;

  ///-------------Constructor -----------------------//

  /**
   * Creates an instance of the game with an initial balance, and creates instances of {@link Shoe}
   * and {@link Hand}.
   *
   * @param initialBalance int
   */
  public Game(int initialBalance) {
    balance = new Balance(initialBalance);
    Random rnd = new SecureRandom();
    double markerPoint = rnd.nextDouble() * (MARKER_MAX - MARKER_MIN) + MARKER_MIN;
    shoe = new Shoe(NUM_DECKS, rnd, markerPoint);
  }

  ///-------------Setter/Getters --------------------//

  /**
   * Checks if the bet is valid and sets the userBet to the matching {@link Bet} value
   *
   * @param userBet the bet given by user
   * @throws IllegalBetException Thrown if userBet does not equal 1, 2, or 3.
   */
  public void setBet(String userBet) throws IllegalBetException {
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

  /**
   * Checks if the amount is a valid amount and sets the amount.
   *
   * @param amount the wager amount given by user
   * @throws IllegalWagerAmountException Thrown if the bet amount is less than 20 or greater than
   *                                     100.
   * @throws NoBalanceException          Thrown if the bet amount is greater than current balance.
   */
  public void setAmount(int amount) throws IllegalWagerAmountException, NoBalanceException {
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

  /**
   * Determines whether user won, if the {@link Result} matches {@link Bet}
   *
   * @param result the result of the game
   * @param bet    the initial bet from the user
   */
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

  /**
   * Updates {@link Balance} based on whether the user won.
   *
   * @param result game result
   * @param bet    the initial bet
   */
  private void updateBalance(Result result, Bet bet) {
    if (userWon) {
      balance.add(amount);
    } else {
      balance.subtract(amount);
    }
  }

  /**
   * Determines whether {@link Hand} that belongs to the player gets dealt a third card.
   *
   * @param player the Hand that belongs to the player
   * @return boolean
   */
  private boolean playerGetsThirdCard(Hand player) {
    return player.pointValue() < 6;
  }

  /**
   * Determines whether {@link Hand} that belongs to the banker gets dealt a third card.
   *
   * @param player the Hand that belongs to the player
   * @param banker the Hand that belongs to the banker
   * @return boolean
   */
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

  /**
   * Determines the {@link Result} of the game by comparing points of both hands
   *
   * @param player the player hand
   * @param banker the banker hand
   * @return Result
   */
  public Result whoWon(Hand player, Hand banker) {
    int playerPoints = player.pointValue();
    int bankerPoints = banker.pointValue();
    Result winResult = null;
    if (player.size() == 2 && (playerPoints == 8 || playerPoints == 9)
        && playerPoints != bankerPoints) {
      winResult = Result.PLAYER;
    } else if ((banker.size() == 2 && bankerPoints == 8 || bankerPoints == 9)
        && playerPoints != bankerPoints) {
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

  /**
   * Deals cards from {@link Shoe} to each hand, determines whether either hand gets third card, and
   * updates information based on final results.
   *
   * @param consumer used as a callback to determine banker vs player hand
   */
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