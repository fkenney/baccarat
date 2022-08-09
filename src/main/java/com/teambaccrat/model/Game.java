package com.teambaccrat.model;

import com.teambaccrat.model.exception.GameFinishedException;
import com.teambaccrat.model.exception.IllegalBetException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class Game {

  private static final double MARKER_MIN = 0.6667;
  private static final double MARKER_MAX = 0.8;
  private final Hand player;
  private final Hand banker;
  private final Shoe shoe;
  private final Bet bet;
  private int amount;

  private String initialHands;
  private String finalResult;


  public Game(int amount, String userBet) {
    userBet = userBet.trim();
    if (!userBet.equals("1") || !userBet.equals("2") || !userBet.equals("3")) {
      throw new IllegalBetException(
          "Please place a valid bet of '1' for Banker, '2' for Player, or '3' for Tie ");
    } else {
      player = new Hand();
      banker = new Hand();
      this.bet = Bet.valueOf(userBet);
      this.amount = amount;
      Random rnd = new SecureRandom();
      int numDecks = 8;
      double markerPoint = rnd.nextDouble() * (MARKER_MAX - MARKER_MIN) + MARKER_MIN;
      shoe = new Shoe(numDecks, rnd, markerPoint);
    }
  }

  public boolean playerGetsThirdCard(Hand player) {
    return player.pointValue() < 6;
  }

  public boolean bankerGetsThirdCard(Hand player, Hand banker) {
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
    if ((playerPoints == 8 || playerPoints == 9) && playerPoints != bankerPoints) {
      winResult = Result.PLAYER_WIN;
    } else if ((bankerPoints == 8 || bankerPoints == 9) && playerPoints != bankerPoints) {
      winResult = Result.BANKER_WIN;
    } else if (playerPoints == bankerPoints) {
      winResult = Result.TIE;
    } else if (bankerPoints > playerPoints) {
      winResult = Result.BANKER_WIN;
    } else {
      winResult = Result.PLAYER_WIN;
    }
    return winResult;
  }


  public void start() {
    shoe.startGame();
    player.add(shoe.draw());
    banker.add(shoe.draw());
    player.add(shoe.draw());
    banker.add(shoe.draw());
    initialHands = String.format("Player has cards %s | Banker has cards %s", player.toString(),
        banker.toString());
    if (playerGetsThirdCard(player)) {
      player.add(shoe.draw());
    }
    if (bankerGetsThirdCard(player, banker)) {
      banker.add(shoe.draw());
    }
    finalResult = String.format("%s - You won %d", whoWon(player, banker), amount);

  }


  public enum Result {
    PLAYER_WIN("Player Wins!"),
    BANKER_WIN("Banker Wins!"),
    TIE("Tie!");

    private final String value;

    Result(String value) {
      this.value = value;
    }
  }

}
